package manage;

import entity.ZakazanTretman;
import entity.StanjeZakazanogTretmana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class ZakazanTretmanManager {
    private final String zakzanTretmanFile;
    private final HashMap<Integer, ZakazanTretman> zakazanTretmanHashMap = new HashMap<>();
    private int nextId = 1;

    public ZakazanTretmanManager(String zakzanTretmanFile) {
        super();
        this.zakzanTretmanFile = zakzanTretmanFile;
    }

    public HashMap<Integer, ZakazanTretman> getZakazanTretmanHashMap() {
        return zakazanTretmanHashMap;
    }

    public void add(LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaTretmana, double cena, StanjeZakazanogTretmana stanje) {
        this.zakazanTretmanHashMap.put(nextId ,new ZakazanTretman(this.nextId, datum, vreme, idKlijenta, idKozmeticara, idTipaTretmana, cena, stanje));
        this.nextId++;
        this.saveData();
    }

    public ZakazanTretman findById(int id){
        return this.zakazanTretmanHashMap.get(id);
    }

    public void deleteById(int id){
        this.zakazanTretmanHashMap.remove(id);
        this.saveData();
    }

    public void update(int id, LocalDate datum, LocalTime vreme, int idKlijenta, int idKozmeticara, int idTipaTretmana, double cena, StanjeZakazanogTretmana stanje) {
        ZakazanTretman updatedZakazanTretman = this.zakazanTretmanHashMap.get(id);
        updatedZakazanTretman.setDatum(datum);
        updatedZakazanTretman.setVreme(vreme);
        updatedZakazanTretman.setIdKlijenta(idKlijenta);
        updatedZakazanTretman.setIdKozmeticara(idKozmeticara);
        updatedZakazanTretman.setIdTipaTretmana(idTipaTretmana);
        updatedZakazanTretman.setCena(cena);
        updatedZakazanTretman.setStanje(stanje);
        this.zakazanTretmanHashMap.replace(id, updatedZakazanTretman);
        this.saveData();
    }

    public boolean loadData() {
        this.zakazanTretmanHashMap.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.zakzanTretmanFile));
            String linija = null;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                int id = Integer.parseInt(tokeni[0]);
                this.zakazanTretmanHashMap.put(id ,new ZakazanTretman(id, LocalDate.parse(tokeni[1]), LocalTime.parse(tokeni[2], DateTimeFormatter.ofPattern("HH:mm")), Integer.parseInt(tokeni[3]),
                        Integer.parseInt(tokeni[4]), Integer.parseInt(tokeni[5]),  Double.parseDouble(tokeni[6]), StanjeZakazanogTretmana.valueOf(tokeni[7])));
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
            pw = new PrintWriter(new FileWriter(this.zakzanTretmanFile, false));
            for (ZakazanTretman zt : zakazanTretmanHashMap.values()) {
                pw.println(zt.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
