package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import entity.Kozmeticar;
import entity.ZakazanTretman;
import manage.Controler;
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
        
        controler.dodajUslugu("Relaks masaza", 45, 2000.00);
        controler.dodajUslugu("Sportska masaza", 75, 2500.00);
        controler.dodajUslugu("Francuski manikir", 50, 1500.00);
        controler.dodajUslugu("Gel lak", 80, 1600.00);
        controler.dodajUslugu("Spa manikir", 90, 2000.00);
        controler.dodajUslugu("Spa pedikir", 45, 1600.00);
        
        ArrayList<Integer> masazaListaUsluga = new ArrayList<>();
        masazaListaUsluga.add(controler.pronadjiUslugu("Relaks masaza").getId());
        masazaListaUsluga.add(controler.pronadjiUslugu("Sportska masaza").getId());
        controler.dodajTipTretmana("masaza", masazaListaUsluga);

        ArrayList<Integer> manikirListaUsluga = new ArrayList<>();
        manikirListaUsluga.add(controler.pronadjiUslugu("Francuski manikir").getId());
        manikirListaUsluga.add(controler.pronadjiUslugu("Gel lak").getId());
        manikirListaUsluga.add(controler.pronadjiUslugu("Spa manikir").getId());
        controler.dodajTipTretmana("manikir", manikirListaUsluga);

        ArrayList<Integer> pedikirListaUsluga = new ArrayList<>();
        pedikirListaUsluga.add(controler.pronadjiUslugu("Spa pedikir").getId());
        controler.dodajTipTretmana("pedikir", pedikirListaUsluga);
        
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
        

        
        // ---------------------------------------------------------------------------------------------------------------------------------
        
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
        
        /// NEUSPESAN TERMIN I KOZMETICAR UBACITII TODO
        
        ArrayList<ZakazanTretman> rasporedZika = controler.zakazaniTretmaniKozmeticara(controler.pronadjiKozmeticara("ZikaZikic").getId());
        for (ZakazanTretman zt : rasporedZika) {
			System.out.printf("Datum: %s, Vreme: %s, Klijent: %s, Usluga: %s\n", zt.getDatum(), zt.getVreme(), controler.pronadjiKlijenta(zt.getIdKlijenta()).getKorisnickoIme(), controler.pronadjiUslugu(zt.getIdTipaUsluge()).getNazivUsluge());
		}
        
        controler.postaviPragZaKarticuLojalnosti(3500.00);
        
        controler.izvrsiTretman(1);
        
        controler.otkaziTretmanKlijent(2);
        
        controler.otkaziTretmanSalon(3);
        
        controler.propustenTretmanKlijent(4);
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 14),
				LocalTime.of(9, 0),
				controler.pronadjiKlijenta("MilicaMilic").getId(),
				controler.pronadjiKozmeticara("SimaSimic").getId(),
				controler.pronadjiUslugu("Gel lak").getId(),
				0);
        
        controler.zakaziTretman(LocalDate.of(2023, 6, 14),
								LocalTime.of(9, 0),
								controler.pronadjiKlijenta("MikaMikic").getId(),
								controler.pronadjiKozmeticara("SimaSimic").getId(),
								controler.pronadjiUslugu("Spa Manikir").getId(),
								controler.dodeliKozmeticara(controler.pronadjiKozmeticara("SimaSimic").getId(), LocalDate.of(2023, 6, 14), LocalTime.of(9, 0)).getId());
        
        for (ZakazanTretman zakazanTretman : controler.tretmaniKlijenta(controler.pronadjiKlijenta("MikaMikic").getId())) {
        	System.out.print(zakazanTretman);
		}
        
        
    }
}