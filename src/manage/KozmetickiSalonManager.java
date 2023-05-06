package manage;

import entity.KozmetickiSalon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class KozmetickiSalonManager {
    private final String kozmetickiSalonFile;
    private final HashMap<Integer, KozmetickiSalon> kozmetickiSalonHashMap = new HashMap<>();
    private int nextId = 1;

    public KozmetickiSalonManager(String kozmetickiSalonFile) {
        super();
        this.kozmetickiSalonFile = kozmetickiSalonFile;
    }

    public void add(String naziv, LocalTime vremeOtvaranja, LocalTime vremeZatvaranja, Double stanjeRacuna) {
        this.kozmetickiSalonHashMap.put(nextId ,new KozmetickiSalon(this.nextId, naziv, vremeOtvaranja, vremeZatvaranja, stanjeRacuna));
        this.nextId++;
        this.saveData();
    }

    public KozmetickiSalon findById(int id){
        return this.kozmetickiSalonHashMap.get(id);
    }

    public void deleteById(int id){
        this.kozmetickiSalonHashMap.remove(id);
        this.saveData();
    }

    public void update(int id, String naziv, LocalTime vremeOtvaranja, LocalTime vremeZatvaranja, Double stanjeRacuna) {
        KozmetickiSalon updatedKozmetickiSalon = this.kozmetickiSalonHashMap.get(id);
        updatedKozmetickiSalon.setNaziv(naziv);
        updatedKozmetickiSalon.setVremeOtvaranja(vremeOtvaranja);
        updatedKozmetickiSalon.setVremeZatvaranja(vremeZatvaranja);
        updatedKozmetickiSalon.setStanjeRacuna(stanjeRacuna);
        this.kozmetickiSalonHashMap.replace(id, updatedKozmetickiSalon);
        this.saveData();
    }

    public HashMap<Integer, KozmetickiSalon> getkozmetickiSalonHashMap() {
        return kozmetickiSalonHashMap;
    }

    public boolean loadData() {
        this.kozmetickiSalonHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.kozmetickiSalonFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.kozmetickiSalonHashMap.put(id ,new KozmetickiSalon(id, tokeni[1], LocalTime.parse(tokeni[2], DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse(tokeni[3], DateTimeFormatter.ofPattern("HH:mm")), Double.parseDouble(tokeni[4])));
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
            pw = new PrintWriter(new FileWriter(this.kozmetickiSalonFile, false));
            for (KozmetickiSalon ks : kozmetickiSalonHashMap.values()) {
                pw.println(ks.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
