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
              Double stanjeRacuna,
              int nivoStrucneSpreme,
              int godineStaza,
              boolean bonus,
              double plata
    ) {
        super(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, stanjeRacuna, nivoStrucneSpreme, godineStaza, bonus, plata);
    }

}
