package entity;

public class Recepcioner extends Zaposleni{
    public Recepcioner(int id,
                    String ime,
                    String prezime,
                    String pol,
                    String telefon,
                    String adresa,
                    String korisnickoIme,
                    String lozinka,
                    Double stanjeRacuna,
                    int nivoStrucneSpreme,
                    int godineStaza,
                    boolean bonus,
                    double plata
    ) {
        super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, stanjeRacuna,nivoStrucneSpreme, godineStaza, bonus, plata);
    }
}
