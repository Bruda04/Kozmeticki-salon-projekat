package main;

import java.time.LocalTime;
import manage.ManageGlobal;
import utils.AppSettings;



public class Main {
    public static void main(String[] args) {
        String separator = System.getProperty("file.separator");
        AppSettings appSettings = new AppSettings(
                "data" + separator + "klijetni.csv",
                "data" + separator +"menadzeri.csv",
                "data" + separator +"recepcioneri.csv",
                "data" + separator +"kozmeticari.csv",
                "data" + separator +"tipoviTretmana.csv",
                "data" + separator +"tipoviUsluga.csv",
                "data" + separator +"zakazaniTretmani.csv",
                "data" + separator +"cenovnici.csv",
                "data" + separator +"kozmetickiSaloni.csv"
        );
        ManageGlobal controlers = new ManageGlobal(appSettings);
        controlers.loadData();

//      PPODESAVANJE PODATAKA KOZMETICKOG SALONA

        controlers.getKozmetickiSalonMngr().add("Moj salon", LocalTime.of(8, 0), LocalTime.of(20, 0));
        controlers.prikazKozmetickiSalon();


    }
}