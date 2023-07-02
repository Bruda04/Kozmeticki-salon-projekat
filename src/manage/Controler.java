package manage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import entity.Cenovnik;
import entity.Klijent;
import entity.Korisnik;
import entity.Kozmeticar;
import entity.KozmetickiSalon;
import entity.Menadzer;
import entity.Recepcioner;
import entity.StanjeZakazanogTretmana;
import entity.TipTretmana;
import entity.Usluga;
import entity.ZakazanTretman;
import entity.Zaposleni;

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
			manager.getMenadzerMngr().add("admin", "Admin", "Admin", "null", "null", "null", "admin", 0, 0, false, 0);
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

	public ZakazanTretman pronadjiZakazanTretman(int idZakazanTretman) {
		return manager.getZakazanTretmanMngr().findById(idZakazanTretman);
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
	
	public boolean korisnickoImeZauzeto(String korisnickoIme) {
		boolean nadjen = false;
		
		if (pronadjiKlijenta(korisnickoIme) != null) {
			nadjen = true;
		}
		
		if (pronadjiKozmeticara(korisnickoIme) != null) {
			nadjen = true;
		}
		
		if (pronadjiMenadzera(korisnickoIme) != null) {
			nadjen = true;
		}
		
		if (pronadjiRecepcionera(korisnickoIme) != null) {
			nadjen = true;
		}
		
		return nadjen;
	}

	public void registrujKlijenta(String korisnickoIme,
			String ime,
			String prezime,
			String pol,
			String telefon,
			String adresa,
			String lozinka) {
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			manager.getKlijentMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, false, 0.00);			
		}
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
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
			Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
			manager.getRecepcionerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata);
		}
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
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
			Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
			manager.getMenadzerMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata);
		}
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
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			KozmetickiSalon salon = manager.getKozmetickiSalonMngr().findById(1);
			Double plata = salon.izracunajPlatu(nivoStrucneSpreme, godineStaza, false);
			manager.getKozmeticarMngr().add(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, false, plata, spisakTretmana);
		}
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
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			manager.getKlijentMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, karticaLojalnosti, potroseno);
		}
	}

	public void izmeniRecepcionera(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			manager.getRecepcionerMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
		}
	}
	public void izmeniMenadzera(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			manager.getMenadzerMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata);
		}
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
		if (korisnickoImeZauzeto(korisnickoIme)) {
			throw new IllegalArgumentException("Korisničko ime je zauzeto.");
		} else {
			manager.getKozmeticarMngr().update(id, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, godineStaza, bonus, plata, spisakTretmana);
		}
	}

	public void dodajUslugu(String nazivUsluge, int vremeTrajanja, double cena, int idTipaTretmna) {
		if (pronadjiUslugu(nazivUsluge) != null) {
			throw new IllegalArgumentException("Usluga pod tim nazivom već postoji.");
		} else {
			Cenovnik cenovnik = manager.getCenovnikMngr().findById(1);
			manager.getUslugaMngr().add(nazivUsluge, vremeTrajanja);
			cenovnik.getCenovnikHasMap().put(pronadjiUslugu(nazivUsluge).getId(), cena);
			manager.getCenovnikMngr().update(cenovnik.getId(), cenovnik.getCenovnikHasMap());
			pridruziUsluguTipu(manager.getUslugaMngr().findByNazivUsluge(nazivUsluge).getId(), idTipaTretmna);
		}
	}

	public void dodajTipTretmana(String naziv, ArrayList<Integer> skupTipovaUsluga) {
		if (pronadjiUslugu(naziv) != null) {
			throw new IllegalArgumentException("Tip tretmana pod tim nazivom već postoji.");
		} else {
			manager.getTipTretmanaMngr().add(naziv, skupTipovaUsluga);
		}
	}

	public void izmeniTipTretmana(int idTipaTretmana, String naziv, ArrayList<Integer> skupTipovaUsluga) {
		if (pronadjiUslugu(naziv) != null) {
			throw new IllegalArgumentException("Tip tretmana pod tim nazivom već postoji.");
		} else {
			manager.getTipTretmanaMngr().update(idTipaTretmana, naziv, skupTipovaUsluga);
		}
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
		novaLista.remove(novaLista.indexOf(idUsluge));
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

	public void izmeniUslugu(int id, String nazivUsluge, int vremeTrajanja, double cena) {
		if (pronadjiUslugu(nazivUsluge) != null) {
			throw new IllegalArgumentException("Tip tretmana pod tim nazivom već postoji.");
		} else {
			manager.getUslugaMngr().update(id, nazivUsluge, vremeTrajanja);
			Cenovnik cenovnik = manager.getCenovnikMngr().findById(1);
			cenovnik.getCenovnikHasMap().replace(pronadjiUslugu(id).getId(), cena);
			manager.getCenovnikMngr().update(cenovnik.getId(), cenovnik.getCenovnikHasMap());
		}
	}

	public double nadjiCenuUsluge(int idUsluge) {
		return manager.getCenovnikMngr().findById(1).getCenovnikHasMap().get(idUsluge);
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
			if (k.getPotroseno() >= prag && !k.isObrisan()) {
				manager.getKlijentMngr().update(k.getId(), k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(), k.getLozinka(), true, k.getPotroseno());
			}
		}
	}

	public Boolean kozmeticarZnaUslugu(int idKozmeticara, int idUsluge) {
		int idTipaTretmana = 0;
		for (TipTretmana tt : manager.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
			if (tt.getSkupTipovaUsluga().contains(idUsluge) && !tt.isObrisan()) {
				idTipaTretmana = tt.getId();
			}
		}
		if(manager.getKozmeticarMngr().findById(idKozmeticara).getSpisakTretmana().contains(idTipaTretmana) && !manager.getKozmeticarMngr().findById(idKozmeticara).isObrisan()) {
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
			if (kozmeticarSlobodan(kozmeticar.getId(), datum).contains(vreme)) {
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

	public ArrayList<LocalTime> radniSatiSalona() {
		ArrayList<LocalTime> listaTermina = new ArrayList<>();
		KozmetickiSalon ks = manager.getKozmetickiSalonMngr().findById(1);
		int radnihSati = ks.getVremeZatvaranja().getHour() - ks.getVremeOtvaranja().getHour();
		for (int i = 0; i < radnihSati; i++) {
			LocalTime termin = ks.getVremeOtvaranja().plusHours(i);
			listaTermina.add(termin);
		}
		return listaTermina;
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
		ArrayList<Zaposleni> zaposleni = new ArrayList<>();
		zaposleni.addAll(manager.getKozmeticarMngr().getKozmeticarHashMap().values());
		zaposleni.addAll(manager.getMenadzerMngr().getMenadzerHashMap().values());
		zaposleni.addAll(manager.getRecepcionerMngr().getRecepcionerHashMap().values());

		LocalDate datumTmp = datumPocetka;
		while (datumTmp.isBefore(datumKraja)) {
			if (datumTmp.getDayOfMonth() == 1) {
				for (Zaposleni zap : zaposleni) {
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
		ArrayList<Zaposleni> zaposleni = new ArrayList<>();
		zaposleni.addAll(manager.getKozmeticarMngr().getKozmeticarHashMap().values());
		zaposleni.addAll(manager.getMenadzerMngr().getMenadzerHashMap().values());
		zaposleni.addAll(manager.getRecepcionerMngr().getRecepcionerHashMap().values());
		ArrayList<Zaposleni> listaZaposlenihSaBonusm = new ArrayList<>();
		for (Zaposleni z : zaposleni) {
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

	public HashMap<Integer, Menadzer> sviMenadzeri() {
		HashMap<Integer, Menadzer> ret = new HashMap<>();
		for (Menadzer m : manager.getMenadzerMngr().getMenadzerHashMap().values()) {
			if (!m.isObrisan()) {
				ret.put(m.getId(), m);
			}
		}
		return ret;
	}

	public HashMap<Integer, Kozmeticar> sviKozmeticari() {
		HashMap<Integer, Kozmeticar> ret = new HashMap<>();
		for (Kozmeticar k : manager.getKozmeticarMngr().getKozmeticarHashMap().values()) {
			if (!k.isObrisan()) {
				ret.put(k.getId(), k);
			}
		}
		return ret;
	}

	public HashMap<Integer, TipTretmana> sviTipoviTretmana() {
		HashMap<Integer, TipTretmana> ret = new HashMap<>();
		for (TipTretmana tt : manager.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
			if (!tt.isObrisan()) {
				ret.put(tt.getId(), tt);
			}
		}
		return ret;
	}
	public HashMap<Integer, Recepcioner> sviRecepcioneri() {
		HashMap<Integer, Recepcioner> ret = new HashMap<>();
		for (Recepcioner r : manager.getRecepcionerMngr().getRecepcionerHashMap().values()) {
			if (!r.isObrisan()) {
				ret.put(r.getId(), r);
			}
		}
		return ret;
	}
	public HashMap<Integer, Klijent> sviKlijenti() {
		HashMap<Integer, Klijent> ret = new HashMap<>();
		for (Klijent k : manager.getKlijentMngr().getKlijentHashMap().values()) {
			if (!k.isObrisan()) {
				ret.put(k.getId(), k);
			}
		}
		return ret;	
	}
	public HashMap<Integer, Usluga> sveUsluge() {
		HashMap<Integer, Usluga> ret = new HashMap<>();
		for (Usluga u : manager.getUslugaMngr().getUslugeHashMap().values()) {
			if (!u.isObrisan()) {
				ret.put(u.getId(), u);
			}
		}
		return ret;	
	}

	public HashMap<Integer, ZakazanTretman> sviZakazaniTretmani() {
		return manager.getZakazanTretmanMngr().getZakazanTretmanHashMap();
	}

	public TipTretmana nadjiTipTretmanaUsluge(int id) {
		for (TipTretmana tt : manager.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
			if (!tt.isObrisan() && tt.getSkupTipovaUsluga().contains(id)) {
				return tt;
			}
		}
		return null;
	}

	public double[] izvestajBrojPrihodKozmeticari(int idKozmeticara, LocalDate datumPocetka, LocalDate datumKraja) {
		double broj = 0.00;
		double prihodi = 0.00;
		double[] ret = new double[2];

		for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
			if (zt.getDatum().isAfter(datumPocetka) && zt.getDatum().isBefore(datumKraja)) {
				if (zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN || zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO) {
					if (zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN) {
						broj += 1;
					}
					prihodi += zt.getCena();
				} else if (zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT) {
					prihodi += zt.getCena()*0.1;
				}				
			}
		}

		ret[0] = broj;
		ret[1] = prihodi;
		return ret;

	}

	public int[] izvestajPotvrdjenoOtkazano(LocalDate datumPocetka, LocalDate datumKraja) {
		int zakazano = 0;
		int izvrseno = 0;
		int otkazaoK = 0;
		int otkazaoS = 0;
		int nipo = 0;

		int[] ret = new int[5];

		for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
			if (zt.getDatum().isAfter(datumPocetka) && zt.getDatum().isBefore(datumKraja)) {
				if (zt.getStanje() == StanjeZakazanogTretmana.ZAKAZAN) {
					zakazano += 1;
				} else if(zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN){
					izvrseno += 1;
				} else if(zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT){
					otkazaoK += 1;
				} else if(zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOSALON){
					otkazaoS += 1;
				} else if(zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO){
					nipo += 1;
				}
			}
		}

		ret[0] = zakazano;
		ret[1] = izvrseno;
		ret[2] = otkazaoK;
		ret[3] = otkazaoS;
		ret[4] = nipo;
		return ret;
	}

	public HashMap<String, Object> izvestajUslugaStatistika(int idUsluge, LocalDate datumPocetka, LocalDate datumKraja) {
		HashMap<String, Object> ret =  new HashMap<>();
		Usluga u = manager.getUslugaMngr().findById(idUsluge);
		ret.put("id", u.getId());
		ret.put("naziv", u.getNazivUsluge());
		ret.put("trajanje", u.getTrajanjeUsluge());
		ret.put("tip", nadjiTipTretmanaUsluge(idUsluge).getNaziv());

		int zakazivanja = 0;
		double prihodi = 0.00;
		for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
			if (zt.getDatum().isAfter(datumPocetka) && zt.getDatum().isBefore(datumKraja) && zt.getIdTipaUsluge() == idUsluge) {
				zakazivanja += 1;
				if(zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN){
					prihodi += zt.getCena();
				} else if(zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT){
					prihodi += zt.getCena()*0.1;
				} else if(zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO){
					prihodi += zt.getCena();
				}
				//prihodi += zt.getCena();
			}
		}
		ret.put("zakazivanja", zakazivanja);
		ret.put("cena", nadjiCenuUsluge(idUsluge));
		ret.put("prihodi", prihodi);

		return ret;
	}
	
	public ArrayList<Klijent> izvestajPotencijalZaKarticuLojalnosti(Double prag) {
		ArrayList<Klijent> ret = new ArrayList<>();
		if (prag == null) {
			return ret;
		}
		for (Klijent k: manager.getKlijentMngr().getKlijentHashMap().values()) {
			if (k.getPotroseno() >= prag && !k.isObrisan()) {
				ret.add(k);
			}
		}
		return ret;
	}
	
	public HashMap<Kozmeticar, Double> dijagramUdeoKozmeticara() {
		HashMap<Kozmeticar, Double> ret = new HashMap<>();
		LocalDate kraj = LocalDate.now();
		LocalDate pocetak = kraj.minusDays(30);
		HashMap<Integer, Integer> calc = new HashMap<>();
		
		int ukupno = 0;
		for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
			if (zt.getDatum().isAfter(pocetak) && zt.getDatum().isBefore(kraj) && zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN) {
				if (calc.get(zt.getIdKozmeticara()) == null) {
					calc.put(zt.getIdKozmeticara(), 1);
				} else {
					calc.put(zt.getIdKozmeticara(), calc.get(zt.getIdKozmeticara()) + 1);
				}
				ukupno += 1;
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : calc.entrySet()) {
            ret.put(pronadjiKozmeticara(entry.getKey()), (double) (ukupno/entry.getValue()));
        }
		
		return ret;
	}
	
	public HashMap<StanjeZakazanogTretmana, Double> dijagramStatusTretmana() {
		HashMap<StanjeZakazanogTretmana, Double> ret = new HashMap<>();
		LocalDate kraj = LocalDate.now();
		LocalDate pocetak = kraj.minusDays(30);
		HashMap<StanjeZakazanogTretmana, Integer> calc = new HashMap<>();
		
		calc.put(StanjeZakazanogTretmana.ZAKAZAN, 0);
		calc.put(StanjeZakazanogTretmana.IZVRŠEN, 0);
		calc.put(StanjeZakazanogTretmana.OTKAZAOKLIJENT, 0);
		calc.put(StanjeZakazanogTretmana.OTKAZAOSALON, 0);
		calc.put(StanjeZakazanogTretmana.NIJESEPOJAVIO, 0);
		
		int ukupno = 0;
		for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
			if (zt.getDatum().isAfter(pocetak) && zt.getDatum().isBefore(kraj)) {
				if (zt.getStanje() == StanjeZakazanogTretmana.ZAKAZAN) {
					calc.put(zt.getStanje(), calc.get(zt.getStanje()) + 1);
				} else if(zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN){
					calc.put(zt.getStanje(), calc.get(zt.getStanje()) + 1);
				} else if(zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT){
					calc.put(zt.getStanje(), calc.get(zt.getStanje()) + 1);
				} else if(zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOSALON){
					calc.put(zt.getStanje(), calc.get(zt.getStanje()) + 1);
				} else if(zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO){
					calc.put(zt.getStanje(), calc.get(zt.getStanje()) + 1);
				}
				ukupno += 1;
			}
		}
		
		for (Map.Entry<StanjeZakazanogTretmana, Integer> entry : calc.entrySet()) {
			if (entry.getValue() != 0) {
				ret.put(entry.getKey(), (double) (ukupno/entry.getValue()));				
			} else {
				ret.put(entry.getKey(), (double) entry.getValue());
			}
        }
		
		return ret;
	}
	
	public TreeMap<Date, Double> dijagramPrihodiGodina(int idTip){
		TreeMap<Date, Double> ret = new TreeMap<>();
		Function<LocalDate, Date> lDC = ld -> Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 LocalDate sad = LocalDate.now();
		 LocalDate pre = sad.minusYears(1).withDayOfMonth(1);
		
		for (int i = 1; i <= 12; i++) {
			double vrednost = 0.00;
			LocalDate datumStart = pre.plusMonths(i);
			
			for (ZakazanTretman zt : manager.getZakazanTretmanMngr().getZakazanTretmanHashMap().values()) {
				if (zt.getDatum().isAfter(datumStart) && zt.getDatum().isBefore(datumStart.plusMonths(1))) {
					if (idTip == nadjiTipTretmanaUsluge(zt.getIdTipaUsluge()).getId() || idTip == 0) {
						if (zt.getStanje() == StanjeZakazanogTretmana.IZVRŠEN || zt.getStanje() == StanjeZakazanogTretmana.NIJESEPOJAVIO) {
							vrednost += zt.getCena();
						} else if (zt.getStanje() == StanjeZakazanogTretmana.OTKAZAOKLIJENT) {
							vrednost += zt.getCena()*0.1;
						}										
					}	
				}
			}
			
			ret.put(lDC.apply(datumStart), vrednost);
		}
		
		return ret;
	}
}

