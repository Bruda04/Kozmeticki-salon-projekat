package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ZakazanTretman {
    private final int id;
    private LocalDate datum;
    private LocalTime vreme;
    private int idKlijenta;
    private int idKozmeticara;
    private int idTipaUsluge;
    private double cena;
    private int idZakazaivaca;
    private StanjeZakazanogTretmana stanje;

    public ZakazanTretman(int id, LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaUsluge, double cena, int idZakazivaca, StanjeZakazanogTretmana stanje) {
        this.id = id;
        this.datum = datum;
        this.vreme = vreme;
        this.idKlijenta = idKlijenta;
        this.idKozmeticara = idKozmeticara;
        this.idTipaUsluge = idTipaUsluge;
        this.cena = cena;
        this.idZakazaivaca = idZakazivaca;
        this.stanje = stanje;
    }

    public String toFileString(){
        return this.id + "," + this.datum.toString() + "," +
                this.getVremeFormatStr() + "," + this.idKlijenta + "," +
                this.idKozmeticara + "," + this.idTipaUsluge + "," +
                this.cena +"," + this.idZakazaivaca + "," + this.stanje.toString();
    }
    

    public int getId() {return id;}

    public LocalDate getDatum() {return datum;}

    public void setDatum(LocalDate datum) {this.datum = datum;}

    public LocalTime getVreme() {return vreme;}
    public String getVremeFormatStr() {
        return this.vreme.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setVreme(LocalTime vreme) {this.vreme = vreme.withSecond(0);}
    
    public void setVremeByString(String vreme) {
        this.setVreme(LocalTime.parse(vreme, DateTimeFormatter.ofPattern("HH:mm")));
    }
    public int getIdKlijenta() {return idKlijenta;}

    public void setIdKlijenta(int idKlijenta) {this.idKlijenta = idKlijenta;}

    public int getIdKozmeticara() {return idKozmeticara;}

    public void setIdKozmeticara(int idKozmeticara) {this.idKozmeticara = idKozmeticara;}

    public int getIdTipaUsluge() {return idTipaUsluge;}

    public void setIdTipaUsluge(int idTipaTretmana) {this.idTipaUsluge = idTipaTretmana;}

    public double getCena() {return cena;}

    public void setCena(double cena) {this.cena = cena;}

    public StanjeZakazanogTretmana getStanje() {return stanje;}
    public void setStanje(StanjeZakazanogTretmana stanje) {this.stanje = stanje;}
    
    public int getIdZakazivaca() {
    	return this.idZakazaivaca;
    }
    public void setIdZakazivaca(int idZakazivaca) {
    	this.idZakazaivaca = idZakazivaca;
    }
}
