package pl.edu.agh.kis.pz1.xml_parser.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.edu.agh.kis.pz1.xml_parser.commons.Invoice;
import pl.edu.agh.kis.pz1.xml_parser.commons.Invoices;
import pl.edu.agh.kis.pz1.xml_parser.commons.InvoicesForXml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Class that reads data form xlsx files
 */
public class XlsxReader {

    /**
     * Reads data from given file
     * @param path path to xlsx file
     * @return Invoices data object created from information read from file
     * @throws IOException if error occurred during reading from file
     */
    public InvoicesForXml read(File path) throws IOException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Invoices invoices = new Invoices();

        try(XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path))){
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                String[] args = new String[15];
                for (int i = 0; i < 15; i++) {
                    args[i] = getCellString(row.getCell(i), formatter);
                    args[i] = args[i].replace(Character.toString(0x00A0),"");
                }
                invoices.addInvoice(new Invoice(args));
            }
        }
        return JPKFAFormatter.formatForXml(invoices);
    }

    private String getCellString(Cell cell, DateFormat formatter){
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return formatter.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            default:
                return "";
        }
    }
}
