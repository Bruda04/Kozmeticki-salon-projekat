package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.TipTretmana;
import manage.TipTretmanaManager;

public class TipTretmanaManagerTest {

	private TipTretmanaManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "tipoviTretmanaTest.csv";
		this.menadzer = new TipTretmanaManager(path);
		menadzer.add("prvi", new ArrayList<>());
		ArrayList<Integer> drugiUsluge = new ArrayList<>();
		drugiUsluge.add(10);
		drugiUsluge.add(2);
		menadzer.add("drugi", drugiUsluge);
	}

	@Test
	public void brojDodatihTipovaTretmana() {
		assertEquals(2, menadzer.getTipoviTretmanaHashMap().size());
	}
	
	@Test
	public void dodavanjeTipovaTretmana() {
		ArrayList<Integer> treciUsluge = new ArrayList<>();
		treciUsluge.add(10);
		treciUsluge.add(2);
		menadzer.add("treci", treciUsluge);
		assertEquals("treci", menadzer.findByNazivTipaTretmana("treci").getNaziv());
	}
	
	@Test
	public void pronalazenjePoNazivuTipoviTretmana() {
		assertEquals("drugi", menadzer.findByNazivTipaTretmana("drugi").getNaziv());
	}
	
	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}
	
	@Test
	public void brisanjeTipovaTretmanaPoNazivu() {
		menadzer.add("cetvrti", new ArrayList<>());
		menadzer.deleteByNazivTipaTretmana("cetvrti");
		assertTrue(menadzer.findByNazivTipaTretmana("cetvrti").isObrisan());
	}
	
	@Test
	public void brisanjeTipovaTretmanaPoId() {
		menadzer.add("cetvrti", new ArrayList<>());
		menadzer.deleteById(menadzer.findByNazivTipaTretmana("cetvrti").getId());
		assertTrue(menadzer.findByNazivTipaTretmana("cetvrti").isObrisan());
	}
	
	@Test
	public void izmenaTipovaTretmana() {
		ArrayList<Integer> cetvrtiUsluge = new ArrayList<>();
		cetvrtiUsluge.add(123);
		menadzer.add("cetvrti", cetvrtiUsluge);
		TipTretmana tt = menadzer.findByNazivTipaTretmana("cetvrti");
		ArrayList<Integer> petiUsluge = new ArrayList<>(cetvrtiUsluge);
		petiUsluge.add(55);
		petiUsluge.add(33);
		menadzer.update(tt.getId(), "peti", petiUsluge);
		assertEquals(petiUsluge, menadzer.findById(tt.getId()).getSkupTipovaUsluga());
	}
	
	@Test
	public void fileTipovaTretmana() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "tipoviTretmanaTest.csv";
		TipTretmanaManager ttmTmp = new TipTretmanaManager(path);
		ttmTmp.loadData();
		
		assertEquals(menadzer, ttmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "tipoviTretmanaTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }
}
