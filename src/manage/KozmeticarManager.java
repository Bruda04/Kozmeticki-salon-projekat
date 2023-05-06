package manage;

import entity.Kozmeticar;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class KozmeticarManager {
    private final String kozmeticarFile;
    private final HashMap<Integer, Kozmeticar> kozmeticarHashMap = new HashMap<>();
    private int nextId = 1;
    public KozmeticarManager(String kozmeticarFile) {
        super();
        this.kozmeticarFile = kozmeticarFile;
    }

    public HashMap<Integer, Kozmeticar> getKozmeticarHashMap() {
        return kozmeticarHashMap;
    }

    public void add(String korisnickoIme,
                    String ime,
                    String prezime,
                    String pol,
                    String telefon,
                    String adresa,
                    String lozinka,
                    Double stanjeRacuna,
                    int nivoStrucneSpreme,
                    int godineStaza,
                    boolean bonus,
                    double plata,
                    ArrayList<Integer> spisakTretmana) {
        this.kozmeticarHashMap.put(nextId ,new Kozmeticar(this.nextId, korisnickoIme, ime, prezime, pol, telefon, adresa,
                lozinka, stanjeRacuna,nivoStrucneSpreme, godineStaza, bonus, plata, spisakTretmana));
        this.nextId++;
        this.saveData();
    }

    public Kozmeticar findById(int id){
        return this.kozmeticarHashMap.get(id);
    }

    public Kozmeticar findByKorisnickoIme(String korisnickoIme){
        for (Kozmeticar k : this.kozmeticarHashMap.values()) {
            if (k.getKorisnickoIme().equals(korisnickoIme)) {
                return k;
            }
        }
        return null;
    }

    public void deleteById(int id){
        this.kozmeticarHashMap.remove(id);
        this.saveData();
    }
    public void deleteByKorisnickoIme(String korisnickoIme){
        for (Kozmeticar k : this.kozmeticarHashMap.values()) {
            if (k.getKorisnickoIme().equals(korisnickoIme)) {
                this.kozmeticarHashMap.remove(k.getId());
                break;
            }
        }
        this.saveData();
    }

    public void update(int id,
            String korisnickoIme,
            String ime,
            String prezime,
            String pol,
            String telefon,
            String adresa,
            String lozinka,
            Double stanjeRacuna,
            int nivoStrucneSpreme,
            int godineStaza,
            boolean bonus,
            double plata,
    ArrayList<Integer> spisakTretmana) {
        Kozmeticar updatedKozmeticar = this.kozmeticarHashMap.get(id);
        updatedKozmeticar.setKorisnickoIme(korisnickoIme);
        updatedKozmeticar.setIme(ime);
        updatedKozmeticar.setPrezime(prezime);
        updatedKozmeticar.setPol(pol);
        updatedKozmeticar.setTelefon(telefon);
        updatedKozmeticar.setAdresa(adresa);
        updatedKozmeticar.setLozinka(lozinka);
        updatedKozmeticar.setStanjeRacuna(stanjeRacuna);
        updatedKozmeticar.setNivoStrucneSpreme(nivoStrucneSpreme);
        updatedKozmeticar.setGodineStaza(godineStaza);
        updatedKozmeticar.setBonus(bonus);
        updatedKozmeticar.setPlata(plata);
        updatedKozmeticar.setSpisakTretmana(spisakTretmana);
        this.kozmeticarHashMap.replace(id, updatedKozmeticar);
        this.saveData();
    }

    public boolean loadData() {
        this.kozmeticarHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.kozmeticarFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                ArrayList<Integer> listaTretmana = new ArrayList<>();
                if (tokeni.length == 14) {
                    for (String i : tokeni[13].split("\\|")) {
                        listaTretmana.add(Integer.parseInt(i));
                    }
                }
                this.kozmeticarHashMap.put(id ,new Kozmeticar(id, tokeni[1], tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7], Double.parseDouble(tokeni[8]),
                        Integer.parseInt(tokeni[9]), Integer.parseInt(tokeni[10]), Boolean.parseBoolean(tokeni[11]), Double.parseDouble(tokeni[12]), listaTretmana));
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
            pw = new PrintWriter(new FileWriter(this.kozmeticarFile, false));
            for (Kozmeticar k : kozmeticarHashMap.values()) {
                pw.println(k.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
