package main;

import entity.KozmetickiSalon;
import manage.ManageGlobal;
import utils.AppSettings;

import java.time.LocalTime;
import java.util.ArrayList;


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
//        controlers.loadData();

//      PPODESAVANJE PODATAKA KOZMETICKOG SALONA

        controlers.getKozmetickiSalonMngr().add("Moj salon", LocalTime.of(8, 0), LocalTime.of(20, 0), 1000.00);
        controlers.prikazKozmetickiSalon();

//      PODESAVANJE KORISNIKA


        controlers.getMenadzerMngr().add(
                "NikolaNikolic",
                "Nikola",
                "Nikolic",
                "M",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                100.00,
                4,
                15,
                false,
                80000.00
        );

        controlers.getRecepcionerMngr().add(
                "PeraPeric",
                "Pera",
                "Peric",
                "M",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                200.00,
                3,
                10,
                true,
                50000.00
        );

        ArrayList<Integer> spisakTretmanaSima = new ArrayList<>();
        spisakTretmanaSima.add(1);
        spisakTretmanaSima.add(3);
        controlers.getKozmeticarMngr().add(
                "SimaSimic",
                "Sima",
                "Simic",
                "M",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                300.00,
                4,
                10,
                true,
                90000.00,
                spisakTretmanaSima
        );

        controlers.getKozmeticarMngr().add(
                "ZikaZikic",
                "Zika",
                "Zikic",
                "M",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                200.00,
                3,
                10,
                true,
                90000.00,
                new ArrayList<Integer>()
        );

        ArrayList<Integer> spisakTretmanaJadranka = new ArrayList<>();
        spisakTretmanaJadranka.add(1);
        spisakTretmanaJadranka.add(2);
        controlers.getKozmeticarMngr().add(
                "JadrankaJovanovic",
                "Jadranka",
                "Jovanovic",
                "F",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                200.00,
                3,
                10,
                true,
                90000.00,
                spisakTretmanaJadranka
        );

        controlers.getKlijentMngr().add(
                "MilicaMilic",
                "Milica",
                "Milic",
                "F",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                2000.00,
                false,
                0.00
        );

        controlers.getKlijentMngr().add(
                "MikaMikic",
                "Mika",
                "Mikic",
                "M",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                5000.00,
                true,
                1000.00
        );

        controlers.getKozmeticarMngr().update(
                controlers.getKozmeticarMngr().findByKorisnickoIme("JadrankaJovanovic").getId(),
                "JadrankaJovanovic",
                "Jovana",
                "Jovanovic",
                "F",
                "123456",
                "Beograd Ustanicka 12",
                "password",
                200.00,
                3,
                10,
                true,
                90000.00,
                spisakTretmanaJadranka
        );

        controlers.prikazKorisnici();

        controlers.getKozmeticarMngr().deleteByKorisnickoIme("ZikaZikic");

        controlers.prikazKorisnici();
//        controlers.getTipTretmanaMngr().add("Nokti", "opisNokti");
//        controlers.getTipTretmanaMngr().add("Nokti1", "opisNokti1");
//        controlers.getTipTretmanaMngr().add("Nokti2", "opisNokti2");
//        controlers.getTipTretmanaMngr().update(1,"Nokti34", "opisNokti");
//        controlers.getTipTretmanaMngr().deleteById(1);
//        controlers.getTipTretmanaMngr().deleteByNazivTipaTretmana("Nokti2");

//        HashMap<Integer, Double> hm = new HashMap<>();
//        hm.put(333, 333.0);
//        hm.put(11, 231233.0);
//        hm.put(21, 111.0);
//        controlers.getCenovnikMngr().add(hm);
//        controlers.getCenovnikMngr().deleteById(2);
//        controlers.getCenovnikMngr().loadData();
//        controlers.getKorisnikMngr().add("LukaB", "Luka", "Bradic", "M", "06400000", "MMa", "password");
//        controlers.getKorisnikMngr().add("LukaB2", "Luka", "Bradic", "M", "06400000", "MMa", "password");
//        controlers.getKorisnikMngr().add("LukaB3", "Luka", "Bradic", "M", "06400000", "MMa", "password");
//        System.out.println(controlers.getKorisnikMngr().findById(1));
//        System.out.println(controlers.getKorisnikMngr().findByKorisnickoIme("LukaB3"));
//        controlers.getKorisnikMngr().deleteByKorisnickoIme("LukaB3");
//        controlers.getKorisnikMngr().update(2,"Lukaaaaaa", "Luka", "Bradic", "M", "06400000", "MMa", "password");
//        System.out.println(controlers.getKorisnikMngr().getKlijentHashMap());
//
//        controlers.getZakazanTretmanMngr().add(LocalDate.now(), LocalTime.now(), 1, 2, 3, 233.3, StanjeZakazanogTretmana.IZVRŠEN);
//        controlers.getZakazanTretmanMngr().deleteById(1);
//        controlers.getZakazanTretmanMngr().update(2, LocalDate.now(), LocalTime.now(), 22, 3, 3123, 21233.3, StanjeZakazanogTretmana.NIJESEPOJAVIO);
//
//        ArrayList<Integer> array = new ArrayList<>();
//        array.add(1);
//        array.add(2);
//        array.add(3);
//        array.add(555);
//        array.add(123);
////        controlers.getUslugaMngr().add("ime", array);
////        controlers.getUslugaMngr().add("prezime", array);
//        controlers.getUslugaMngr().update(2,"prezime", array);


    }
}