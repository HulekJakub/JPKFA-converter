package pl.edu.agh.kis.pz1.xml_parser.commons;

import lombok.Getter;
import lombok.Setter;



/**
 * Data class for further data formatting
 */
@Getter
@Setter
public class Invoice {
    private String nazwaOdbiorcy;
    private String adresOdbiorcy;
    private String nipOdbiorcy;
    private String dataWystawienia;
    private String dataSprzedazy;
    private String nrFaktury;
    private String tytulPozycji;
    private String liczbaSztuk;
    private String cenaJednostkowa;
    private String stawkaPodatku;
    private String kwotaPodatku;
    private String cenaNettoPozycji;
    private String cenaBruttoPozycji;
    private String cenaNettoFakturyLacznie;
    private String cenaBruttoFakturyLacznie;

    /**
     * Constructor setting all object variables
     * @param args String array of length 15
     */
    public Invoice(String[] args){
        nazwaOdbiorcy = args[0];
        adresOdbiorcy = args[1];
        nipOdbiorcy = args[2];
        dataWystawienia = args[3];
        dataSprzedazy = args[4];
        nrFaktury = args[5];
        tytulPozycji = args[6];
        liczbaSztuk = args[7];
        cenaJednostkowa = args[8];
        stawkaPodatku = args[9];
        kwotaPodatku = args[10];
        cenaNettoPozycji = args[11];
        cenaBruttoPozycji = args[12];
        cenaNettoFakturyLacznie = args[13];
        cenaBruttoFakturyLacznie = args[14];
    }

}
