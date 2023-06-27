package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.Cenovnik;
import manage.CenovnikManager;

public class CenovnikManagerTest {

	private CenovnikManager menadzer;

	@Before
	public void setUp() throws Exception {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "cenovniciTest.csv";
		this.menadzer = new CenovnikManager(path);
		menadzer.add(new HashMap<>());
		HashMap<Integer, Double> drugiCene = new HashMap<>();
		drugiCene.put(1, 2000.00);
		drugiCene.put(2, 1000.00);
		menadzer.add(drugiCene);
	}

	@Test
	public void brojDodatihTipovaTretmana() {
		assertEquals(2, menadzer.getCenovnikHashMapp().size());
	}
	
	@Test
	public void dodavanjeTipovaTretmana() {
		HashMap<Integer, Double> treciCene = new HashMap<>();
		treciCene.put(1, 3000.00);
		treciCene.put(2, 4000.00);
		menadzer.add(treciCene);
		assertEquals(treciCene, menadzer.findById(3).getCenovnikHasMap());
	}
	
	@Test
	public void pronalazenjePoId() {
		assertEquals(1, menadzer.findById(1).getId());
	}
	
	@Test
	public void brisanjeTipovaTretmanaPoId() {
		int oldSize = menadzer.getCenovnikHashMapp().size();
		menadzer.add(new HashMap<>());
		menadzer.deleteById(3);
		assertEquals(oldSize, menadzer.getCenovnikHashMapp().size());
	}
	
	@Test
	public void izmenaTipovaTretmana() {
		HashMap<Integer, Double> cetvrtiCene = new HashMap<>();
		cetvrtiCene.put(1, 1000.00);
		cetvrtiCene.put(2, 5000.00);
		menadzer.add(cetvrtiCene);
		Cenovnik c = menadzer.findById(3);
		HashMap<Integer, Double> noveCene = new HashMap<>(cetvrtiCene);
		noveCene.put(3, 3000.00);
		noveCene.put(4, 2000.00);
		menadzer.update(c.getId(), noveCene);
		assertEquals(noveCene, menadzer.findById(3).getCenovnikHasMap());
	}
	
	@Test
	public void fileTipovaTretmana() {
        String separator = System.getProperty("file.separator");
        String path = "data" + separator + "cenovniciTest.csv";
		CenovnikManager cmTmp = new CenovnikManager(path);
		cmTmp.loadData();
		
		assertEquals(menadzer, cmTmp);
	}
	
	@AfterClass
    public static void finished() {
		String separator = System.getProperty("file.separator");
        String path = "data" + separator + "cenovniciTest.csv";
        File testFile = new File(path);
        testFile.delete();
    }

}
