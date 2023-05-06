package entity;

public class Klijent extends Korisnik {
     private boolean karticaLojalnosti;
     private double potroseno;

     public Klijent(int id,String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, Double stanjeRacuna, boolean karticaLojalnosti, double potroseno){
          super(id, korisnickoIme,  ime,  prezime,  pol,  telefon,  adresa,  lozinka, stanjeRacuna);
          this.karticaLojalnosti = karticaLojalnosti;
          this.potroseno = potroseno;
     }

     public String toFileString(){
          return super.toFileString()+ "," + this.karticaLojalnosti + "," + this.potroseno;
     }

     public String toString(){
          return super.toString() + ", " + String.format("Kartica lojalnosti: %b, Potroseno sredstava: %f", this.karticaLojalnosti, this.potroseno);
     }

     public boolean isKarticaLojalnosti() {
          return karticaLojalnosti;
     }

     public void setKarticaLojalnosti(boolean karticaLojalnosti) {
          this.karticaLojalnosti = karticaLojalnosti;
     }

     public double getPotroseno() {
          return potroseno;
     }

     public void setPotroseno(double potroseno) {
          this.potroseno = potroseno;
     }
}
