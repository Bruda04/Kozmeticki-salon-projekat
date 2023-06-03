package manage;

import entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controler {
    private ManageGlobal manager;
    public Controler() {}
    public Controler(ManageGlobal manager){
        this.manager = manager;
//        manager.loadData();
    }

    public Korisnik prijava(String korisnickoIme, String lozinka) {
        Klijent k = manager.getKlijentMngr().findByKorisnickoIme(korisnickoIme);
        if (k != null && k.getLozinka().equals(lozinka) && !k.isObrisan()) {
            return k;
        }
        Kozmeticar ko = manager.getKozmeticarMngr().findByKorisnickoIme(korisnickoIme);
        if (ko != null && ko.getLozinka().equals(lozinka) && !ko.isObrisan()) {
            return ko;
        }
        Recepcioner r = manager.getRecepcionerMngr().findByKorisnickoIme(korisnickoIme);
        if (r != null && r.getLozinka().equals(lozinka) && !r.isObrisan()) {
            return r;
        }
        Menadzer m = manager.getMenadzerMngr().findByKorisnickoIme(korisnickoIme);
        if (m != null && m.getLozinka().equals(lozinka) && !m.isObrisan()) {
            return m;
        }
        return null;
    }

    public void registrujKlijenta(String korisnickoIme,
                                  String ime,
                                  String prezime,
                                  String pol,
                                  String telefon,
                                  String adresa,
                                  String lozinka) {
        manager.getKlijentMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, false, 0.00);
    }

    public void registrujRecepcionera(String korisnickoIme,
                                      String ime,
                                      String prezime,
                                      String pol,
                                      String telefon,
                                      String adresa,
                                      String lozinka,
                                      int nivoStrucneSpreme) {
        manager.getRecepcionerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, 0, false, 0.00);

    }
    public void registrujMenadzera(String korisnickoIme,
                                      String ime,
                                      String prezime,
                                      String pol,
                                      String telefon,
                                      String adresa,
                                      String lozinka,
                                      int nivoStrucneSpreme) {
        manager.getMenadzerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, 0, false, 0.00);

    }

    public void registrujKozmeticara(String korisnickoIme,
                                     String ime,
                                     String prezime,
                                     String pol,
                                     String telefon,
                                     String adresa,
                                     String lozinka,
                                     int nivoStrucneSpreme,
                                     ArrayList<Integer> spisakTretmana) {
        manager.getKozmeticarMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, 0, false, 0.00, spisakTretmana);
    }

    public void obrisiKlijenta(int id) {
        manager.getKlijentMngr().deleteById(id);
    }

    public void obrisiRecepcionera(int id) {
        manager.getRecepcionerMngr().deleteById(id);
    }
    public void obrisiMenadzera(int id) {
        manager.getMenadzerMngr().deleteById(id);
    }

    public void obrisiKozmeticara(int id) {
        manager.getKozmeticarMngr().deleteById(id);
    }

    public void izmeniKlijenta(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, Boolean karticaLojalnosti, Double potroseno) {
        manager.getKlijentMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, karticaLojalnosti, potroseno);
    }

    public void izmeniRecepcionera(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        manager.getRecepcionerMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
    }
    public void izmeniMMenadzera(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        manager.getMenadzerMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
    }
    public void izmeniKozmeticara(int id,
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
                                  double plata,
                                  ArrayList<Integer> spisakTretmana) {
        manager.getKozmeticarMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata, spisakTretmana);
    }

    public void dodajUslugu(String nazivUsluge, int vremeTrajanja) {
        manager.getUslugaMngr().add(nazivUsluge, vremeTrajanja);
    }

    public void dodajTipTretmana(String naziv, ArrayList<Integer> skupTipovaUsluga) {
        manager.getTipTretmanaMngr().add(naziv, skupTipovaUsluga);
    }

    public void pridruziUsluguTipu(int idUsluge, int idTipa) {
        TipTretmana tt = manager.getTipTretmanaMngr().findById(idTipa);
        ArrayList<Integer> novaLista = tt.getSkupTipovaUsluga();
        novaLista.add(idUsluge);
        manager.getTipTretmanaMngr().update(idTipa, tt.getNaziv(), novaLista);
    }

    public void ukluniUsluguIzTipa(int idUsluge, int idTipa) {
        TipTretmana tt = manager.getTipTretmanaMngr().findById(idTipa);
        ArrayList<Integer> novaLista = tt.getSkupTipovaUsluga();
        novaLista.remove(idUsluge);
        manager.getTipTretmanaMngr().update(idTipa, tt.getNaziv(), novaLista);
    }

    public void premestiUslugu(int idUsluge, int idStarogTipa, int idNovogTipa) {
        ukluniUsluguIzTipa(idUsluge, idStarogTipa);
        pridruziUsluguTipu(idUsluge, idNovogTipa);
    }

    public void obrisiUslugu(int id) {
        manager.getUslugaMngr().deleteById(id);
        for (TipTretmana tt: manager.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
            if (tt.getSkupTipovaUsluga().contains(id)) {
                ukluniUsluguIzTipa(id, tt.getId());
                break;
            }
        }
    }

    public void obrisiTipTretmana(int id) {
        TipTretmana tipTretmana = manager.getTipTretmanaMngr().findById(id);
        for (int idUsluge: tipTretmana.getSkupTipovaUsluga()) {
            manager.getUslugaMngr().deleteById(idUsluge);
        }
        manager.getTipTretmanaMngr().deleteById(id);
    }

    public void izmeniUslugu(int id, String nazivUsluge, int vremeTrajanja) {
        manager.getUslugaMngr().update(id, nazivUsluge, vremeTrajanja);
    }

    public void zakaziTretman(LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaUsluge, double cena) {
        Klijent k = manager.getKlijentMngr().findById(idKlijenta);
        double cenaNew = cena;
        if(k.isKarticaLojalnosti()) {
            cenaNew = cena*0.9;
        }
        manager.getZakazanTretmanMngr().add(datum, vreme, idKlijenta, idKozmeticara, idTipaUsluge, cenaNew, StanjeZakazanogTretmana.ZAKAZAN);
        double novoStanjePotroseno = k.getPotroseno() + cenaNew;
        manager.getKlijentMngr().update(idKlijenta, k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), novoStanjePotroseno);
    }
    public void izmeniZakazanTretman(int id, LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaUsluge, double cena, StanjeZakazanogTretmana stanje) {
        manager.getZakazanTretmanMngr().update(id, datum, vreme, idKlijenta, idKozmeticara, idTipaUsluge, cena, stanje);
    }
//    public void obrisiZakazanTretman(int id) {
//        manager.getZakazanTretmanMngr().deleteById(id);
//    }
    public void otkaziTretmanKlijent(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), StanjeZakazanogTretmana.OTKAZAOKLIJENT);
        Klijent k = manager.getKlijentMngr().findById(zt.getIdKlijenta());
        double potrosenoNew = k.getPotroseno() - zt.getCena()*0.9;
        manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), potrosenoNew);
    }
    public void otkaziTretmanSalon(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), StanjeZakazanogTretmana.OTKAZAOSALON);
        Klijent k = manager.getKlijentMngr().findById(zt.getIdKlijenta());
        double potrosenoNew = k.getPotroseno() - zt.getCena();
        manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), potrosenoNew);
    }
    public void PropustenTretmanKlijent(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), StanjeZakazanogTretmana.NIJESEPOJAVIO);
    }
    public void izvrsiTretman(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), StanjeZakazanogTretmana.IZVRŠEN);
    }

    public void postaviPragZaKarticuLojalnosti(double prag) {
        for (Klijent k: manager.getKlijentMngr().getKlijentHashMap().values()) {
            if (k.getPotroseno() >= prag) {
                manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), true, k.getPotroseno());
            }
        }
    }

    public Boolean kozmeticarZnaUslugu(int idKozmeticara, int idUsluge) {
        return manager.getKozmeticarMngr().findById(idKozmeticara).getSpisakTretmana().contains(idUsluge);
    }

    public ArrayList<Kozmeticar> kozmeticariKojiZnajuUslugu(int idUsluge) {
        ArrayList<Kozmeticar> listaKozmeticara = new ArrayList<>();
        for (Kozmeticar k: manager.getKozmeticarMngr().getKozmeticarHashMap().values()) {
            if (kozmeticarZnaUslugu(k.getId(), idUsluge)) {
                listaKozmeticara.add(k);
            }
        }
        return  listaKozmeticara;
    }

    public ArrayList<Integer> zakazaniTretmaniKozmeticara(int idKozmeticara, LocalDate datum) {
        ArrayList<Integer> tretmani = new ArrayList<>();
        for (ZakazanTretman zt: manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
            if (zt.getIdKozmeticara() == idKozmeticara && zt.getDatum().equals(datum) && zt.getStanje().equals(StanjeZakazanogTretmana.ZAKAZAN)) {
                tretmani.add(zt.getId());
            }
        }
        return tretmani;
    }

    public ArrayList<Integer> zakazaniTretmaniKlijenta(int idKlijenta) {
        ArrayList<Integer> tretmani = new ArrayList<>();
        for (ZakazanTretman zt: manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
            if (zt.getIdKlijenta() == idKlijenta) {
                tretmani.add(zt.getId());
            }
        }
        return tretmani;
    }

    public ArrayList<LocalTime> kozmeticarSlobodan(int idKozmeticara, LocalDate datum) {
        ArrayList<LocalTime> listaTermina = new ArrayList<>();
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        int radnihSati = ks.getVremeZatvaranja().getHour() - ks.getVremeOtvaranja().getHour();
        for (int i = 0; i < radnihSati; i++) {
            LocalTime termin = ks.getVremeOtvaranja().plusHours(i);
            listaTermina.add(termin);
        }
        ArrayList<Integer>  listaZauzetosti = zakazaniTretmaniKozmeticara(idKozmeticara, datum);
        for (int idZakazanogTretmana: listaZauzetosti) {
            ZakazanTretman zakazaniTretmanTmp = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
            if (listaTermina.contains(zakazaniTretmanTmp.getVreme())){
                listaTermina.remove(zakazaniTretmanTmp.getVreme());
                int trajanjeUslugeTmp = manager.getUslugaMngr().findById(zakazaniTretmanTmp.getIdTipaUsluge()).getTrajanjeUsluge();
                if (trajanjeUslugeTmp > 60){
                    int dodatniSati = trajanjeUslugeTmp / 60;
                    for (int j = 1; j < dodatniSati; j++) {
                        LocalTime novoVreme = zakazaniTretmanTmp.getVreme();
                        listaTermina.remove(novoVreme.plusHours(j));
                    }
                }
            }
        }

        return listaTermina;
    }

    public void izmeniVremeOtvaranjaSalona(LocalTime vremeOtvaranja) {
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), vremeOtvaranja, ks.getVremeZatvaranja());
    }
    public void izmeniVremeZatvaranjaSalona(LocalTime vremeZatvaranja) {
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), vremeZatvaranja);
    }


}

