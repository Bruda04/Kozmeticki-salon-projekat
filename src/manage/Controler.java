package manage;

import entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controler {
    private ManageGlobal manager;
    public Controler() {}
    public Controler(ManageGlobal manager){
        this.manager = manager;
        manager.loadData();
        if (manager.getKozmetickiSalonMngr().getkozmetickiSalonHashMap().size() == 0) {
        	manager.getKozmetickiSalonMngr().add("Bruda beauty", LocalTime.of(8, 0), LocalTime.of(22, 0), 0.00, 1500.00, 5000.00);
        }        
        if (manager.getCenovnikMngr().getCenovnikHashMapp().size() == 0) {
        	manager.getCenovnikMngr().add(new HashMap<Integer, Double>());
        }
        if (manager.getMenadzerMngr().getMenadzerHashMap().size() == 0) {
        	manager.getMenadzerMngr().add("", "Admin", "Admin", "null", "null", "null", "", 0, 0, false, 0);
        }
    }
    
    public Klijent pronadjiKlijenta(String korisnickoIme) {
    	return manager.getKlijentMngr().findByKorisnickoIme(korisnickoIme);
    }
    public Klijent pronadjiKlijenta(int id) {
    	return manager.getKlijentMngr().findById(id);
    }
    
    public Kozmeticar pronadjiKozmeticara(String korisnickoIme) {
    	return manager.getKozmeticarMngr().findByKorisnickoIme(korisnickoIme);
    }
    public Kozmeticar pronadjiKozmeticara(int id) {
    	return manager.getKozmeticarMngr().findById(id);
    }
    
    public Menadzer pronadjiMenadzera(String korisnickoIme) {
    	return manager.getMenadzerMngr().findByKorisnickoIme(korisnickoIme);
    }
    public Menadzer pronadjiMenadzera(int id) {
    	return manager.getMenadzerMngr().findById(id);
    }
    
    public Recepcioner pronadjiRecepcionera(String korisnickoIme) {
    	return manager.getRecepcionerMngr().findByKorisnickoIme(korisnickoIme);
    }
    public Recepcioner pronadjiRecepcionera(int id) {
    	return manager.getRecepcionerMngr().findById(id);
    }
    
    public Usluga pronadjiUslugu(String nazivUsluge) {
    	return manager.getUslugaMngr().findByNazivUsluge(nazivUsluge);
    }
    public Usluga pronadjiUslugu(int id) {
    	return manager.getUslugaMngr().findById(id);
    }
    
    public TipTretmana pronadjiTipTretmana(String nazivTipaTretmana) {
    	return manager.getTipTretmanaMngr().findByNazivTipaTretmana(nazivTipaTretmana);
    }
    public TipTretmana pronadjiTipTretmana(int id) {
    	return manager.getTipTretmanaMngr().findById(id);
    }
    
    public KozmetickiSalon pronadjiKozmetickiSalon() {
    	return manager.getKozmetickiSalonMngr().findById(1);
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
                                      int nivoStrucneSpreme,
                                      int godineStaza) {
    	KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
    	Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
        manager.getRecepcionerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata);

    }
    public void registrujMenadzera(String korisnickoIme,
                                      String ime,
                                      String prezime,
                                      String pol,
                                      String telefon,
                                      String adresa,
                                      String lozinka,
                                      int nivoStrucneSpreme,
                                      int godineStaza) {
    	KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
    	Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
        manager.getMenadzerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata);

    }

    public void registrujKozmeticara(String korisnickoIme,
                                     String ime,
                                     String prezime,
                                     String pol,
                                     String telefon,
                                     String adresa,
                                     String lozinka,
                                     int nivoStrucneSpreme,
                                     int godineStaza,
                                     ArrayList<Integer> spisakTretmana) {
    	KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
    	Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
        manager.getKozmeticarMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata, spisakTretmana);
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

    public void dodajUslugu(String nazivUsluge, int vremeTrajanja, double cena, int idTipaTretmna) {
    	Cenovnik cenovnik = manager.getCenovnikMngr().findById(1);
        manager.getUslugaMngr().add(nazivUsluge, vremeTrajanja);
        cenovnik.getCenovnikHasMap().put(pronadjiUslugu(nazivUsluge).getId(), cena);
        manager.getCenovnikMngr().update(cenovnik.getId(), cenovnik.getCenovnikHasMap());
        pridruziUsluguTipu(manager.getUslugaMngr().findByNazivUsluge(nazivUsluge).getId(), idTipaTretmna);
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

    public void zakaziTretman(LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaUsluge, int IdZakzivaca) {
    	Klijent k = manager.getKlijentMngr().findById(idKlijenta);
    	Double cena = manager.getCenovnikMngr().findById(1).getCenovnikHasMap().get(idTipaUsluge);
        double cenaNew = cena;
        if(k.isKarticaLojalnosti()) {
            cenaNew = cena*0.9;
        }
        manager.getZakazanTretmanMngr().add(datum, vreme, idKlijenta, idKozmeticara, idTipaUsluge, cenaNew, IdZakzivaca,StanjeZakazanogTretmana.ZAKAZAN);
        double novoStanjePotroseno = k.getPotroseno() + cenaNew;
        manager.getKlijentMngr().update(idKlijenta, k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), novoStanjePotroseno);
    }
    
    public void izmeniZakazanTretman(int id, LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaUsluge, double cena, int IdZakzivaca,StanjeZakazanogTretmana stanje) {
        manager.getZakazanTretmanMngr().update(id, datum, vreme, idKlijenta, idKozmeticara, idTipaUsluge, cena, IdZakzivaca, stanje);
    }
    
    public void obrisiZakazanTretman(int id) {
        manager.getZakazanTretmanMngr().deleteById(id);
    }
    
    public void otkaziTretmanKlijent(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), zt.getIdZakazivaca(),StanjeZakazanogTretmana.OTKAZAOKLIJENT);
        Klijent k = manager.getKlijentMngr().findById(zt.getIdKlijenta());
        
        double potrosenoNew = k.getPotroseno() - zt.getCena()*0.9;
        manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), potrosenoNew);
        
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), ks.getVremeZatvaranja(), ks.getStanje()+zt.getCena()*0.1, ks.getPragBonus(), ks.getBonusIznos());
    }
    
    public void otkaziTretmanSalon(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), zt.getIdZakazivaca(), StanjeZakazanogTretmana.OTKAZAOSALON);
        
        Klijent k = manager.getKlijentMngr().findById(zt.getIdKlijenta());
        double potrosenoNew = k.getPotroseno() - zt.getCena();
        
        manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), k.isKarticaLojalnosti(), potrosenoNew);
    }
    
    public void propustenTretmanKlijent(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), zt.getIdZakazivaca(), StanjeZakazanogTretmana.NIJESEPOJAVIO);
        
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), ks.getVremeZatvaranja(), ks.getStanje()+zt.getCena(), ks.getPragBonus(), ks.getBonusIznos());
        
        refaktorisiBonuse();
    }
    
    public void izvrsiTretman(int idZakazanogTretmana) {
        ZakazanTretman zt = manager.getZakazanTretmanMngr().findById(idZakazanogTretmana);
        manager.getZakazanTretmanMngr().update(idZakazanogTretmana, zt.getDatum(), zt.getVreme(), zt.getIdKlijenta(), zt.getIdKozmeticara(), zt.getIdTipaUsluge(), zt.getCena(), zt.getIdZakazivaca(), StanjeZakazanogTretmana.IZVRŠEN);
        
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), ks.getVremeZatvaranja(), ks.getStanje()+zt.getCena(), ks.getPragBonus(), ks.getBonusIznos());
        refaktorisiBonuse();
    }

    public void postaviPragZaKarticuLojalnosti(double prag) {
        for (Klijent k: manager.getKlijentMngr().getKlijentHashMap().values()) {
            if (k.getPotroseno() >= prag) {
                manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), true, k.getPotroseno());
            }
        }
    }

    public Boolean kozmeticarZnaUslugu(int idKozmeticara, int idUsluge) {
    	int idTipaTretmana = 0;
    	for (TipTretmana tt : manager.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
			if (tt.getSkupTipovaUsluga().contains(idUsluge)) {
				idTipaTretmana = tt.getId();
			}
		}
        if(manager.getKozmeticarMngr().findById(idKozmeticara).getSpisakTretmana().contains(idTipaTretmana)) {
        	return true;
        }
        return false;
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
    
    public ArrayList<ZakazanTretman> zakazaniTretmaniKozmeticara(int idKozmeticara) {
        ArrayList<ZakazanTretman> tretmani = new ArrayList<>();
        for (ZakazanTretman zt: manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
            if (zt.getIdKozmeticara() == idKozmeticara && zt.getStanje().equals(StanjeZakazanogTretmana.ZAKAZAN)) {
                tretmani.add(zt);
            }
        }
        return tretmani;
    }
    
    public ArrayList<ZakazanTretman> zakazaniTretmaniKozmeticara(int idKozmeticara, LocalDate datum) {
        ArrayList<ZakazanTretman> tretmani = new ArrayList<>();
        for (ZakazanTretman zt: manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
            if (zt.getIdKozmeticara() == idKozmeticara && zt.getDatum().equals(datum) && zt.getStanje().equals(StanjeZakazanogTretmana.ZAKAZAN)) {
                tretmani.add(zt);
            }
        }
        return tretmani;
    }

    public ArrayList<ZakazanTretman> tretmaniKlijenta(int idKlijenta) {
        ArrayList<ZakazanTretman> tretmani = new ArrayList<>();
        for (ZakazanTretman zt: manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
            if (zt.getIdKlijenta() == idKlijenta) {
                tretmani.add(zt);
            }
        }
        return tretmani;
    }
    
    public Kozmeticar dodeliKozmeticara(int idUsluge, LocalDate datum, LocalTime vreme) {
    	ArrayList<Kozmeticar> pogodniKozmeticari = new ArrayList<>();
    	ArrayList<Kozmeticar> kozmeticariKojiZnajuUslugu = kozmeticariKojiZnajuUslugu(idUsluge);
    	
    	for (Kozmeticar kozmeticar : kozmeticariKojiZnajuUslugu) {
			if (kozmeticarSlobodan(idUsluge, datum).contains(vreme)) {
				pogodniKozmeticari.add(kozmeticar);
			}
		}
    	
    	int idnex =((int) (Math.random()*10))%pogodniKozmeticari.size();
    	return pogodniKozmeticari.get(idnex);
    }
    
    public ArrayList<LocalTime> kozmeticarSlobodan(int idKozmeticara, LocalDate datum) {
        ArrayList<LocalTime> listaTermina = new ArrayList<>();
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        int radnihSati = ks.getVremeZatvaranja().getHour() - ks.getVremeOtvaranja().getHour();
        for (int i = 0; i < radnihSati; i++) {
            LocalTime termin = ks.getVremeOtvaranja().plusHours(i);
            listaTermina.add(termin);
        }
        ArrayList<ZakazanTretman>  listaZauzetosti = zakazaniTretmaniKozmeticara(idKozmeticara, datum);
        for (ZakazanTretman zakazanTretman: listaZauzetosti) {
            if (listaTermina.contains(zakazanTretman.getVreme())){
                listaTermina.remove(zakazanTretman.getVreme());
                int trajanjeUsluge = manager.getUslugaMngr().findById(zakazanTretman.getIdTipaUsluge()).getTrajanjeUsluge();
                if (trajanjeUsluge > 60){
                    int dodatniSati = trajanjeUsluge / 60;
                    for (int j = 1; j < dodatniSati; j++) {
                        LocalTime novoVreme = zakazanTretman.getVreme();
                        listaTermina.remove(novoVreme.plusHours(j));
                    }
                }
            }
        }

        return listaTermina;
    }

    public void izmeniVremeOtvaranjaSalona(LocalTime vremeOtvaranja) {
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), vremeOtvaranja, ks.getVremeZatvaranja(), ks.getStanje(), ks.getPragBonus(), ks.getBonusIznos());
    }
    public void izmeniVremeZatvaranjaSalona(LocalTime vremeZatvaranja) {
        KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
        manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), vremeZatvaranja, ks.getStanje(), ks.getPragBonus(), ks.getBonusIznos());
    }
    
    public double[] izvestajPrihodiRashodi(LocalDate datumPocetka, LocalDate datumKraja) {
    	double prihodi = 0.00;
    	double rashodi = 0.00;
    	double[] ret = new double[2];

    	for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
    		if (zt.getDatum().isAfter(datumPocetka) && zt.getDatum().isBefore(datumKraja)) {
    			if (zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN || zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO) {
    				prihodi += zt.getCena();
    			} else if (zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT) {
    				prihodi += zt.getCena()*0.1;
    			}				
			}
		}
    	HashMap<Integer, Zaposleni> zaposleni = new HashMap<>();
    	zaposleni.putAll(manager.getKozmeticarMngr().getKozmeticarHashMap());
    	zaposleni.putAll(manager.getMenadzerMngr().getMenadzerHashMap());
    	zaposleni.putAll(manager.getRecepcionerMngr().getRecepcionerHashMap());
    	
    	LocalDate datumTmp = datumPocetka;
    	while (datumTmp.isBefore(datumKraja)) {
    		if (datumTmp.getDayOfMonth() == 1) {
    			for (Zaposleni zap : zaposleni.values()) {
    				rashodi += zap.getPlata();
    			}
			}
    		datumTmp = datumTmp.plusDays(1);
		}
    	
    	ret[0] = prihodi;
    	ret[1] = rashodi;
    	return ret;
    }

    public ArrayList<Zaposleni> zaposleniSaBonusom() {
    	HashMap<Integer, Zaposleni> zaposleni = new HashMap<>();
    	zaposleni.putAll(manager.getKozmeticarMngr().getKozmeticarHashMap());
    	zaposleni.putAll(manager.getMenadzerMngr().getMenadzerHashMap());
    	zaposleni.putAll(manager.getRecepcionerMngr().getRecepcionerHashMap());
    	ArrayList<Zaposleni> listaZaposlenihSaBonusm = new ArrayList<>();
    	for (Zaposleni z : zaposleni.values()) {
			if (z.getBonus()) {
				listaZaposlenihSaBonusm.add(z);
			}
		}
		return listaZaposlenihSaBonusm;
    }
    
    public void izmeniPragBonusa(double pragBonusa, double bonusIznos) {
    	KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
    	manager.getKozmetickiSalonMngr().update(1, ks.getNaziv(), ks.getVremeOtvaranja(), ks.getVremeZatvaranja(), ks.getStanje(), pragBonusa, bonusIznos);
    	refaktorisiBonuse();
    }
    
    public void refaktorisiBonuse() {
    	HashMap<Integer, Double> zaposleniScore = new HashMap<>();
    	KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
    	
    	for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
    		if (zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN || zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO) {
    			if (!zaposleniScore.containsKey(zt.getIdKozmeticara())) {
    				zaposleniScore.put(zt.getIdKozmeticara(), zt.getCena());
    			} else {
    				zaposleniScore.put(zt.getIdKozmeticara(), zaposleniScore.get(zt.getIdKozmeticara()) + zt.getCena());
    			}				
			}
		}
    	
    	for (Map.Entry<Integer, Double> entry : zaposleniScore.entrySet()) {
			if (entry.getValue() >= ks.getPragBonus()) {
				Kozmeticar ktmp = manager.getKozmeticarMngr().findById(entry.getKey());
				Double newPlata = manager.getKozmetickiSalonMngr().findById(1).izracunajPlatu(ktmp.getNivoStrucneSpreme(), ktmp.getGodineStaza(), true);
				manager.getKozmeticarMngr().update(ktmp.getId(), ktmp.getKorisnickoIme(), ktmp.getIme(), ktmp.getPrezime(), ktmp.getPol(), ktmp.getTelefon(), ktmp.getAdresa(), ktmp.getLozinka(), ktmp.getNivoStrucneSpreme(), ktmp.getGodineStaza(), true, newPlata, ktmp.getSpisakTretmana());
			}
		}    	
    }
    
    public void izmeniNazivKozmetickogSalona(String naziv) {
    	KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
    	manager.getKozmetickiSalonMngr().update(1, naziv, ks.getVremeOtvaranja(), ks.getVremeZatvaranja(), ks.getStanje(), ks.getPragBonus(), ks.getBonusIznos());
    }
}

