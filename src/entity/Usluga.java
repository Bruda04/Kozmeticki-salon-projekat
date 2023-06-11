package entity;

public class Usluga {
    private final int id;
    private String nazivUsluge;
    private int trajanjeUsluge;
    private boolean obrisan;


	public Usluga(int id, String nazivUsluge, int trajanjeUsluge) {
        this.id = id;
        this.nazivUsluge = nazivUsluge;
        this.trajanjeUsluge = trajanjeUsluge;
        this.obrisan = false;
    }

    public String toFileString(){
        return this.id + "," + this.nazivUsluge + "," + this.trajanjeUsluge + "," + this.obrisan;
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

    public boolean isObrisan() {
    	return obrisan;
    }
    
    public void setObrisan(boolean obrisan) {
    	this.obrisan = obrisan;
    }
}
