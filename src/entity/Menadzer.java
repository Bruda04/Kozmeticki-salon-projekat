package entity;

public class Menadzer extends Zaposleni{
    public Menadzer(int id,
              String korisnickoIme,
              String ime,
              String prezime,
              String pol,
              String telefon,
              String adresa,
              String lozinka,
              int nivoStrucneSpreme,
              int godineStaza,
              boolean bonus,
              double plata
    ) {
        super(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
    }
}
