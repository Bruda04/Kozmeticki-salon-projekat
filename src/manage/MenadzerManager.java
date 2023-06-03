package manage;

import entity.Menadzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MenadzerManager {
    private final String menadzerFile;
    private final HashMap<Integer, Menadzer> menadzerHashMap = new HashMap<>();
    private int nextId = 1;
    public MenadzerManager(String menadzerFile) {
        super();
        this.menadzerFile = menadzerFile;
    }

    public HashMap<Integer, Menadzer> getMenadzerHashMap() {
        return menadzerHashMap;
    }

    public void add(String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka,int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        this.menadzerHashMap.put(nextId ,new Menadzer(this.nextId, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka ,nivoStrucneSpreme, godineStaza, bonus, plata));
        this.nextId++;
        this.saveData();
    }

    public Menadzer findById(int id){
        return this.menadzerHashMap.get(id);
    }
    public Menadzer findByKorisnickoIme(String korisnickoIme){
        for (Menadzer m : this.menadzerHashMap.values()) {
            if (m.getKorisnickoIme().equals(korisnickoIme)) {
                return m;
            }
        }
        return null;
    }

    public void deleteById(int id){
        this.menadzerHashMap.remove(id);
        this.saveData();
    }
    public void deleteByKorisnickoIme(String korisnickoIme){
        for (Menadzer m : this.menadzerHashMap.values()) {
            if (m.getKorisnickoIme().equals(korisnickoIme)) {
                this.menadzerHashMap.remove(m.getId());
                break;
            }
        }
        this.saveData();
    }

    public void update(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        Menadzer updatedMenadzer = this.menadzerHashMap.get(id);
        updatedMenadzer.setKorisnickoIme(korisnickoIme);
        updatedMenadzer.setIme(ime);
        updatedMenadzer.setPrezime(prezime);
        updatedMenadzer.setPol(pol);
        updatedMenadzer.setTelefon(telefon);
        updatedMenadzer.setAdresa(adresa);
        updatedMenadzer.setLozinka(lozinka);
        updatedMenadzer.setNivoStrucneSpreme(nivoStrucneSpreme);
        updatedMenadzer.setGodineStaza(godineStaza);
        updatedMenadzer.setBonus(bonus);
        updatedMenadzer.setPlata(plata);
        this.menadzerHashMap.replace(id, updatedMenadzer);
        this.saveData();
    }

    public boolean loadData() {
        this.menadzerHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.menadzerFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.menadzerHashMap.put(id ,new Menadzer(id, tokeni[1], tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7],Integer.parseInt(tokeni[9]), Integer.parseInt(tokeni[10]), Boolean.parseBoolean(tokeni[11]),Double.parseDouble(tokeni[12])));
                if (Boolean.parseBoolean(tokeni[8])) {
                    this.menadzerHashMap.get(id).setObrisan(true);
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
            pw = new PrintWriter(new FileWriter(this.menadzerFile, false));
            for (Menadzer m : menadzerHashMap.values()) {
                pw.println(m.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
