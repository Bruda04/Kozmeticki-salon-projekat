package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Recepcioner;
import manage.RecepcionerManager;

public class RecepcionerTest {

	private RecepcionerManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "recepcioneriTest.csv";
		this.menadzer = new RecepcionerManager(path);
		menadzer.add("prvi", "prviIme", "prviPrezime", "prviPol", "prviTelefon", "prviAdresa", "prviLozinka", 4, 1, true, 80000.00);
		menadzer.add("drugi", "drugiIme", "drugiPrezime", "drugiPol", "drugiTelefon", "drugiAdresa", "drugiLozinka", 3, 5, false, 90000.00);
	}

	@Test
	public void brojDodatihRecepcionera() {
		assertEquals(2, menadzer.getRecepcionerHashMap().size());
	}
	
	@Test
	public void dodavanjeRecepcionera() {
		menadzer.add("treci", "treciIme", "treciPrezime", "treciPol", "treciTelefon", "treciAdresa", "treciLozinka", 4, 1, true, 80000.00);
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
	public void brisanjeRecepcioneraPoKorisnickomImenu() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00);
		menadzer.deleteByKorisnickoIme("cetvrti");
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void brisanjeRecepcioneraPoId() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00);
		menadzer.deleteById(menadzer.findByKorisnickoIme("cetvrti").getId());
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void izmenaRecepcionera() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00);
		Recepcioner k = menadzer.findByKorisnickoIme("cetvrti");
		menadzer.update(k.getId(), "peti", "petiIme", "petiPrezime", "petiPol", "petiTelefon", "petiAdresa", "petiLozinka", 3, 10, false, 80000.00);
		assertEquals("peti", menadzer.findById(k.getId()).getKorisnickoIme());
	}
	
	@Test
	public void fileRecepcioneri() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "recepcioneriTest.csv";
		RecepcionerManager rmTmp = new RecepcionerManager(path);
		rmTmp.loadData();
		
		assertEquals(menadzer, rmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "recepcioneriTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
