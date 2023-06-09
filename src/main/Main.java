package main;

import manage.Controler;
import manage.ManageGlobal;
import utils.AppSettings;
import view.MainFrame;



public class Main {
    public static void main(String[] args) {
        String separator = System.getProperty("file.separator");
        AppSettings appSettings = new AppSettings(
                "data" + separator + "klijetni.csv",
                "data" + separator + "menadzeri.csv",
                "data" + separator + "recepcioneri.csv",
                "data" + separator + "kozmeticari.csv",
                "data" + separator + "tipoviTretmana.csv",
                "data" + separator + "tipoviUsluga.csv",
                "data" + separator + "zakazaniTretmani.csv",
                "data" + separator + "cenovnici.csv",
                "data" + separator + "kozmetickiSaloni.csv"
        );
        ManageGlobal crudManagers = new ManageGlobal(appSettings);
        Controler controler = new Controler(crudManagers);
        
        new MainFrame(controler);
		
    }
}