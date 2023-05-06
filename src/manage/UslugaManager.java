package manage;
import entity.Usluga;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UslugaManager {
    private final String uslugaFile;
    private final HashMap<Integer, Usluga> uslugeHashMap = new HashMap<>();
    private int nextId = 1;


    public UslugaManager(String uslugaFile) {
        super();
        this.uslugaFile = uslugaFile;
    }
    public void add(String naziv, ArrayList<Integer> skupTipovaTretmana) {
        this.uslugeHashMap.put(nextId ,new Usluga(this.nextId, naziv, skupTipovaTretmana));
        this.nextId++;
        this.saveData();
    }

    public Usluga findById(int id){
        return this.uslugeHashMap.get(id);
    }

    public void deleteById(int id){
        this.uslugeHashMap.remove(id);
        this.saveData();
    }

    public void update(int id, String naziv, ArrayList<Integer> skupTipovaTretmana) {
        Usluga updatedUsluga = this.uslugeHashMap.get(id);
        updatedUsluga.setNaziv(naziv);
        updatedUsluga.setSkupTipovaTretmana(skupTipovaTretmana);
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
                ArrayList<Integer> listaTretmana = new ArrayList<>();
                for (String i: tokeni[2].split("\\|")) {
                    listaTretmana.add(Integer.parseInt(i));
                }
                this.uslugeHashMap.put(id ,new Usluga(id, tokeni[1], listaTretmana));
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
