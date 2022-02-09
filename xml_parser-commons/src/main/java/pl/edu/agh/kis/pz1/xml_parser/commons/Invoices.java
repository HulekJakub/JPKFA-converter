package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for further data formatting
 */
@Getter
@Setter
public class Invoices {


    private final List<Invoice> invoiceList = new ArrayList<>();

    /**
     * Adds invoice to invoice list
     * @param invoice Invoice to add to the list
     */
    public void addInvoice(Invoice invoice){
        invoiceList.add(invoice);
    }

}
