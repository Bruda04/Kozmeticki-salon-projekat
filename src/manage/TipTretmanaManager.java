package manage;
import entity.TipTretmana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class TipTretmanaManager {
    private final String tipTretmanaFile;
    private final HashMap<Integer, TipTretmana> tipoviTretmanaHashMap = new HashMap<>();
    private int nextId = 1;

    public TipTretmanaManager(String tipTretmanaFile) {
        super();
        this.tipTretmanaFile = tipTretmanaFile;
    }

    public HashMap<Integer, TipTretmana> getTipoviTretmanaHashMap() {
        return tipoviTretmanaHashMap;
    }

    public void add(String nazivTretmana, String opisTretmana) {
        this.tipoviTretmanaHashMap.put(nextId ,new TipTretmana(this.nextId, nazivTretmana, opisTretmana));
        this.nextId++;
        this.saveData();
    }

    public TipTretmana findById(int id){
        return this.tipoviTretmanaHashMap.get(id);
    }

    public TipTretmana findByNazivTipaTretmana(String nazivTretmana){
        for (TipTretmana tt : this.tipoviTretmanaHashMap.values()) {
            if (tt.getNazivTretmana().equals(nazivTretmana)) {
                return tt;
            }
        }
        return null;
    }

    public void deleteById(int id){
        this.tipoviTretmanaHashMap.remove(id);
        this.saveData();
    }

    public void deleteByNazivTipaTretmana(String nazivTretmana){
        for (TipTretmana tt : this.tipoviTretmanaHashMap.values()) {
            if (tt.getNazivTretmana().equals(nazivTretmana)) {
                this.tipoviTretmanaHashMap.remove(tt.getId());
                break;
            }
        }
        this.saveData();
    }
    public void update(int id, String nazivTretmana, String opisTretmana) {
        TipTretmana updatedTipTretmana = this.tipoviTretmanaHashMap.get(id);
        updatedTipTretmana.setNazivTretmana(nazivTretmana);
        updatedTipTretmana.setOpisTretmana(opisTretmana);
        this.tipoviTretmanaHashMap.replace(id, updatedTipTretmana);
        this.saveData();
    }

    public boolean loadData() {
        this.tipoviTretmanaHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.tipTretmanaFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.tipoviTretmanaHashMap.put(id ,new TipTretmana(id, tokeni[1], tokeni[2]));
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
            pw = new PrintWriter(new FileWriter(this.tipTretmanaFile, false));
            for (TipTretmana tt : tipoviTretmanaHashMap.values()) {
                pw.println(tt.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
