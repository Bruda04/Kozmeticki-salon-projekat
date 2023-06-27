package entity;

import java.util.ArrayList;

public class Kozmeticar extends Zaposleni{
    private ArrayList<Integer> spisakTretmana;

    public Kozmeticar(int id,
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
                    double plata,
                    ArrayList<Integer> spisakTretmana
    ) {
        super(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
        this.spisakTretmana = spisakTretmana;
    }


    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        for(Integer i : this.spisakTretmana) {
            sb.append("|").append(i);
        }
        if (sb.length() == 0) {
            return super.toFileString() + ",";
        }
        return super.toFileString() + "," + sb.toString().substring(1);
    }

    public void setSpisakTretmana(ArrayList<Integer> spisakTretmana) {
        this.spisakTretmana = spisakTretmana;
    }
    public ArrayList<Integer> getSpisakTretmana() {return spisakTretmana;}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Integer i : this.spisakTretmana) {
            sb.append(", ").append(i);
        }
        if (sb.length() == 0) {
            return super.toString() + ", Spisak tretmana koje zna: Ne zna nista";
        }
        return super.toString()+ ", " + String.format("Spisak tretmana koje zna: %s", sb.toString().substring(1));
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        if (!super.equals(o)) {
       	 return false;
        }

        Kozmeticar other = (Kozmeticar) o;
        if(!spisakTretmana.equals(other.getSpisakTretmana()))
            return false;

        return true;
    }
}
