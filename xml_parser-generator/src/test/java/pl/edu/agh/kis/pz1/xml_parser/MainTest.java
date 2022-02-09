package pl.edu.agh.kis.pz1.xml_parser;

import org.junit.Test;
import pl.edu.agh.kis.pz1.xml_parser.commons.Invoices;
import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test class
 */
public class MainTest {
    private static final String CSV_READ_FILE_NAME  =  "src/test/resources/csv_test.csv";
    private static final String CSV_WRITE_FILE_NAME  =  "target/test-classes/csv_test.xml";
    private static final String XLSX_READ_FILE_NAME  =  "src/test/resources/xlsx_test.xlsx";
    private static final String XLSX_WRITE_FILE_NAME  =  "target/test-classes/xlsx_test.xml";

    @Test
    public void mainForCsv() throws JAXBException {
        Main.main(new String[]{CSV_READ_FILE_NAME, CSV_WRITE_FILE_NAME});
        assertFalse(unmarshallFile(CSV_WRITE_FILE_NAME).getInvoiceMainList().isEmpty());
    }

    @Test
    public void mainForXlsx() throws JAXBException {
        Main.main(new String[]{XLSX_READ_FILE_NAME, XLSX_WRITE_FILE_NAME});
        assertFalse(unmarshallFile(XLSX_WRITE_FILE_NAME).getInvoiceMainList().isEmpty());
    }

    @Test
    public void mainForInvalidArgs() throws IOException {
        File writeFile = new File(XLSX_WRITE_FILE_NAME);
        writeFile.delete();
        writeFile.createNewFile();
        Main.main(new String[]{XLSX_READ_FILE_NAME, XLSX_READ_FILE_NAME});

        assertEquals(0, writeFile.length());
        writeFile = new File(XLSX_WRITE_FILE_NAME);
        writeFile.delete();
        writeFile.createNewFile();
        Main.main(new String[]{XLSX_WRITE_FILE_NAME, XLSX_WRITE_FILE_NAME});

        assertEquals(0, writeFile.length());

        writeFile = new File(XLSX_WRITE_FILE_NAME);
        writeFile.delete();
        writeFile.createNewFile();
        Main.main(new String[]{XLSX_WRITE_FILE_NAME});

        assertEquals(0, writeFile.length());
    }

    public InvoicesForXml unmarshallFile(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(InvoicesForXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (InvoicesForXml) unmarshaller.unmarshal(new File(path));
    }
}