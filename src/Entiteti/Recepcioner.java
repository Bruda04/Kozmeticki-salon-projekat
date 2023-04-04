package Entiteti;

public class Recepcioner extends Zaposleni{
    public Recepcioner(String ime,
                    String prezime,
                    String pol,
                    String telefon,
                    String adresa,
                    String korisnicko_ime,
                    String lozinka,
                    int nivoStrucneSpreme,
                    int godineStaza,
                    int bonus,
                    int plata
    ) {
        super(ime, prezime, pol, telefon, adresa, korisnicko_ime, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
    }
}
