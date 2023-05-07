package entity;

public class Usluga {
    private final int id;
    private String nazivUsluge;
    private int trajanjeUsluge;

    public Usluga(int id, String nazivUsluge, int trajanjeUsluge) {
        this.id = id;
        this.nazivUsluge = nazivUsluge;
        this.trajanjeUsluge = trajanjeUsluge;
    }

    public String toFileString(){
        return this.id + "," + this.nazivUsluge + "," + this.trajanjeUsluge;
    }

    public int getId() {return id;}

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public int getTrajanjeUsluge() {
        return trajanjeUsluge;
    }

    public void setTrajanjeUsluge(int trajanjeUsluge) {
        this.trajanjeUsluge = trajanjeUsluge;
    }
}
