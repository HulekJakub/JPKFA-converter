package pl.edu.agh.kis.pz1.xml_parser.readers;


import org.junit.Test;
import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test class
 */
public class XlsxReaderTest {
    private static final String XLSX_READ_FILE_NAME  =  "src/test/resources/xlsx_test.xlsx";


    @Test
    public void read() throws IOException {
        XlsxReader xlsxReader = new XlsxReader();
        InvoicesForXml invoicesForXml = xlsxReader.read(new File(XLSX_READ_FILE_NAME));
        assertFalse(invoicesForXml.getInvoiceMainList().isEmpty());
        assertNotNull(invoicesForXml.getInvoiceMainList().get(0).getCenaBruttoFakturyLacznie());
    }
}