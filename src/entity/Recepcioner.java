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
                    int nivoStrucneSpreme,
                    int godineStaza,
                    boolean bonus,
                    double plata
    ) {
        super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka,nivoStrucneSpreme, godineStaza, bonus, plata);
    }
    
}
