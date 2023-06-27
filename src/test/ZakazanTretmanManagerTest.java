package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.StanjeZakazanogTretmana;
import entity.ZakazanTretman;
import manage.ZakazanTretmanManager;

public class ZakazanTretmanManagerTest {

	private ZakazanTretmanManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "zakazaniTretmaniTest.csv";
		this.menadzer = new ZakazanTretmanManager(path);
		menadzer.add(LocalDate.of(2023, 7, 15), LocalTime.of(10, 0), 1, 1, 1, 1000.00, 0, StanjeZakazanogTretmana.ZAKAZAN);
		menadzer.add(LocalDate.of(2023, 7, 17), LocalTime.of(10, 0), 1, 2, 2, 2000.00, 0, StanjeZakazanogTretmana.NIJESEPOJAVIO);

	}

	@Test
	public void brojDodatihZakazanihTretmana() {
		assertEquals(2, menadzer.getZakazanTretmanHashMap().size());
	}
	
	@Test
	public void dodavanjeZakazanogTretmana() {
		menadzer.add(LocalDate.of(2023, 7, 15), LocalTime.of(13, 0), 3, 1, 1, 1000.00, 0, StanjeZakazanogTretmana.ZAKAZAN);
		assertEquals(3, menadzer.findById(3).getIdKlijenta());
	}
	
	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}
	
	@Test
	public void brisanjeZakazanogTretmanaPoId() {
		int oldSize = menadzer.getZakazanTretmanHashMap().size();
		menadzer.add(LocalDate.of(2023, 7, 16), LocalTime.of(13, 0), 4, 1, 1, 1000.00, 0, StanjeZakazanogTretmana.ZAKAZAN);
		menadzer.deleteById(3);
		assertEquals(oldSize, menadzer.getZakazanTretmanHashMap().size());
	}
	
	@Test
	public void izmenaZakazanTretman() {
		menadzer.add(LocalDate.of(2023, 7, 16), LocalTime.of(13, 0), 4, 1, 1, 1000.00, 0, StanjeZakazanogTretmana.ZAKAZAN);
		ZakazanTretman zt = menadzer.findById(3);
		menadzer.update(zt.getId(), LocalDate.of(2023, 7, 16), LocalTime.of(13, 0), 4, 1, 1, 1000.00, 0, StanjeZakazanogTretmana.OTKAZAOKLIJENT);
		assertNotEquals(StanjeZakazanogTretmana.ZAKAZAN, menadzer.findById(zt.getId()).getStanje());
	}
	
	@Test
	public void fileZakazanTretman() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "zakazaniTretmaniTest.csv";
		ZakazanTretmanManager ztTmp = new ZakazanTretmanManager(path);
		ztTmp.loadData();
		
		assertEquals(menadzer, ztTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "zakazaniTretmaniTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
