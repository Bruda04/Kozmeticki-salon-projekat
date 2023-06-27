package entity;

import java.util.HashMap;
import java.util.Map;

public class Cenovnik {
    private final int id;

    private HashMap<Integer, Double> cenovnikHasMap;

    public Cenovnik(int id, HashMap<Integer, Double> ceneHashMap) {
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
            sb.append(",").append(entry.getKey()).append("=").append(entry.getValue());
        }
       return this.id + sb.toString();
    }

	public int getId() {
		return id;
	}
    
	@Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Cenovnik other = (Cenovnik) o;
        
        if(!cenovnikHasMap.equals(other.getCenovnikHasMap())) {
        	return false;        	
        }

        return true;
    }
}