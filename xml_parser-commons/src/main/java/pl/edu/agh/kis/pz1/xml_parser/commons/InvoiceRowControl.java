package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data class for xml JPK FA format generating
 */
@XmlRootElement(name = "FakturaWierszCtrl")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoiceRowControl {
    private int liczbaWierszyFaktur = 0;
    private String wartoscWierszyFaktur = "0.0";
}
