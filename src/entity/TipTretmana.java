package entity;

import java.util.ArrayList;

public class TipTretmana {
    private final int id;
    private String naziv;
    private boolean obrisan;
    private ArrayList<Integer> skupTipovaUsluga = new ArrayList<>();

    public TipTretmana(int id, String naziv, ArrayList<Integer> skupTipovaUsluga) {
        this.id = id;
        this.naziv = naziv;
        this.skupTipovaUsluga = skupTipovaUsluga;
        this.setObrisan(false);
    }

    public String toFileString() {
        String ret = this.id + "," + this.naziv + "," + this.obrisan + ",";
        StringBuilder sb = new StringBuilder();
        for(Integer i : this.skupTipovaUsluga) {
            sb.append("|").append(i);
        }
        if (sb.length() == 0) {
            return ret;
        }
        return ret + sb.toString().substring(1);
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        
        TipTretmana other = (TipTretmana) o;
        
        if(!naziv.equals(other.getNaziv())) {
        	return false;        	
        }
        if(!skupTipovaUsluga.equals(other.getSkupTipovaUsluga())) {
        	return false;        	
        }

        return true;
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

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
}
