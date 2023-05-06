package entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class KozmetickiSalon {
    private final int id;
    private String naziv;
    private LocalTime vremeOtvaranja;
    private LocalTime vremeZatvaranja;
    private Double stanjeRacuna;

    public KozmetickiSalon(int id, String naziv, LocalTime vremeOtvaranja, LocalTime vremeZatvaranja, Double stanjeRacuna) {
        this.id = id;
        this.naziv = naziv;
        this.vremeOtvaranja = vremeOtvaranja;
        this.vremeZatvaranja = vremeZatvaranja;
        this.stanjeRacuna = stanjeRacuna;
    }

    public String toFileString(){
        return this.id + "," + this.naziv + "," + this.getVremeOtvaranjaFormatStr() + ","
                + this.getVremeZatvaranjaFormatStr() + "," + this.stanjeRacuna;
    }

    public String toString(){
        return String.format("Naziv: %s, Vreme otvaranja: %s, Vreme zatvaranja: %s, Stanje racuna: %.2f", this.naziv, this.vremeOtvaranja.toString(), this.vremeZatvaranja.toString(), this.stanjeRacuna);
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

    public LocalTime getVremeOtvaranja() {
        return vremeOtvaranja;
    }

    public String getVremeOtvaranjaFormatStr() {
        return this.vremeOtvaranja.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setVremeOtvaranja(LocalTime vremeOtvaranja) {
        this.vremeOtvaranja = vremeOtvaranja;
    }

    public void setVremeOtvaranjaByString(String vremeOtvaranja) {
        this.setVremeOtvaranja(LocalTime.parse(vremeOtvaranja, DateTimeFormatter.ofPattern("HH:mm")));
    }

    public LocalTime getVremeZatvaranja() {
        return vremeZatvaranja;
    }

    public String getVremeZatvaranjaFormatStr() {
        return this.vremeZatvaranja.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setVremeZatvaranja(LocalTime vremeZatvaranja) {
        this.vremeZatvaranja = vremeZatvaranja;
    }

    public void setVremeZatvaranjaByString(String vremeZatvaranja) {
        this.setVremeZatvaranja(LocalTime.parse(vremeZatvaranja, DateTimeFormatter.ofPattern("HH:mm")));
    }

    public Double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public void setStanjeRacuna(Double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }
}
