package pl.edu.agh.kis.pz1.xml_parser.readers;

import pl.edu.agh.kis.pz1.xml_parser.commons.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class providing tools for formatting "Invoices" data class to JPK FA data class
 */
public class JPKFAFormatter {
    private JPKFAFormatter(){}

    /**
     * Method formatting data to JPK FA format
     * @param invoices data class with information about invoices
     * @return data class ready to be marshalled into xml file
     */
    public static InvoicesForXml formatForXml(Invoices invoices){
        InvoicesForXml invoicesForXml = new InvoicesForXml();
        InvoiceControl invoiceControl = invoicesForXml.getInvoiceControl();
        InvoiceRowControl invoiceRowControl = invoicesForXml.getInvoiceRowControl();

        Map<String, InvoiceMain> invoiceMainMap = new HashMap<>();

        for(Invoice invoice:invoices.getInvoiceList()){
           InvoiceMain invoiceMain = invoiceMainMap.get(invoice.getNrFaktury());
           if(invoiceMain == null){
               invoiceMain = new InvoiceMain();
               invoiceMainMap.put(invoice.getNrFaktury(), invoiceMain);
               invoiceMain.setDataWystawienia(invoice.getDataWystawienia());
               invoiceMain.setNrFaktury(invoice.getNrFaktury());
               invoiceMain.setNazwaOdbiorcy(invoice.getNazwaOdbiorcy());
               invoiceMain.setAdresOdbiorcy(invoice.getAdresOdbiorcy());
               invoiceMain.setNipOdbiorcy(invoice.getNipOdbiorcy());
               invoiceMain.setDataSprzedazy(invoice.getDataSprzedazy());
           }
           invoiceMain.setCenaNettoFakturyLacznie(round(cutEnd(invoice.getCenaNettoFakturyLacznie())));
           invoiceMain.setKwotaPodatkuLacznie(round(cutEnd(invoice.getKwotaPodatku())));
           invoiceMain.setCenaBruttoFakturyLacznie(round(cutEnd(invoice.getCenaBruttoFakturyLacznie())));

           InvoiceRow invoiceRow = new InvoiceRow();
           invoiceRow.setStawkaPodatku(invoice.getStawkaPodatku());
           invoiceRow.setNrFaktury(invoice.getNrFaktury());
           invoiceRow.setNazwaTowaru(invoice.getTytulPozycji());
           invoiceRow.setLiczbaSztuk(invoice.getLiczbaSztuk());
           invoiceRow.setCenaJednostkowaNetto(round(cutEnd(invoice.getCenaJednostkowa())));
           invoiceRow.setCenaJednostkowaBrutto(round(brutto(invoice.getCenaJednostkowa(), invoice.getStawkaPodatku())));
           invoiceRow.setWartoscCalkowita(round(multiply(invoiceRow.getCenaJednostkowaNetto(), invoiceRow.getLiczbaSztuk())));
           invoicesForXml.getInvoiceRowList().add(invoiceRow);

           invoiceRowControl.setLiczbaWierszyFaktur(invoiceRowControl.getLiczbaWierszyFaktur()+1);
           invoiceRowControl.setWartoscWierszyFaktur(round(add(invoiceRowControl.getWartoscWierszyFaktur(), invoiceRow.getWartoscCalkowita())));
        }

        invoiceControl.setLiczbaFaktur(invoiceMainMap.size());
        for(InvoiceMain invoiceMain:invoiceMainMap.values()){
            invoiceControl.setWartoscFaktur(round(add(invoiceControl.getWartoscFaktur(), invoiceMain.getCenaBruttoFakturyLacznie())));
            invoicesForXml.getInvoiceMainList().add(invoiceMain);
        }


        return invoicesForXml;
    }

    private static String round(String s){
        s = String.valueOf(Math.round(Double.parseDouble(s.replace(',','.'))*100)/100.0);
        if(s.matches("^.*\\.[0-9]$")){
            s = s + "0";
        }
        return s;
    }

    private static String add(String s1, String s2){
        s1 = s1.replace(',','.').split(" ")[0];
        s2 = s2.replace(',','.').split(" ")[0];
        return String.valueOf(Double.parseDouble(s1) + Double.parseDouble(s2));

    }

    private static String brutto(String s, String vat){
        s = s.replace(',','.').split(" ")[0];
        return String.valueOf(Double.parseDouble(s) * (1.0 + 0.01*Double.parseDouble(vat)));
    }

    private static String multiply(String s1, String s2){
        s1 = s1.replace(',','.').split(" ")[0];
        s2 = s2.replace(',','.').split(" ")[0];
        return String.valueOf(Double.parseDouble(s1) * Double.parseDouble(s2));
    }


    private static String cutEnd(String s){
        return s.split(" ")[0];
    }
}
