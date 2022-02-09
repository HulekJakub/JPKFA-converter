package pl.edu.agh.kis.pz1.xml_parser.readers;

import org.junit.Test;
import pl.edu.agh.kis.pz1.xml_parser.commons.Invoices;
import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test class
 */
public class CsvReaderTest {
    private static final String CSV_READ_FILE_NAME  =  "src/test/resources/csv_test.csv";


    @Test
    public void read() throws IOException {
        CsvReader csvReader = new CsvReader();
        InvoicesForXml invoicesForXml = csvReader.read(new File(CSV_READ_FILE_NAME));
        assertFalse(invoicesForXml.getInvoiceMainList().isEmpty());
        assertNotNull(invoicesForXml.getInvoiceMainList().get(0).getCenaBruttoFakturyLacznie());
    }
}