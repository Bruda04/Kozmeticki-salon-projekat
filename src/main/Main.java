package main;

import entity.*;
import manage.ManageGlobal;
import utils.AppSettings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;


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
                new ArrayList<>()
        );

        ArrayList<Integer> spisakTretmanaJadranka = new ArrayList<>();
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
        Kozmeticar kUpdate = controlers.getKozmeticarMngr().findByKorisnickoIme("JadrankaJovanovic");
        controlers.getKozmeticarMngr().update(
                kUpdate.getId(),
                kUpdate.getKorisnickoIme(),
                "Jovana",
                kUpdate.getPrezime(),
                kUpdate.getPol(),
                kUpdate.getTelefon(),
                kUpdate.getAdresa(),
                kUpdate.getLozinka(),
                kUpdate.getStanjeRacuna(),
                kUpdate.getNivoStrucneSpreme(),
                kUpdate.getGodineStaza(),
                kUpdate.getBonus(),
                kUpdate.getPlata(),
                kUpdate.getSpisakTretmana()
        );

        controlers.prikazKorisnici();

        controlers.getKozmeticarMngr().deleteByKorisnickoIme("ZikaZikic");

        controlers.prikazKorisnici();

//        PODESAVANJE TIPOVA USLUGA

        controlers.getUslugaMngr().add("Relaks masaza", 45);
        controlers.getUslugaMngr().add("Sportska masaza", 75);
        controlers.getUslugaMngr().add("Francuski manikir", 50);
        controlers.getUslugaMngr().add("Gel lak", 80);
        controlers.getUslugaMngr().add("Spa manikir", 90);
        controlers.getUslugaMngr().add("Spa pedikir", 45);

//        PODESAVANJE TIPOVA TRETMANA

        ArrayList<Integer> masazaListaUsluga = new ArrayList<>();
        masazaListaUsluga.add(controlers.getUslugaMngr().findByNazivUsluge("Relaks masaza").getId());
        masazaListaUsluga.add(controlers.getUslugaMngr().findByNazivUsluge("Sportska masaza").getId());
        controlers.getTipTretmanaMngr().add("masaza", masazaListaUsluga);

        ArrayList<Integer> manikirListaUsluga = new ArrayList<>();
        manikirListaUsluga.add(controlers.getUslugaMngr().findByNazivUsluge("Francuski manikir").getId());
        manikirListaUsluga.add(controlers.getUslugaMngr().findByNazivUsluge("Gel lak").getId());
        manikirListaUsluga.add(controlers.getUslugaMngr().findByNazivUsluge("Spa manikir").getId());
        controlers.getTipTretmanaMngr().add("manikir", manikirListaUsluga);

        ArrayList<Integer> pedikirListaUsluga = new ArrayList<>();
        controlers.getTipTretmanaMngr().add("pedikir", pedikirListaUsluga);

//        PODESAVANJE CENOVNIKA
        HashMap<Integer, Double> cenovnik = new HashMap<>();
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Relaks masaza").getId(), 2000.00);
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Sportska masaza").getId(), 2500.00);
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Francuski manikir").getId(), 1500.00);
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Gel lak").getId(), 1600.00);
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Spa manikir").getId(), 2000.00);
        cenovnik.put(controlers.getUslugaMngr().findByNazivUsluge("Spa pedikir").getId(), 1600.00);
        controlers.getCenovnikMngr().add(cenovnik);

        controlers.prikazTretmanUslugaCena();

//        IZMENE TRETMANA I USLUGA


        Usluga uUpdate = controlers.getUslugaMngr().findByNazivUsluge("Francuski manikir");
        controlers.getUslugaMngr().update(
                uUpdate.getId(),
                uUpdate.getNazivUsluge(),
                55
        );

        int idUslugeZaPremestanje = controlers.getUslugaMngr().findByNazivUsluge("Spa pedikir").getId();
        boolean finish = false;
        for (TipTretmana tt : controlers.getTipTretmanaMngr().getTipoviTretmanaHashMap().values()) {
            int i = 0;
            for (int uId: tt.getSkupTipovaUsluga()) {
                if (uId == idUslugeZaPremestanje) {
                    ArrayList<Integer> ttNew = tt.getSkupTipovaUsluga();
                    ttNew.remove(i);
                    controlers.getTipTretmanaMngr().update(tt.getId(), tt.getNaziv(), ttNew);
                    finish = true;
                    break;
                }
                if (finish){
                    break;
                }
                i++;
            }
        }

        TipTretmana tUpdate = controlers.getTipTretmanaMngr().findByNazivTipaTretmana("pedikir");
        ArrayList<Integer> ttNew = tUpdate.getSkupTipovaUsluga();
        ttNew.add(idUslugeZaPremestanje);
        controlers.getTipTretmanaMngr().update(
                tUpdate.getId(),
                tUpdate.getNaziv(),
                ttNew
        );

        controlers.prikazTretmanUslugaCena();


//        PODESAVANJE OBUCENOSTI KOZMETICARA

        Kozmeticar kozmeticarUpd =  controlers.getKozmeticarMngr().findByKorisnickoIme("SimaSimic");
        spisakTretmanaSima.add(controlers.getTipTretmanaMngr().findByNazivTipaTretmana("masaza").getId());
        spisakTretmanaSima.add(controlers.getTipTretmanaMngr().findByNazivTipaTretmana("manikir").getId());
        controlers.getKozmeticarMngr().update(
                kozmeticarUpd.getId(),
                kozmeticarUpd.getKorisnickoIme(),
                kozmeticarUpd.getIme(),
                kozmeticarUpd.getPrezime(),
                kozmeticarUpd.getPol(),
                kozmeticarUpd.getTelefon(),
                kozmeticarUpd.getAdresa(),
                kozmeticarUpd.getLozinka(),
                kozmeticarUpd.getStanjeRacuna(),
                kozmeticarUpd.getNivoStrucneSpreme(),
                kozmeticarUpd.getGodineStaza(),
                kozmeticarUpd.getBonus(),
                kozmeticarUpd.getPlata(),
                spisakTretmanaSima
        );

        kozmeticarUpd =  controlers.getKozmeticarMngr().findByKorisnickoIme("JadrankaJovanovic");
        spisakTretmanaJadranka.add(controlers.getTipTretmanaMngr().findByNazivTipaTretmana("manikir").getId());
        spisakTretmanaJadranka.add(controlers.getTipTretmanaMngr().findByNazivTipaTretmana("pedikir").getId());
        controlers.getKozmeticarMngr().update(
                kozmeticarUpd.getId(),
                kozmeticarUpd.getKorisnickoIme(),
                kozmeticarUpd.getIme(),
                kozmeticarUpd.getPrezime(),
                kozmeticarUpd.getPol(),
                kozmeticarUpd.getTelefon(),
                kozmeticarUpd.getAdresa(),
                kozmeticarUpd.getLozinka(),
                kozmeticarUpd.getStanjeRacuna(),
                kozmeticarUpd.getNivoStrucneSpreme(),
                kozmeticarUpd.getGodineStaza(),
                kozmeticarUpd.getBonus(),
                kozmeticarUpd.getPlata(),
                spisakTretmanaJadranka
        );

        controlers.prikazKorisnici();

//        ZAKAZIVANJE KONKRETNIH TRETMANA

        controlers.getZakazanTretmanMngr().add(
                LocalDate.now(),
                LocalTime.now(),
                controlers.getKlijentMngr().findByKorisnickoIme("MilicaMilic").getId(),
                controlers.getKozmeticarMngr().findByKorisnickoIme("SimaSimic").getId(),
                controlers.getUslugaMngr().findByNazivUsluge("Relaks masaza").getId(),
                controlers.getCenovnikMngr().findById(1).getCenovnikHasMap().get(controlers.getUslugaMngr().findByNazivUsluge("Relaks masaza").getId()),
                StanjeZakazanogTretmana.ZAKAZAN
        );

        controlers.getZakazanTretmanMngr().add(
                LocalDate.now(),
                LocalTime.now(),
                controlers.getKlijentMngr().findByKorisnickoIme("MikaMikic").getId(),
                controlers.getKozmeticarMngr().findByKorisnickoIme("SimaSimic").getId(),
                controlers.getUslugaMngr().findByNazivUsluge("Gel lak").getId(),
                controlers.getCenovnikMngr().findById(1).getCenovnikHasMap().get(controlers.getUslugaMngr().findByNazivUsluge("Gel lak").getId()),
                StanjeZakazanogTretmana.ZAKAZAN
        );

        controlers.getZakazanTretmanMngr().add(
                LocalDate.now(),
                LocalTime.now(),
                controlers.getKlijentMngr().findByKorisnickoIme("MikaMikic").getId(),
                controlers.getKozmeticarMngr().findByKorisnickoIme("JadrankaJovanovic").getId(),
                controlers.getUslugaMngr().findByNazivUsluge("Spa pedikir").getId(),
                controlers.getCenovnikMngr().findById(1).getCenovnikHasMap().get(controlers.getUslugaMngr().findByNazivUsluge("Spa pedikir").getId()),
                StanjeZakazanogTretmana.ZAKAZAN
        );

        controlers.getTipTretmanaMngr().deleteByNazivTipaTretmana("pedikir");

        controlers.prikaziZakazaneTretmane();

        ZakazanTretman ztUpdate = controlers.getZakazanTretmanMngr().findById(2);
        controlers.getZakazanTretmanMngr().update(
                ztUpdate.getId(),
                ztUpdate.getDatum(),
                ztUpdate.getVreme(),
                ztUpdate.getIdKlijenta(),
                ztUpdate.getIdKozmeticara(),
                controlers.getUslugaMngr().findByNazivUsluge("Francuski manikir").getId(),
                controlers.getCenovnikMngr().findById(1).getCenovnikHasMap().get(controlers.getUslugaMngr().findByNazivUsluge("Francuski manikir").getId()),
                ztUpdate.getStanje()
        );

        controlers.prikaziZakazaneTretmane();


//        IZMENE CENA

        controlers.prikazTretmanUslugaCena();

        HashMap<Integer, Double> cenovnikUpdate = controlers.getCenovnikMngr().findById(1).getCenovnikHasMap();
        cenovnikUpdate.replace(controlers.getUslugaMngr().findByNazivUsluge("Relaks masaza").getId(), 1700.00);
        controlers.getCenovnikMngr().update(1, cenovnikUpdate);

        controlers.prikazTretmanUslugaCena();
    }
}