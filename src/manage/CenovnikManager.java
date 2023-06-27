package manage;

import entity.Cenovnik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class CenovnikManager {
    private final String cenovniciFile;
    private final HashMap<Integer, Cenovnik> cenovniciHashMap = new HashMap<>();
    private int nextId = 1;

    public CenovnikManager(String cenovniciFile) {
        super();
        this.cenovniciFile = cenovniciFile;
    }

    public HashMap<Integer, Cenovnik> getCenovnikHashMapp() {
        return cenovniciHashMap;
    }

    public void add(HashMap<Integer, Double> cenovnikHashMap) {
        this.cenovniciHashMap.put(nextId ,new Cenovnik(this.nextId, cenovnikHashMap));
        this.nextId++;
        this.saveData();
    }

    public Cenovnik findById(int id){
        return this.cenovniciHashMap.get(id);
    }


    public void deleteById(int id){
        this.cenovniciHashMap.remove(id);
        this.saveData();
    }
    public void update(int id, HashMap<Integer, Double> cenovnikHashMap) {
        Cenovnik updatedCenovnik = this.cenovniciHashMap.get(id);
        updatedCenovnik.setCenovnikHasMap(cenovnikHashMap);
        this.cenovniciHashMap.replace(id, updatedCenovnik);
        this.saveData();
    }

    public boolean loadData() {
        this.cenovniciHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.cenovniciFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                HashMap<Integer, Double> ceneHashMap = new HashMap<>();
                for (int i = 1; i < tokeni.length; i++) {
                    String[] podTokeni = tokeni[i].split("=");
                    ceneHashMap.put(Integer.parseInt(podTokeni[0]), Double.parseDouble(podTokeni[1]));
                }
                this.cenovniciHashMap.put(id ,new Cenovnik(id, ceneHashMap));
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
            pw = new PrintWriter(new FileWriter(this.cenovniciFile, false));
            for (Cenovnik c : cenovniciHashMap.values()) {
                pw.println(c.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        
        CenovnikManager other = (CenovnikManager) o;
        if (!this.cenovniciHashMap.equals(other.getCenovnikHashMapp())) {
			return false;
		}
        
        return true;
    }
}
