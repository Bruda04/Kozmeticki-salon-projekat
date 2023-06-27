package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Kozmeticar;
import manage.KozmeticarManager;

public class KozmeticarManagerTest {

	private KozmeticarManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmeticariTest.csv";
		this.menadzer = new KozmeticarManager(path);
		menadzer.add("prvi", "prviIme", "prviPrezime", "prviPol", "prviTelefon", "prviAdresa", "prviLozinka", 4, 1, true, 80000.00, new ArrayList<>());
		ArrayList<Integer> drugiTretmani = new ArrayList<>();
		drugiTretmani.add(1);
		drugiTretmani.add(2);
		menadzer.add("drugi", "drugiIme", "drugiPrezime", "drugiPol", "drugiTelefon", "drugiAdresa", "drugiLozinka", 3, 5, false, 90000.00, drugiTretmani);
	}

	@Test
	public void brojDodatihKozmeticara() {
		assertEquals(2, menadzer.getKozmeticarHashMap().size());
	}
	
	@Test
	public void dodavanjeKozmeticara() {
		menadzer.add("treci", "treciIme", "treciPrezime", "treciPol", "treciTelefon", "treciAdresa", "treciLozinka", 4, 1, true, 80000.00, new ArrayList<>());
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
	public void brisanjeKozmeticaraPoKorisnickomImenu() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00, new ArrayList<>());
		menadzer.deleteByKorisnickoIme("cetvrti");
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void brisanjeKozmeticaraPoId() {
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00, new ArrayList<>());
		menadzer.deleteById(menadzer.findByKorisnickoIme("cetvrti").getId());
		assertTrue(menadzer.findByKorisnickoIme("cetvrti").isObrisan());
	}
	
	@Test
	public void izmenaKozmeticara() {
		ArrayList<Integer> petiUsluge = new ArrayList<>();
		menadzer.add("cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00, petiUsluge);
		Kozmeticar k = menadzer.findByKorisnickoIme("cetvrti");
		ArrayList<Integer> noveUsluge = new ArrayList<>(petiUsluge);
		noveUsluge.add(4);
		menadzer.update(k.getId(), "cetvrti", "cetvrtiIme", "cetvrtiPrezime", "cetvrtiPol", "cetvrtiTelefon", "cetvrtiAdresa", "cetvrtiLozinka", 4, 1, true, 80000.00, noveUsluge);
		assertEquals(noveUsluge, menadzer.findById(k.getId()).getSpisakTretmana());
	}
	
	@Test
	public void fileKozmeticari() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmeticariTest.csv";
		KozmeticarManager kmTmp = new KozmeticarManager(path);
		kmTmp.loadData();
		
		assertEquals(menadzer, kmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmeticariTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }
}
