package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Menadzer;
import manage.MenadzerManager;

public class MenadzerManagerTest {

	private MenadzerManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "menadzeriTest.csv";
		this.menadzer = new MenadzerManager(path);
		menadzer.add("prvi", "prviIme", "prviPrezime", "prviPol", "prviTelefon", "prviAdresa", "prviLozinka", 4, 1, true, 100000.00);
		menadzer.add("drugi", "drugiIme", "drugiPrezime", "drugiPol", "drugiTelefon", "drugiAdresa", "drugiLozinka", 3, 5, false, 90000.00);
	}

	@Test
	public void brojDodatihMenadzera() {
		assertEquals(2, menadzer.getMenadzerHashMap().size());
	}
	
	@Test
	public void dodavanjeMenadzera() {
		menadzer.add("treci", "treciIme", "treciPrezime", "treciPol", "treciTelefon", "treciAdresa", "treciLozinka", 4, 1, true, 100000.00);
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
	public void brisanjeMenadzeraPoKorisnickomImenu() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 100000.00);
		menadzer.deleteByKorisnickoIme("cetvrti");
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void brisanjeMenadzeraPoId() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 100000.00);
		menadzer.deleteById(menadzer.findByKorisnickoIme("cetvrti").getId());
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void izmenaMenadzera() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 100000.00);
		Menadzer k = menadzer.findByKorisnickoIme("cetvrti");
		menadzer.update(k.getId(), "peti", "petiIme", "petiPrezime", "petiPol", "petiTelefon", "petiAdresa", "petiLozinka", 3, 10, false, 100000.00);
		assertEquals("peti", menadzer.findById(k.getId()).getKorisnickoIme());
	}
	
	@Test
	public void fileMenadzeri() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "menadzeriTest.csv";
		MenadzerManager mmTmp = new MenadzerManager(path);
		mmTmp.loadData();
		
		assertEquals(menadzer, mmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "menadzeriTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }
}
