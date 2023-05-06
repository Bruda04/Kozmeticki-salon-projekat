package entity;

import java.util.HashMap;
import java.util.Map;

public class Cenovnik {
    private int id;

    private HashMap<Integer, Double> cenovnikHasMap = new HashMap<>();

    public Cenovnik(int id, HashMap ceneHashMap) {
        this.id = id;
        this.cenovnikHasMap = ceneHashMap;
    }

    public HashMap<Integer, Double> getCenovnikHasMap() {
        return cenovnikHasMap;
    }

    public void setCenovnikHasMap(HashMap<Integer, Double> cenovnikHasMap) {
        this.cenovnikHasMap = cenovnikHasMap;
    }

    public String toFileString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Double> entry : this.cenovnikHasMap.entrySet()) {
            sb.append("," + entry.getKey() + "=" + entry.getValue());
        }
       return this.id + sb.toString();
    }

}