package entity;

public abstract class Korisnik {
    private int id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String pol;
    private String telefon;
    private String adresa;
    private String lozinka;
    private boolean obrisan = false;

    Korisnik() {

    }

    Korisnik(int id,String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka){
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.telefon = telefon;
        this.adresa = adresa;
        this.lozinka = lozinka;
    }

    public String toFileString() {
        return id+","+korisnickoIme+","+ime+","+prezime+","+pol+","+telefon+","+adresa+","+lozinka+","+obrisan;
    }
    public String toString() {
        return String.format("Korisnicko ime: %s, Ime: %s, Prezime: %s, Pol: %s, Telefon: %s, Adresa: %s, Lozinka: %s, Obrisan: %b", korisnickoIme,ime,prezime,pol,telefon,adresa,lozinka, obrisan);
    }
    public int getId() {
        return id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isObrisan() {
        return this.obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}
