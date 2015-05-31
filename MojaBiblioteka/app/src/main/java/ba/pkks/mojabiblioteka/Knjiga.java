package ba.pkks.mojabiblioteka;

import java.sql.Date;

/**
 * Created by tarik on 5/29/15.
 */
public class Knjiga {

    public Knjiga(String naslov, String autor, String ISBN, Date datumObjave, int brojStranica, String opis, boolean procitana) {
        this.naslov = naslov;
        this.autor = autor;
        this.ISBN = ISBN;
        this.datumObjave = datumObjave;
        this.brojStranica = brojStranica;
        this.opis = opis;
        this.procitana = procitana;
    }

    private String naslov;
    private String autor;
    private String ISBN;
    private java.sql.Date datumObjave;
    private int brojStranica;
    private String opis;


    private boolean procitana;

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public java.sql.Date getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(java.sql.Date datumObjave) {
        this.datumObjave = datumObjave;
    }

    public int getBrojStranica() { return brojStranica; }

    public void setBrojStranica(int brojStranica) {
        this.brojStranica = brojStranica;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isProcitana() { return procitana; }

    public void setProcitana(boolean procitana) { this.procitana = procitana; }

    @Override
    public String toString(){
        return naslov + " - " + autor;
    }

}
