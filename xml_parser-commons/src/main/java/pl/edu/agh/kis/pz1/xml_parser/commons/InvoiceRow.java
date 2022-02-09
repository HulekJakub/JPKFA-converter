package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data class for xml JPK FA format generating
 */
@XmlRootElement(name = "FakturaWiersz")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoiceRow {
    private String nrFaktury;
    private String nazwaTowaru;
    private String miara;
    private String liczbaSztuk;
    private String cenaJednostkowaNetto = "0.0";
    private String cenaJednostkowaBrutto = "0.0";
    private String wartoscCalkowita = "0.0";
    private String stawkaPodatku;

    /**
     * Constructor for amount type of item quantifier
     */
    public InvoiceRow(){
        miara = "szt";
    }
}
