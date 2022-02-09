package pl.edu.agh.kis.pz1.xml_parser.readers;

import pl.edu.agh.kis.pz1.xml_parser.commons.Invoice;
import pl.edu.agh.kis.pz1.xml_parser.commons.Invoices;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Class that reads data form csv files
 */
public class CsvReader {

    /**
     * Reads data from given file
     * @param path path to csv file
     * @return Invoices data object created from information read from file
     * @throws IOException if error occurred during reading from file
     */
    public InvoicesForXml read(File path) throws IOException {
        Invoices invoices = new Invoices();
        Reader in = new FileReader(path, StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.builder().setDelimiter('\t').build().parse(in);

        boolean first = true;

        for (CSVRecord csvRecord : records) {
            if(!first){
                String[] args = new String[15];
                for (int i = 0; i < 15; i++) {
                    args[i] = csvRecord.get(i);
                    args[i] = args[i].replace(Character.toString(0x00A0),"");
                }
                invoices.addInvoice(new Invoice(args));
            }
            first = false;
        }
        return JPKFAFormatter.formatForXml(invoices);
    }
}
