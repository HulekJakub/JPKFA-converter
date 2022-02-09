package pl.edu.agh.kis.pz1.xml_parser.generator;

import org.junit.After;
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
public class GeneratorTest {
    private static final String CSV_READ_FILE_NAME  =  "src/test/resources/csv_test.csv";
    private static final String CSV_WRITE_FILE_NAME  =  "target/test-classes/csv_test.xml";
    private static final String XLSX_READ_FILE_NAME  =  "src/test/resources/xlsx_test.xlsx";
    private static final String XLSX_WRITE_FILE_NAME  =  "target/test-classes/xlsx_test.xml";
    private static final String BAD_FILE_NAME = "awdfadawfawf.csv";

    Generator generator;

    @After
    public void tearDown() throws Exception {
        generator = null;
    }

    @Test
    public void createXmlFile() throws IOException {
        generator = new Generator(CSV_READ_FILE_NAME, CSV_WRITE_FILE_NAME);
        generator.createXmlFile();
        String result = generator.createXmlFile();
        assertEquals("File already exists.", result);
    }

    @Test
    public void generateFromCsv() throws IOException, JAXBException {
        generator = new Generator(CSV_READ_FILE_NAME, CSV_WRITE_FILE_NAME);
        generator.createXmlFile();
        generator.generate();

        assertFalse(unmarshallFile(CSV_WRITE_FILE_NAME).getInvoiceMainList().isEmpty());
    }

    @Test
    public void generateFromXlsx() throws IOException, JAXBException {
        generator = new Generator(XLSX_READ_FILE_NAME, XLSX_WRITE_FILE_NAME);
        generator.createXmlFile();
        generator.generate();

        assertFalse(unmarshallFile(XLSX_WRITE_FILE_NAME).getInvoiceMainList().isEmpty());
    }

    public InvoicesForXml unmarshallFile(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(InvoicesForXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (InvoicesForXml) unmarshaller.unmarshal(new File(path));
    }

    @Test
    public void filesAreGood() throws IOException {
        generator = new Generator(CSV_READ_FILE_NAME, CSV_WRITE_FILE_NAME);
        generator.createXmlFile();
        assertTrue(generator.filesAreGood());
        generator = new Generator(BAD_FILE_NAME, CSV_WRITE_FILE_NAME);
        generator.createXmlFile();
        assertFalse(generator.filesAreGood());
        generator = new Generator(CSV_READ_FILE_NAME, BAD_FILE_NAME);
        assertFalse(generator.filesAreGood());
    }
}