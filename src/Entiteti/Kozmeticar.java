package Entiteti;

import java.util.ArrayList;

public class Kozmeticar extends Zaposleni{
    private ArrayList<String> spisakTretmana;
    public Kozmeticar(String ime,
                    String prezime,
                    String pol,
                    String telefon,
                    String adresa,
                    String korisnicko_ime,
                    String lozinka,
                    int nivoStrucneSpreme,
                    int godineStaza,
                    int bonus,
                    int plata,
                      ArrayList<String> spisakTretmana
    ) {
        super(ime, prezime, pol, telefon, adresa, korisnicko_ime, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
    }
}
