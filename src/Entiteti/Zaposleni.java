package Entiteti;

public abstract class Zaposleni extends Korisnik{
    private int nivoStrucneSpreme;
    private int godineStaza;
    private int bonus;
    private int plata;

    Zaposleni() {

    }
    Zaposleni(String ime,
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

        super(ime, prezime, pol, telefon, adresa, korisnicko_ime, lozinka);
        this.nivoStrucneSpreme = nivoStrucneSpreme;
        this.godineStaza = godineStaza;
        this.bonus = bonus;
        this.plata = plata;
    }

}
