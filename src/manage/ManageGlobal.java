package manage;

import entity.*;
import utils.AppSettings;

public class ManageGlobal {
	private final AppSettings appSettings;
	private final KlijentManager klijentMngr;
	private final MenadzerManager menadzerMngr;
	private final RecepcionerManager recepcionerMngr;
	private final KozmeticarManager kozmeticarMngr;
	private final CenovnikManager cenovnikMngr;
	private final TipTretmanaManager tipTretmanaMngr;
	private final ZakazanTretmanManager zakazanTretmanMngr;
	private final UslugaManager uslugaMngr;
	private final KozmetickiSalonManager kozmetickiSalonMngr;

	public ManageGlobal(AppSettings appSettings) {
		this.appSettings = appSettings;
		this.klijentMngr = new KlijentManager(this.appSettings.getKLijentiFilename());
		this.menadzerMngr = new MenadzerManager(this.appSettings.getMenadzeriFilename());
		this.recepcionerMngr = new RecepcionerManager(this.appSettings.getRecepcioneriFilename());
		this.cenovnikMngr = new CenovnikManager(this.appSettings.getCenovnikFilename());
		this.kozmeticarMngr = new KozmeticarManager(this.appSettings.getKozmeticariFilename());
		this.zakazanTretmanMngr = new ZakazanTretmanManager(this.appSettings.getZakazaniTretmanFilename());
		this.tipTretmanaMngr = new TipTretmanaManager(this.appSettings.getTipTretmanaFilename());
		this.uslugaMngr = new UslugaManager(this.appSettings.getUslugaFilename());
		this.kozmetickiSalonMngr = new KozmetickiSalonManager(this.appSettings.getKozmetickiSalonFilename());
	}

	public KlijentManager getKlijentMngr() {return klijentMngr;}
	public MenadzerManager getMenadzerMngr() {return menadzerMngr;}
	public RecepcionerManager getRecepcionerMngr() {return recepcionerMngr;}
	public CenovnikManager getCenovnikMngr() {return cenovnikMngr;}
	public TipTretmanaManager getTipTretmanaMngr() {return tipTretmanaMngr;}
	public ZakazanTretmanManager getZakazanTretmanMngr() {return zakazanTretmanMngr;}
	public  UslugaManager getUslugaMngr() {return  uslugaMngr;}
	public KozmeticarManager getKozmeticarMngr() {return kozmeticarMngr;}
	public KozmetickiSalonManager getKozmetickiSalonMngr() {return kozmetickiSalonMngr;}

	public void loadData() {
		this.klijentMngr.loadData();
		this.menadzerMngr.loadData();
		this.recepcionerMngr.loadData();
		this.cenovnikMngr.loadData();
		this.tipTretmanaMngr.loadData();
		this.zakazanTretmanMngr.loadData();
		this.kozmeticarMngr.loadData();
		this.uslugaMngr.loadData();
		this.kozmetickiSalonMngr.loadData();
	}

	public void prikazKozmetickiSalon(){
			System.out.println();
		for (KozmetickiSalon ks: this.kozmetickiSalonMngr.getkozmetickiSalonHashMap().values()
		) {
			System.out.println(ks);
		}
			System.out.println();
	}

	public void prikazKorisnici() {
		System.out.println();
		System.out.println("#####Menadzeri#####");
		for (Menadzer m: this.menadzerMngr.getMenadzerHashMap().values()
		) {
			System.out.println(m);
		}
		System.out.println();
		System.out.println("#####Recepcioneri#####");
		for (Recepcioner r: this.recepcionerMngr.getRecepcionerHashMap().values()
		) {
			System.out.println(r);
		}
		System.out.println();
		System.out.println("#####Kozmeticari#####");
		for (Kozmeticar k: this.kozmeticarMngr.getKozmeticarHashMap().values()
		) {
			System.out.println(k);
		}
		System.out.println();
		System.out.println("#####Klijenti#####");
		for (Klijent k: this.klijentMngr.getKlijentHashMap().values()
		) {
			System.out.println(k);
		}
	}

    public void prikazTretmanUslugaCena() {
		System.out.println();
		for (TipTretmana tt : this.tipTretmanaMngr.getTipoviTretmanaHashMap().values()) {
			System.out.printf("#####%s#####\n", tt.getNaziv().toUpperCase());
			for (int uId: tt.getSkupTipovaUsluga()) {
				Usluga u = this.uslugaMngr.findById(uId);
				double uCena = this.cenovnikMngr.findById(1).getCenovnikHasMap().get(u.getId());
				System.out.printf("Naziv: %s, Trajanje(minuti): %s, Tip tretmana: %s, Cena: %.2f\n", u.getNazivUsluge(), u.getTrajanjeUsluge(), tt.getNaziv(), uCena);
			}
		}
    }

	public void prikaziZakazaneTretmane() {
		System.out.println();
		System.out.println("#####ZAKAZANI TRETMANI#####");
		for (ZakazanTretman zt: this.zakazanTretmanMngr.getZakazanTretmanHashMap().values()) {
			System.out.printf("Klijent: %s, Kozmeticar: %s, Datum: %s, Vreme: %s, Tip usluge: %s, Cena: %.2f, Stanje: %s\n",
					this.klijentMngr.findById(zt.getIdKlijenta()).getKorisnickoIme(),
					this.kozmeticarMngr.findById(zt.getIdKozmeticara()).getKorisnickoIme(),
					zt.getDatum(),
					zt.getVremeFormatStr(),
					this.uslugaMngr.findById(zt.getIdTipaUsluge()).getNazivUsluge(),
					zt.getCena(),
					zt.getStanje()
					);
		}
	}
}
