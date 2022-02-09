package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


/**
 * Data class for xml JPK FA format generating
 */
@XmlRootElement(name = "JPK")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoicesForXml {
    @XmlElement(name = "Faktura")
    private final List<InvoiceMain> invoiceMainList = new ArrayList<>();
    @XmlElement(name = "FakturaCtrl")
    private final InvoiceControl invoiceControl = new InvoiceControl();
    @XmlElement(name = "FakturaWiersz")
    private final List<InvoiceRow> invoiceRowList = new ArrayList<>();
    @XmlElement(name = "FakturaWierszCtrl")
    private final InvoiceRowControl invoiceRowControl = new InvoiceRowControl();
}
