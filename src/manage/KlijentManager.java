package manage;

import entity.Klijent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class KlijentManager {
	private final String klijentFile;
	private final HashMap<Integer, Klijent> klijentHashMap = new HashMap<>();
	private int nextId = 1;

	public KlijentManager(String klijentFile) {
		super();
		this.klijentFile = klijentFile;
	}

	public HashMap<Integer, Klijent> getKlijentHashMap() {
		return klijentHashMap;
	}

	public void add(String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, Boolean karticaLojalnosti, Double potroseno) {
		this.klijentHashMap.put(nextId ,new Klijent(this.nextId, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka,karticaLojalnosti, potroseno));
		this.nextId++;
		this.saveData();
	}

	public Klijent findById(int id){
		return this.klijentHashMap.get(id);
	}

	public Klijent findByKorisnickoIme(String korisnickoIme){
		for (Klijent k : this.klijentHashMap.values()) {
			if (k.getKorisnickoIme().equals(korisnickoIme)) {
				return k;
			}
		}
		return null;
	}

	public void deleteById(int id){
		this.klijentHashMap.remove(id);
		this.saveData();
	}
	public void deleteByKorisnickoIme(String korisnickoIme){
		for (Klijent k : this.klijentHashMap.values()) {
			if (k.getKorisnickoIme().equals(korisnickoIme)) {
				this.klijentHashMap.remove(k.getId());
				break;
			}
		}
		this.saveData();
	}

	public void update(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, Boolean karticaLojalnosti, Double potroseno) {
		Klijent updatedKlijent = this.klijentHashMap.get(id);
		updatedKlijent.setKorisnickoIme(korisnickoIme);
		updatedKlijent.setIme(ime);
		updatedKlijent.setPrezime(prezime);
		updatedKlijent.setPol(pol);
		updatedKlijent.setTelefon(telefon);
		updatedKlijent.setAdresa(adresa);
		updatedKlijent.setLozinka(lozinka);
		updatedKlijent.setKarticaLojalnosti(karticaLojalnosti);
		updatedKlijent.setPotroseno(potroseno);
		this.klijentHashMap.replace(id, updatedKlijent);
		this.saveData();
	}

	public boolean loadData() {
		this.klijentHashMap.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.klijentFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");
				int id = Integer.parseInt(tokeni[0]);
				this.klijentHashMap.put(id ,new Klijent(id, tokeni[1], tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7],Boolean.parseBoolean(tokeni[9]), Double.parseDouble(tokeni[10])));
				if (Boolean.parseBoolean(tokeni[8])) {
					this.klijentHashMap.get(id).setObrisan(true);
				}
				this.nextId = ++id;
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean saveData() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.klijentFile, false));
			for (Klijent k : klijentHashMap.values()) {
				pw.println(k.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
