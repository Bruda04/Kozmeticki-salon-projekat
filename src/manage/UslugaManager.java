package manage;
import entity.Usluga;

import java.io.*;
import java.util.HashMap;

public class UslugaManager {
    private final String uslugaFile;
    private final HashMap<Integer, Usluga> uslugeHashMap = new HashMap<>();
    private int nextId = 1;

    public UslugaManager(String uslugaFile) {
        super();
        this.uslugaFile = uslugaFile;
    }

    public HashMap<Integer, Usluga> getUslugeHashMap() {
        return uslugeHashMap;
    }

    public void add(String nazivUsluge, int vremeTrajanja) {
        this.uslugeHashMap.put(nextId ,new Usluga(this.nextId, nazivUsluge, vremeTrajanja));
        this.nextId++;
        this.saveData();
    }

    public Usluga findById(int id){
        return this.uslugeHashMap.get(id);
    }

    public Usluga findByNazivUsluge(String nazivUsluge){
        for (Usluga u : this.uslugeHashMap.values()) {
            if (u.getNazivUsluge().equals(nazivUsluge)) {
                return u;
            }
        }
        return null;
    }

    public void deleteById(int id){
        this.uslugeHashMap.remove(id);
        this.saveData();
    }

    public void deleteByNazivUsluge(String nazivUsluge){
        for (Usluga u : this.uslugeHashMap.values()) {
            if (u.getNazivUsluge().equals(nazivUsluge)) {
                this.uslugeHashMap.remove(u.getId());
                break;
            }
        }
        this.saveData();
    }

    public void update(int id, String nazivUsluge, int vremeTrajanja) {
        Usluga updatedUsluga = this.uslugeHashMap.get(id);
        updatedUsluga.setNazivUsluge(nazivUsluge);
        updatedUsluga.setTrajanjeUsluge(vremeTrajanja);
        this.uslugeHashMap.replace(id, updatedUsluga);
        this.saveData();
    }

    public boolean loadData() {
        this.uslugeHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.uslugaFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.uslugeHashMap.put(id ,new Usluga(id, tokeni[1], Integer.parseInt(tokeni[2])));
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
            pw = new PrintWriter(new FileWriter(this.uslugaFile, false));
            for (Usluga u : uslugeHashMap.values()) {
                pw.println(u.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
