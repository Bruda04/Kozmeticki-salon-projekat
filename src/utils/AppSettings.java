package utils;

public class AppSettings {
    private final String klijentiFilename;
    private final String menadzeriFilename;
    private final String recepcioneriFilename;
    private final String kozmeticariFilename;
    private final String tipTretmanaFilename;
    private final String uslugaFilename;
    private final String zakazaniTretmanFilename;
    private final String cenovnikFilename;
    private final  String kozmetickiSalonFilename;

    public AppSettings(String klijentiFilename,
                       String menadzeriFilename,
                       String recepcioneriFilename,
                       String kozmeticariFilename,
                       String tipTretmanaFilename,
                       String uslugaFilename,
                       String zakazaniTretmanFilename,
                       String cenovnikFilename,
                       String kozmetickiSalonFilename) {
        this.klijentiFilename = klijentiFilename;
        this.menadzeriFilename = menadzeriFilename;
        this.recepcioneriFilename = recepcioneriFilename;
        this.kozmeticariFilename = kozmeticariFilename;
        this.tipTretmanaFilename = tipTretmanaFilename;
        this.uslugaFilename = uslugaFilename;
        this.zakazaniTretmanFilename = zakazaniTretmanFilename;
        this.cenovnikFilename = cenovnikFilename;
        this.kozmetickiSalonFilename = kozmetickiSalonFilename;
    }


    public String getKLijentiFilename() {
        return this.klijentiFilename;
    }
    public String getMenadzeriFilename() {
        return this.menadzeriFilename;
    }
    public String getRecepcioneriFilename() {
        return this.recepcioneriFilename;
    }
    public String getKozmeticariFilename() {
        return this.kozmeticariFilename;
    }
    public String getTipTretmanaFilename() {
        return tipTretmanaFilename;
    }
    public String getUslugaFilename() {
        return uslugaFilename;
    }
    public String getZakazaniTretmanFilename() {
        return zakazaniTretmanFilename;
    }
    public String getCenovnikFilename() {
        return cenovnikFilename;
    }
    public String getKozmetickiSalonFilename() {
        return kozmetickiSalonFilename;
    }
}
