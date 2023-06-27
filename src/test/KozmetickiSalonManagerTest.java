package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalTime;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.KozmetickiSalon;
import manage.KozmetickiSalonManager;

public class KozmetickiSalonManagerTest {

	private KozmetickiSalonManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmetickiSaloniTest.csv";
		this.menadzer = new KozmetickiSalonManager(path);
		menadzer.add("prvi", LocalTime.of(8, 0), LocalTime.of(22, 0), 0.00, 1000.00, 2500.00);
		menadzer.add("prvi", LocalTime.of(10, 0), LocalTime.of(22, 0), 10000.00, 1000.00, 3500.00);
	}

	@Test
	public void brojDodatihKozmetickihSalona() {
		assertEquals(2, menadzer.getkozmetickiSalonHashMap().size());
	}
	
	@Test
	public void dodavanjeKozmetickihSalona() {
		menadzer.add("treci", LocalTime.of(9, 0), LocalTime.of(20, 0), 0.00, 1000.00, 2500.00);
		assertEquals("treci", menadzer.findById(3).getNaziv());
	}

	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}

	@Test
	public void brisanjeKozmetickihSalonaPoId() {
		int oldSize = menadzer.getkozmetickiSalonHashMap().size();
		menadzer.add("cetvrti", LocalTime.of(9, 0), LocalTime.of(20, 0), 0.00, 2000.00, 2500.00);
		menadzer.deleteById(3);
		assertEquals(oldSize, menadzer.getkozmetickiSalonHashMap().size());
	}
	
	@Test
	public void izmenaKozmetickiSalona() {
		menadzer.add("cetvrti", LocalTime.of(9, 0), LocalTime.of(20, 0), 0.00, 2000.00, 2500.00);
		KozmetickiSalon k = menadzer.findById(3);
		menadzer.update(k.getId(), "peti", LocalTime.of(9, 0), LocalTime.of(20, 0), 0.00, 2000.00, 2500.00);
		assertEquals("peti", menadzer.findById(k.getId()).getNaziv());
	}
	
	@Test
	public void fileKozmetickiSalon() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmetickiSaloniTest.csv";
		KozmetickiSalonManager ksTmp = new KozmetickiSalonManager(path);
		ksTmp.loadData();
		
		assertEquals(menadzer, ksTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "kozmetickiSaloniTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
