package entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class KozmetickiSalon {
    private final int id;
    private String naziv;
    private LocalTime vremeOtvaranja;
    private LocalTime vremeZatvaranja;
	private double stanje;
	private double pragBonus;
	private double bonusIznos;
	
    public KozmetickiSalon(int id, String naziv, LocalTime vremeOtvaranja, LocalTime vremeZatvaranja, double stanje, double pragBonus, double bonusIznos) {
        this.id = id;
        this.naziv = naziv;
        this.vremeOtvaranja = vremeOtvaranja;
        this.vremeZatvaranja = vremeZatvaranja;
        this.stanje = stanje;
        this.pragBonus = pragBonus;
        this.bonusIznos = bonusIznos;
    }
    
    public double izracunajPlatu(int nivoStrucneSpreme, int godineStaza, boolean bonus) {
    	if (bonus) {
    		return 1000.00*(nivoStrucneSpreme)+godineStaza*2000.00 + 50000.00 + this.bonusIznos;    		
    	} else {
    		return 1000.00*(nivoStrucneSpreme)+godineStaza*2000.00 + 50000.00;
    	}
    	
    	
    }

    public String toFileString(){
        return this.id + "," + this.naziv + "," + this.getVremeOtvaranjaFormatStr() + ","
                + this.getVremeZatvaranjaFormatStr() + "," + this.stanje + "," + this.pragBonus + "," + this.bonusIznos;
    }

    public String toString(){
        return String.format("Naziv: %s, Vreme otvaranja: %s, Vreme zatvaranja: %s, Stanje racuna: %.2f", this.naziv, this.vremeOtvaranja.toString(), this.vremeZatvaranja.toString(), this.stanje);
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        KozmetickiSalon other = (KozmetickiSalon) o;
        
        if(!naziv.equals(other.getNaziv())) {
        	return false;        	
        }
        if(!vremeOtvaranja.equals(other.getVremeOtvaranja())) {
            return false;
        }
        if(!vremeZatvaranja.equals(other.getVremeZatvaranja())) {
        	return false;        	
        }
        if(stanje != other.getStanje()) {
        	return false;        	
        }
        if(pragBonus != other.getPragBonus()) {
        	return false;        	
        }
        if(bonusIznos != other.getBonusIznos()) {
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

    public Double getStanje() {
    	return stanje;
    }
    
    public void setStanje(double stanje) {
    	this.stanje = stanje;
    }

	public double getPragBonus() {
		return pragBonus;
	}

	public void setPragBonus(double pragBonus) {
		this.pragBonus = pragBonus;
	}

	public double getBonusIznos() {
		return bonusIznos;
	}

	public void setBonusIznos(double bonusIznos) {
		this.bonusIznos = bonusIznos;
	}
    
}
