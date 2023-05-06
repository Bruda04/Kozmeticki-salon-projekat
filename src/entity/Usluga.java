package entity;

import java.util.ArrayList;
import java.util.Map;

public class Usluga {
    private int id;
    private String naziv;
    private ArrayList<Integer> skupTipovaTretmana = new ArrayList<>();

    public Usluga(int id, String naziv, ArrayList<Integer> skupTipovaTretmana) {
        this.id = id;
        this.naziv = naziv;
        this.skupTipovaTretmana = skupTipovaTretmana;
    }

    public String toFileString() {
        String ret = this.id + "," + this.naziv + ",";
        StringBuilder sb = new StringBuilder();
        for(Integer i : this.skupTipovaTretmana) {
            sb.append("|" + i);
        }
        return ret + sb.toString().substring(1);
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<Integer> getSkupTipovaTretmana() {
        return skupTipovaTretmana;
    }

    public void setSkupTipovaTretmana(ArrayList<Integer> skupTipovaTretmana) {
        this.skupTipovaTretmana = skupTipovaTretmana;
    }
}
