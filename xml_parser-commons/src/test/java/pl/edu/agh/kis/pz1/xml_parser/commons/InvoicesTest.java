package pl.edu.agh.kis.pz1.xml_parser.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class
 */
public class InvoicesTest {

    @Test
    public void addInvoice() {
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        Invoices invoices = new Invoices();
        Invoice invoice = new Invoice(strings);
        invoice.setAdresOdbiorcy("21");
        invoices.addInvoice(invoice);
        assertTrue(invoices.getInvoiceList().contains(invoice));
    }
}