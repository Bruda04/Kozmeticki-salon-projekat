package entity;

public abstract class Zaposleni extends Korisnik{
    private int nivoStrucneSpreme;
    private int godineStaza;
    private boolean bonus;
    private double plata;

    Zaposleni() {

    }
    Zaposleni(int id,
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
              double plata
              ) {

        super(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka);
        this.nivoStrucneSpreme = nivoStrucneSpreme;
        this.godineStaza = godineStaza;
        this.bonus = bonus;
        this.plata = plata;
    }

    public String toFileString() {
        return super.toFileString()+","+this.nivoStrucneSpreme+"," +this.godineStaza+","+this.bonus+","+this.plata;
    }

    public String toString() {
        return super.toString() + ", " + String.format("Nivo strucne spreme: %d, Godine staza: %d, Bonus: %b, Plata: %.2f", this.nivoStrucneSpreme,this.godineStaza,this.bonus,this.plata);
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

        Zaposleni other = (Zaposleni) o;
        if(nivoStrucneSpreme != other.getNivoStrucneSpreme())
            return false;
        if(godineStaza != other.getGodineStaza())
            return false;
        if(bonus != other.getBonus())
            return false;
        if(plata != other.getPlata())
            return false;

        return true;
    }
    
    public int getNivoStrucneSpreme() {
        return nivoStrucneSpreme;
    }

    public void setNivoStrucneSpreme(int nivoStrucneSpreme) {
        this.nivoStrucneSpreme = nivoStrucneSpreme;
    }

    public int getGodineStaza() {
        return godineStaza;
    }

    public void setGodineStaza(int godineStaza) {
        this.godineStaza = godineStaza;
    }

    public boolean getBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }
}
