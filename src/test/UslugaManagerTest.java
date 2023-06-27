package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Usluga;
import manage.UslugaManager;

public class UslugaManagerTest {

	private UslugaManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "uslugeTest.csv";
		this.menadzer = new UslugaManager(path);
		menadzer.add("prva", 45);
		menadzer.add("druga", 30);
	}

	@Test
	public void brojDodatihUsluga() {
		assertEquals(2, menadzer.getUslugeHashMap().size());
	}
	
	@Test
	public void dodavanjeUsluga() {
		menadzer.add("treca", 90);
		assertEquals("treca", menadzer.findByNazivUsluge("treca").getNazivUsluge());
	}
	
	@Test
	public void pronalazenjePoNazivuUsluge() {
		assertEquals("druga", menadzer.findByNazivUsluge("druga").getNazivUsluge());
	}
	
	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}
	
	@Test
	public void brisanjeUslugaPoNazivuUsluge() {
		menadzer.add("cetvrta", 100);
		menadzer.deleteByNazivUsluge("cetvrta");
		assertTrue(menadzer.findByNazivUsluge("cetvrta").isObrisan());
	}
	
	@Test
	public void brisanjeUslugaPoId() {
		menadzer.add("cetvrta", 100);
		menadzer.deleteById(menadzer.findByNazivUsluge("cetvrta").getId());
		assertTrue(menadzer.findByNazivUsluge("cetvrta").isObrisan());
	}
	
	@Test
	public void izmenaUsluga() {
		menadzer.add("cetvrta", 100);
		Usluga u = menadzer.findByNazivUsluge("cetvrta");
		menadzer.update(u.getId(), "peta", 90);
		assertEquals("peta", menadzer.findById(u.getId()).getNazivUsluge());
	}
	
	@Test
	public void fileUsluga() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "uslugeTest.csv";
		UslugaManager umTmp = new UslugaManager(path);
		umTmp.loadData();
		
		assertEquals(menadzer, umTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "uslugeTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
