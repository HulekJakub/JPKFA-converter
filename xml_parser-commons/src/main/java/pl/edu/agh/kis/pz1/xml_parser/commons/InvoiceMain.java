package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data class for xml JPK FA format generating
 */
@XmlRootElement(name = "Faktura")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoiceMain {
    private String kodWaluty;
    private String dataWystawienia;
    private String nrFaktury;
    private String nazwaOdbiorcy;
    private String adresOdbiorcy;
    private String nipOdbiorcy;
    private String dataSprzedazy;
    private String cenaNettoFakturyLacznie = "0.0";
    private String kwotaPodatkuLacznie = "0.0";
    private String cenaBruttoFakturyLacznie = "0.0";

    /**
     * Constructor for Polish currency
     */
    public InvoiceMain(){
        kodWaluty = "PLN";
    }
}
