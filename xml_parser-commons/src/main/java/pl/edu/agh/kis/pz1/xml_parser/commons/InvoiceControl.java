package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data class for xml JPK FA format generating
 */
@XmlRootElement(name = "FakturaCtrl")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoiceControl {
    private int liczbaFaktur = 0;
    private String wartoscFaktur = "0.0";

}
