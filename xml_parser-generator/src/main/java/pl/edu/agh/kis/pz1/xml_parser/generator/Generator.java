package pl.edu.agh.kis.pz1.xml_parser.generator;

import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;
import pl.edu.agh.kis.pz1.xml_parser.readers.CsvReader;
import pl.edu.agh.kis.pz1.xml_parser.readers.XlsxReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

/**
 * Main logic class for xml parsing
 */
public class Generator {
    private final File readFile;
    private final File writeFile;
    private final boolean isCsv;

    /**
     * Generator constructor
     * @param readFile relative path to csv/xlsx file
     * @param writeFile relative path to xml file
     */
    public Generator(String readFile, String writeFile){
        this.readFile = new File(readFile);
        this.writeFile = new File(writeFile);
        isCsv = readFile.matches(".*\\.csv$");
    }

    /**
     * Creates xml file
     * @return message to print
     * @throws IOException if error occurred during creation of new file
     */
    public String createXmlFile() throws IOException {
        boolean existed = !writeFile.createNewFile();
        if(existed){
            return "File already exists.";
        }
        return "File was successfully created.";
    }

    /**
     * Generates xml file content
     * @return true if succeeded, false otherwise
     * @throws IOException if error occurred during reading/writing from/into files
     */
    public boolean generate() throws IOException {
        InvoicesForXml invoicesForXml;
        if(isCsv){
            CsvReader csvReader = new CsvReader();
            invoicesForXml = csvReader.read(readFile);
        } else {
            XlsxReader xlsxReader = new XlsxReader();
            invoicesForXml = xlsxReader.read(readFile);
        }
        try {
            marshal(invoicesForXml);
        } catch (JAXBException e) {
            return false;
        }
        return true;
    }

    private void marshal(InvoicesForXml invoicesForXml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(InvoicesForXml.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(invoicesForXml, writeFile);
    }

    /**
     * Checks if both files exist
     * @return true if both files exit, false otherwise
     */
    public boolean filesAreGood(){
        return readFile.isFile() && writeFile.isFile();
    }
}
