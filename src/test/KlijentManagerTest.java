package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Klijent;
import manage.KlijentManager;

public class KlijentManagerTest {
	
	private KlijentManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "klijetniTest.csv";
		this.menadzer = new KlijentManager(path);
		menadzer.add("prvi", "prviIme", "prviPrezime", "prviPol", "prviTelefon", "prviAdresa", "prviLozinka", true, 0.00);
		menadzer.add("drugi", "drugiIme", "drugiPrezime", "drugiPol", "drugiTelefon", "drugiAdresa", "drugiLozinka", false, 1100.00);
	}

	@Test
	public void brojDodatihKlijenata() {
		assertEquals(2, menadzer.getKlijentHashMap().size());
	}
	
	@Test
	public void dodavanjeKlijenta() {
		menadzer.add("treci", "treciIme", "treciPrezime", "treciPol", "treciTelefon", "treciAdresa", "treciLozinka", false, 1100.00);
		assertEquals("treci", menadzer.findByKorisnickoIme("treci").getKorisnickoIme());
	}
	
	@Test
	public void pronalazenjePoKorisnickomImenu() {
		assertEquals("drugi", menadzer.findByKorisnickoIme("drugi").getKorisnickoIme());
	}
	
	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}
	
	@Test
	public void brisanjeKlijentaPoKorisnickomImenu() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", false, 1100.00);
		menadzer.deleteByKorisnickoIme("cetvrti");
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void brisanjeKlijentaPoId() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", false, 1100.00);
		menadzer.deleteById(menadzer.findByKorisnickoIme("cetvrti").getId());
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void izmenaKlijenta() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", false, 1100.00);
		Klijent k = menadzer.findByKorisnickoIme("cetvrti");
		menadzer.update(k.getId(), "peti", "petiIme", "petiPrezime", "petiPol", "petiTelefon", "petiAdresa", "petiLozinka", true, 11010.00);
		assertEquals("peti", menadzer.findById(k.getId()).getKorisnickoIme());
	}
	
	@Test
	public void fileKlijent() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "klijetniTest.csv";
		KlijentManager kmTmp = new KlijentManager(path);
		kmTmp.loadData();
		
		assertEquals(menadzer, kmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "klijetniTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
