package entity;

public class TipTretmana {
    private final int id;
    private String nazivTretmana;
    private String opisTretmana;

    public TipTretmana(int id, String nazivTretmana, String opisTretmana) {
        this.id = id;
        this.nazivTretmana = nazivTretmana;
    }

    public String toFileString(){
        return this.id + "," + this.nazivTretmana + "," + this.opisTretmana;
    }

    public int getId() {return id;}

    public String getNazivTretmana() {
        return nazivTretmana;
    }

    public void setNazivTretmana(String nazivTretmana) {
        this.nazivTretmana = nazivTretmana;
    }

    public String getOpisTretmana() {
        return opisTretmana;
    }

    public void setOpisTretmana(String opisTretmana) {
        this.opisTretmana = opisTretmana;
    }
}
