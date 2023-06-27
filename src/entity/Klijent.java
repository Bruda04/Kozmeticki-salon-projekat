package entity;

public class Klijent extends Korisnik {
     private boolean karticaLojalnosti;
     private double potroseno;

     public Klijent(int id,String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, boolean karticaLojalnosti, double potroseno){
          super(id, korisnickoIme,  ime,  prezime,  pol,  telefon,  adresa,  lozinka);
          this.karticaLojalnosti = karticaLojalnosti;
          this.potroseno = potroseno;
     }

     public String toFileString(){
          return super.toFileString()+ "," + this.karticaLojalnosti + "," + this.potroseno;
     }

     public String toString(){
          return super.toString() + ", " + String.format("Kartica lojalnosti: %b, Potroseno sredstava: %.2f", this.karticaLojalnosti, this.potroseno);
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

         Klijent other = (Klijent) o;
         if(karticaLojalnosti != other.isKarticaLojalnosti())
             return false;
         if(potroseno != other.getPotroseno())
             return false;

         return true;
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
