package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Klijent;
import manage.Controler;
import manage.ManageGlobal;
import utils.AppSettings;

public class ControlerTest {
	
	private Controler controler;

	@Before
	public void setUp() throws Exception {
		String separator = System.getProperty("file.separator");
        AppSettings appSettings = new AppSettings(
                "data" + separator + "testKlijetni.csv",
                "data" + separator + "testMenadzeri.csv",
                "data" + separator + "testRecepcioneri.csv",
                "data" + separator + "testKozmeticari.csv",
                "data" + separator + "testTipoviTretmana.csv",
                "data" + separator + "testTipoviUsluga.csv",
                "data" + separator + "testZakazaniTretmani.csv",
                "data" + separator + "testCenovnici.csv",
                "data" + separator + "testKozmetickiSaloni.csv"
        );
        ManageGlobal crudManagers = new ManageGlobal(appSettings);
        this.controler = new Controler(crudManagers);
        
        controler.dodajTipTretmana("tipovi1", new ArrayList<>());
        controler.dodajUslugu("usluga1", 30, 1000.00, 1);
        
        controler.dodajTipTretmana("tipovi2", new ArrayList<>());
        controler.dodajUslugu("usluga2", 90, 3000.00, 2);
        
        controler.dodajTipTretmana("tipovi3", new ArrayList<>());
        controler.dodajUslugu("usluga3", 90, 2000.00, 3);
        
        controler.registrujKlijenta("klijent1", "klijent1", "klijent1", "M", "000000", "AdresaKlijent1", "password");
        controler.registrujKlijenta("klijent2", "klijent2", "klijent2", "M", "000000", "AdresaKlijent2", "password");

        ArrayList<Integer> zna = new ArrayList<>();
        zna.add(1);
        controler.registrujKozmeticara("kozmeticar", "kozmeticar", "kozmeticar", "M", "000000", "AdresaKozmeticar", "password", 3, 10, zna);
	}

	@Test
	public void izvestajPotvrdjenoOtkazano() {
		controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 12, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 8), LocalTime.of(9, 0), 1, 1, 2, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(14, 0), 1, 1, 3, 0);
        controler.zakaziTretman(LocalDate.of(2023, 7, 10), LocalTime.of(14, 0), 1, 1, 3, 0);
        
        controler.izvrsiTretman(1);
        controler.otkaziTretmanKlijent(2);
        controler.otkaziTretmanSalon(3);
        controler.propustenTretmanKlijent(4);
        
        int[] izvestaj = controler.izvestajPotvrdjenoOtkazano(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 8, 1));
        int[] ocekivano = {1, 1, 0, 1, 1};
        
        assertArrayEquals(ocekivano, izvestaj);
		
	}
	
	@Test
	public void izvestajUslugaStatistika() {
		controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(10, 0), 1, 1, 1, 0); // nista
        controler.zakaziTretman(LocalDate.of(2023, 12, 10), LocalTime.of(10, 0), 1, 1, 1, 0); // nista
        controler.zakaziTretman(LocalDate.of(2023, 6, 8), LocalTime.of(9, 0), 1, 1, 2, 0); // nista
        controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(14, 0), 1, 1, 3, 0); // 2k
        controler.zakaziTretman(LocalDate.of(2023, 7, 10), LocalTime.of(14, 0), 1, 1, 3, 0); 
        controler.zakaziTretman(LocalDate.of(2023, 7, 11), LocalTime.of(14, 0), 1, 1, 3, 0);

        
        controler.izvrsiTretman(1);
        controler.otkaziTretmanKlijent(2);
        controler.otkaziTretmanSalon(3);
        controler.propustenTretmanKlijent(4);
        
        HashMap<String, Object> izvestaj = controler.izvestajUslugaStatistika(3, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 8, 1));
        
        HashMap<String, Object> ocekivano = new HashMap<>();
        ocekivano.put("id", 3);
        ocekivano.put("naziv", "usluga3");
        ocekivano.put("trajanje", 90);
        ocekivano.put("tip", "tipovi3");
        ocekivano.put("zakazivanja", 3);
        ocekivano.put("cena", 2000.00);
        ocekivano.put("prihodi", 2000.00);
        
        assertEquals(ocekivano, izvestaj);
		
	}
	
	@Test
	public void izvestajPotencijalZaKarticuLojalnosti() {
		controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 12, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 8), LocalTime.of(9, 0), 1, 1, 2, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(14, 0), 2, 1, 3, 0);
        
        controler.izvrsiTretman(1);
        controler.izvrsiTretman(3);
        controler.izvrsiTretman(4);
        
        ArrayList<Klijent> izvestaj = controler.izvestajPotencijalZaKarticuLojalnosti(4000.00);
        ArrayList<Klijent> ocekivano = new ArrayList<>();
        ocekivano.add(controler.pronadjiKlijenta(1));
        
        assertEquals(ocekivano, izvestaj);
		
	}
	
	@Test
	public void izvestajBrojPrihodKozmeticari() {
		controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 12, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 8), LocalTime.of(9, 0), 1, 1, 2, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(14, 0), 2, 1, 3, 0);
        
        controler.izvrsiTretman(1);
        controler.otkaziTretmanKlijent(2);
        controler.otkaziTretmanSalon(3);
        controler.propustenTretmanKlijent(4);
        
        double[] izvestaj = controler.izvestajBrojPrihodKozmeticari(1, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 8, 1));
        double[] ocekivano = {1, 3000.00};
        
        assertEquals(ocekivano[0], izvestaj[0], 0.001);
        assertEquals(ocekivano[1], izvestaj[1], 0.001);

		
	}
	
	@Test
	public void izvestajPrihodiRashodi() {
		controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(10, 0), 1, 1, 1, 0);
        controler.zakaziTretman(LocalDate.of(2023, 12, 10), LocalTime.of(10, 0), 1, 1, 1, 0); //nista
        controler.zakaziTretman(LocalDate.of(2023, 6, 8), LocalTime.of(9, 0), 1, 1, 2, 0);
        controler.zakaziTretman(LocalDate.of(2023, 6, 10), LocalTime.of(14, 0), 2, 1, 3, 0);
        
        controler.izvrsiTretman(1);
        controler.otkaziTretmanKlijent(2);
        controler.otkaziTretmanSalon(3);
        controler.propustenTretmanKlijent(4);
        
        double[] izvestaj = controler.izvestajPrihodiRashodi(LocalDate.of(2023, 6, 1), LocalDate.of(2023, 7, 1));
        double[] ocekivano = {3000.00, controler.pronadjiKozmeticara(1).getPlata()};
        
        assertEquals(ocekivano[0], izvestaj[0], 0.001);
        assertEquals(ocekivano[1], izvestaj[1], 0.001);

		
	}
	
	@After
    public void finished() {
		String separator = System.getProperty("file.separator");
        File[] testFiles = {
        		new File("data" + separator + "testKlijetni.csv"),
        		new File("data" + separator + "testMenadzeri.csv"),
        		new File("data" + separator + "testRecepcioneri.csv"),
        		new File("data" + separator + "testKozmeticari.csv"),
        		new File("data" + separator + "testTipoviTretmana.csv"),
        		new File("data" + separator + "testTipoviUsluga.csv"),
        		new File("data" + separator + "testZakazaniTretmani.csv"),
        		new File("data" + separator + "testCenovnici.csv"),
        		new File("data" + separator + "testKozmetickiSaloni.csv")
        };
        
        for (File file : testFiles) {
        	file.delete();			
		}
    }

}
