package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import entity.Kozmeticar;
import entity.ZakazanTretman;
import entity.Zaposleni;
import manage.Controler;
import manage.ManageGlobal;
import utils.AppSettings;



public class KT3 {
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
        ManageGlobal crudManagers = new ManageGlobal(appSettings);
        Controler controler = new Controler(crudManagers);

        controler.registrujMenadzera("NikolaNikolic", "Nikola", "Nikolic", "M", "0123456", "Beograd Ustanicka 12", "password", 4, 10);
        controler.registrujRecepcionera("PeraPeric", "Pera", "Peric", "M", "0123456", "Beograd Ustanicka 12", "password", 3, 2);
        
        ArrayList<Integer> spisakTretmanaSima = new ArrayList<>();
        controler.registrujKozmeticara("SimaSimic",
						               "Sima",
						               "Simic",
						               "M",
						               "0123456",
						               "Beograd Ustanicka 12",
						               "password",
						               4,
						               2,
						               spisakTretmanaSima);
        
        ArrayList<Integer> spisakTretmanaZika = new ArrayList<>();
        controler.registrujKozmeticara("ZikaZikic",
						               "Zika",
						               "Zikic",
						               "M",
						               "0123456",
						               "Beograd Ustanicka 12",
						               "password",
						               3,
						               10,
						               spisakTretmanaZika);
        
        ArrayList<Integer> spisakTretmanaJovana = new ArrayList<>();
        controler.registrujKozmeticara("JovanaJovanovic",
						               "Jovana",
						               "Jovanovic",
						               "F",
						               "0123456",
						               "Beograd Ustanicka 12",
						               "password",
						               4,
						               1,
						               spisakTretmanaJovana);
        
        controler.registrujKlijenta("MilicaMilic",
					                "Milica",
					                "Milic",
					                "F",
					                "0123456",
					                "Beograd Ustanicka 12",
					                "password");
						            

        controler.registrujKlijenta("MikaMikic",
					                "Mika",
					                "Mikic",
					                "M",
					                "0123456",
					                "Beograd Ustanicka 12",
					                "password");
        
        ArrayList<Integer> masazaListaUsluga = new ArrayList<>();
        controler.dodajTipTretmana("masaza", masazaListaUsluga);
        
        ArrayList<Integer> manikirListaUsluga = new ArrayList<>();
        controler.dodajTipTretmana("manikir", manikirListaUsluga);
        
        ArrayList<Integer> pedikirListaUsluga = new ArrayList<>();
        controler.dodajTipTretmana("pedikir", pedikirListaUsluga);
        
        controler.dodajUslugu("Relaks masaza", 45, 2000.00, controler.pronadjiTipTretmana("masaza").getId());
        controler.dodajUslugu("Sportska masaza", 75, 2500.00, controler.pronadjiTipTretmana("masaza").getId());
        controler.dodajUslugu("Francuski manikir", 50, 1500.00, controler.pronadjiTipTretmana("manikir").getId());
        controler.dodajUslugu("Gel lak", 80, 1600.00, controler.pronadjiTipTretmana("manikir").getId());
        controler.dodajUslugu("Spa manikir", 90, 2000.00, controler.pronadjiTipTretmana("manikir").getId());
        controler.dodajUslugu("Spa pedikir", 45, 1600.00, controler.pronadjiTipTretmana("pedikir").getId());
        
        
        Kozmeticar kozmeticarUpd =  controler.pronadjiKozmeticara("SimaSimic");
        spisakTretmanaSima.add(controler.pronadjiTipTretmana("masaza").getId());
        spisakTretmanaSima.add(controler.pronadjiTipTretmana("manikir").getId());
        controler.izmeniKozmeticara(
                kozmeticarUpd.getId(),
                kozmeticarUpd.getKorisnickoIme(),
                kozmeticarUpd.getIme(),
                kozmeticarUpd.getPrezime(),
                kozmeticarUpd.getPol(),
                kozmeticarUpd.getTelefon(),
                kozmeticarUpd.getAdresa(),
                kozmeticarUpd.getLozinka(),
                kozmeticarUpd.getNivoStrucneSpreme(),
                kozmeticarUpd.getGodineStaza(),
                kozmeticarUpd.getBonus(),
                kozmeticarUpd.getPlata(),
                spisakTretmanaSima
        );
        
        kozmeticarUpd =  controler.pronadjiKozmeticara("ZikaZikic");
        spisakTretmanaZika.add(controler.pronadjiTipTretmana("manikir").getId());
        spisakTretmanaZika.add(controler.pronadjiTipTretmana("masaza").getId());
        spisakTretmanaZika.add(controler.pronadjiTipTretmana("pedikir").getId());
        controler.izmeniKozmeticara(
                kozmeticarUpd.getId(),
                kozmeticarUpd.getKorisnickoIme(),
                kozmeticarUpd.getIme(),
                kozmeticarUpd.getPrezime(),
                kozmeticarUpd.getPol(),
                kozmeticarUpd.getTelefon(),
                kozmeticarUpd.getAdresa(),
                kozmeticarUpd.getLozinka(),
                kozmeticarUpd.getNivoStrucneSpreme(),
                kozmeticarUpd.getGodineStaza(),
                kozmeticarUpd.getBonus(),
                kozmeticarUpd.getPlata(),
                spisakTretmanaZika
        );

        kozmeticarUpd =  controler.pronadjiKozmeticara("JovanaJovanovic");
        spisakTretmanaJovana.add(controler.pronadjiTipTretmana("manikir").getId());
        controler.izmeniKozmeticara(
                kozmeticarUpd.getId(),
                kozmeticarUpd.getKorisnickoIme(),
                kozmeticarUpd.getIme(),
                kozmeticarUpd.getPrezime(),
                kozmeticarUpd.getPol(),
                kozmeticarUpd.getTelefon(),
                kozmeticarUpd.getAdresa(),
                kozmeticarUpd.getLozinka(),
                kozmeticarUpd.getNivoStrucneSpreme(),
                kozmeticarUpd.getGodineStaza(),
                kozmeticarUpd.getBonus(),
                kozmeticarUpd.getPlata(),
                spisakTretmanaJovana
        );
        

//        crudManagers.prikazKozmetickiSalon();
//        crudManagers.prikazKorisnici();
//        crudManagers.prikazTretmanUslugaCena();
//        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 10),
        						LocalTime.of(18, 0),
        						controler.pronadjiKlijenta("MilicaMilic").getId(),
        						controler.pronadjiKozmeticara("JovanaJovanovic").getId(),
        						controler.pronadjiUslugu("Francuski manikir").getId(),
        						0);
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 11),
								LocalTime.of(9, 0),
								controler.pronadjiKlijenta("MilicaMilic").getId(),
								controler.pronadjiKozmeticara("ZikaZikic").getId(),
								controler.pronadjiUslugu("Spa pedikir").getId(),
								controler.pronadjiRecepcionera("PeraPeric").getId());
        
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 12),
								LocalTime.of(8, 0),
								controler.pronadjiKlijenta("MikaMikic").getId(),
								controler.pronadjiKozmeticara("SimaSimic").getId(),
								controler.pronadjiUslugu("Sportska masaza").getId(),
								0);
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 13),
								LocalTime.of(19, 0),
								controler.pronadjiKlijenta("MikaMikic").getId(),
								controler.pronadjiKozmeticara("ZikaZikic").getId(),
								controler.pronadjiUslugu("Relaks masaza").getId(),
								controler.pronadjiRecepcionera("PeraPeric").getId());
        
        
        System.out.println("\n#####NEUSPESNO ZAKAZIVANJE MIKA#####");
        Boolean znaTretman = controler.kozmeticarZnaUslugu(controler.pronadjiKozmeticara("JovanaJovanovic").getId(), controler.pronadjiUslugu("Francuski manikir").getId());
        if (!znaTretman) {
        	System.out.println("Kozmeticar ne zna taj tretman");
        }
        ArrayList<LocalTime> moguciTermini = controler.kozmeticarSlobodan(controler.pronadjiKozmeticara("JovanaJovanovic").getId(), LocalDate.of(2023, 6, 10));
        if (!moguciTermini.contains(LocalTime.of(18, 0))) {
        	System.out.println("Kozmeticar nije slobodan u tom terminu");
        }
        
//        controler.zakaziTretman(LocalDate.of(2023, 6, 10),
//				LocalTime.of(18, 0),
//				controler.pronadjiKlijenta("MikaMikic").getId(),
//				controler.pronadjiKozmeticara("JovanaJovanovic").getId(),
//				controler.pronadjiUslugu("Francuski manikir").getId(),
//				0);
        
        System.out.println("\n#####RASPORED TRETMANA ZIKA#####");
        ArrayList<ZakazanTretman> rasporedZika = controler.zakazaniTretmaniKozmeticara(controler.pronadjiKozmeticara("ZikaZikic").getId());
        for (ZakazanTretman zt : rasporedZika) {
			System.out.printf("Datum: %s, Vreme: %s, Klijent: %s, Usluga: %s\n", zt.getDatum(), zt.getVreme(), controler.pronadjiKlijenta(zt.getIdKlijenta()).getKorisnickoIme(), controler.pronadjiUslugu(zt.getIdTipaUsluge()).getNazivUsluge());
		}
        
        
        controler.postaviPragZaKarticuLojalnosti(3500.00);
        System.out.println("\n#####PROMENA PRAGA ZA KARTICU LOJALNOSTI#####");
        crudManagers.prikazKlijenti();
        
        System.out.println("\n----------------------------------IZVRSAVANJE PRVOG TRETMANA MILICEMILIC---------------------------------------------\n");
        controler.izvrsiTretman(1);
        crudManagers.prikaziZakazaneTretmane();
        crudManagers.prikazKlijenti();
        crudManagers.prikazKozmetickiSalon();
        
        System.out.println("\n----------------------------------MILICEMILIC OTKAZUJE DRUGI TRETMAN---------------------------------------------\n");
        controler.otkaziTretmanKlijent(2);
        crudManagers.prikaziZakazaneTretmane();
        crudManagers.prikazKlijenti();
        crudManagers.prikazKozmetickiSalon();
        
        System.out.println("\n----------------------------------MIKAMIKIC SALON OTKAZUJE PRVI TRETMAN---------------------------------------------\n");
        controler.otkaziTretmanSalon(3);
        crudManagers.prikaziZakazaneTretmane();
        crudManagers.prikazKlijenti();
        crudManagers.prikazKozmetickiSalon();
        
        System.out.println("\n----------------------------------MIKAMIKIC NIJE SE POJAVIO ZA DRUGI TRETMAN---------------------------------------------\n");
        controler.propustenTretmanKlijent(4);
        crudManagers.prikaziZakazaneTretmane();
        crudManagers.prikazKlijenti();
        crudManagers.prikazKozmetickiSalon();
        
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 14),
								LocalTime.of(9, 0),
								controler.pronadjiKlijenta("MilicaMilic").getId(),
								controler.pronadjiKozmeticara("SimaSimic").getId(),
								controler.pronadjiUslugu("Gel lak").getId(),
								0);
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 14),
								LocalTime.of(9, 0),
								controler.pronadjiKlijenta("MikaMikic").getId(),
								controler.dodeliKozmeticara(controler.pronadjiUslugu("Spa manikir").getId(), LocalDate.of(2023, 6, 14), LocalTime.of(9, 0)).getId(),
								controler.pronadjiUslugu("Spa manikir").getId(),
								0);
        
        System.out.println("\n----------------------------------IZVRSAVANJE TRETMANA MIKEMIKIC AUTOMATSKI DODELJEN KOZMETICAR---------------------------------------------\n");
        controler.izvrsiTretman(6);
        crudManagers.prikaziZakazaneTretmane();
        crudManagers.prikazKlijenti();
        crudManagers.prikazKozmetickiSalon();
        
        System.out.println("\n#####ISTORIJA TRETMANA MIKA#####");
        for (ZakazanTretman zakazanTretman : controler.tretmaniKlijenta(controler.pronadjiKlijenta("MikaMikic").getId())) {
			System.out.printf("Datum: %s, Vreme: %s, Kozmeticar: %s, Usluga: %s, Status: %s, Cena: %.2f\n", zakazanTretman.getDatum(), zakazanTretman.getVreme(), controler.pronadjiKozmeticara(zakazanTretman.getIdKozmeticara()).getKorisnickoIme(), controler.pronadjiUslugu(zakazanTretman.getIdTipaUsluge()).getNazivUsluge(), zakazanTretman.getStanje(), zakazanTretman.getCena());
		}
        
        System.out.println("\n#####PRIHODI RASHODI#####");
        double[] izvestaj = controler.izvestajPrihodiRashodi(LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 30));
        System.out.printf("Prihodi: %.2f, Rashodi: %.2f\n", izvestaj[0], izvestaj[1]);
        
        
        System.out.println("\n#####ZAPOSLENI SA BONUSOM#####");
        for (Zaposleni zap : controler.zaposleniSaBonusom()) {
			System.out.println(zap.toString());
		}
        
        
    }
}