package Entiteti;

public abstract class Korisnik {
    private String ime;
    private String prezime;
    private String pol;
    private String telefon;
    private String adresa;
    private String korisnicko_ime;
    private String lozinka;

    Korisnik() {

    }

    Korisnik(String ime, String prezime, String pol, String telefon, String adresa, String korisnicko_ime, String lozinka){
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.telefon = telefon;
        this.adresa = adresa;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
    }

}
