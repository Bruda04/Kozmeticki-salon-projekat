package manage;

import entity.Recepcioner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class RecepcionerManager {
    private final String recepcionerFile;
    private final HashMap<Integer, Recepcioner> recepcionerHashMap = new HashMap<>();
    private int nextId = 1;

    public RecepcionerManager(String recepcionerFile) {
        super();
        this.recepcionerFile = recepcionerFile;
    }

    public HashMap<Integer, Recepcioner> getRecepcionerHashMap() {
        return recepcionerHashMap;
    }

    public void add(String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        this.recepcionerHashMap.put(nextId ,new Recepcioner(this.nextId, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka,nivoStrucneSpreme, godineStaza, bonus, plata));
        this.nextId++;
        this.saveData();
    }

    public Recepcioner findById(int id){
        return this.recepcionerHashMap.get(id);
    }
    
    public Recepcioner findByKorisnickoIme(String korisnickoIme){
        for (Recepcioner r : this.recepcionerHashMap.values()) {
            if (r.getKorisnickoIme().equals(korisnickoIme)) {
                return r;
            }
        }
        return null;
    }

    public void deleteById(int id){
    	this.recepcionerHashMap.get(id).setObrisan(true);
//        this.recepcionerHashMap.remove(id);
        this.saveData();
    }
    public void deleteByKorisnickoIme(String korisnickoIme){
        for (Recepcioner r : this.recepcionerHashMap.values()) {
            if (r.getKorisnickoIme().equals(korisnickoIme)) {
            	this.recepcionerHashMap.get(r.getId()).setObrisan(true);
//                this.recepcionerHashMap.remove(r.getId());
                break;
            }
        }
        this.saveData();
    }

    public void update(int id, String korisnickoIme, String ime, String prezime, String pol, String telefon, String adresa, String lozinka, int nivoStrucneSpreme, int godineStaza, boolean bonus, double plata) {
        Recepcioner updatedRecepcioner = this.recepcionerHashMap.get(id);
        updatedRecepcioner.setKorisnickoIme(korisnickoIme);
        updatedRecepcioner.setIme(ime);
        updatedRecepcioner.setPrezime(prezime);
        updatedRecepcioner.setPol(pol);
        updatedRecepcioner.setTelefon(telefon);
        updatedRecepcioner.setAdresa(adresa);
        updatedRecepcioner.setLozinka(lozinka);
        updatedRecepcioner.setNivoStrucneSpreme(nivoStrucneSpreme);
        updatedRecepcioner.setGodineStaza(godineStaza);
        updatedRecepcioner.setBonus(bonus);
        updatedRecepcioner.setPlata(plata);
        this.recepcionerHashMap.replace(id, updatedRecepcioner);
        this.saveData();
    }
    
    public boolean loadData() {
        this.recepcionerHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.recepcionerFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.recepcionerHashMap.put(id ,new Recepcioner(id, tokeni[1], tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7], Integer.parseInt(tokeni[9]), Integer.parseInt(tokeni[10]), Boolean.parseBoolean(tokeni[11]),Double.parseDouble(tokeni[12])));
                if (Boolean.parseBoolean(tokeni[8])) {
                    this.recepcionerHashMap.get(id).setObrisan(true);
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
            pw = new PrintWriter(new FileWriter(this.recepcionerFile, false));
            for (Recepcioner r : recepcionerHashMap.values()) {
                pw.println(r.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
