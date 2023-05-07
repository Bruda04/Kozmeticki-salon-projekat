package entity;

import java.util.ArrayList;

public class TipTretmana {
    private final int id;
    private String naziv;
    private ArrayList<Integer> skupTipovaUsluga = new ArrayList<>();

    public TipTretmana(int id, String naziv, ArrayList<Integer> skupTipovaUsluga) {
        this.id = id;
        this.naziv = naziv;
        this.skupTipovaUsluga = skupTipovaUsluga;
    }

    public String toFileString() {
        String ret = this.id + "," + this.naziv + ",";
        StringBuilder sb = new StringBuilder();
        for(Integer i : this.skupTipovaUsluga) {
            sb.append("|").append(i);
        }
        if (sb.length() == 0) {
            return ret;
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

    public ArrayList<Integer> getSkupTipovaUsluga() {
        return skupTipovaUsluga;
    }

    public void setSkupTipovaUsluga(ArrayList<Integer> skupTipovaUsluga) {
        this.skupTipovaUsluga = skupTipovaUsluga;
    }
}
