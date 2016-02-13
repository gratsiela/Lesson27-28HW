package BankingSystem;

import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Bank {

	private String ime;
	private String adres;
	HashSet<BankProduct> bankoviProdukti;
	HashSet<Klient> klienti;
	private double parichnaNalichnost;
	private double bankovRezerv;

	public Bank(String ime, String adres) {
		this.ime = ime;
		this.adres = adres;
		this.bankoviProdukti = new HashSet<BankProduct>();
		this.klienti = new HashSet<Klient>();
		this.parichnaNalichnost = 100000;
		this.bankovRezerv = 100000;
	}

	void priemaneDepozit(Klient klient, BankProduct.Deposit primerenDeposit, double suma) {
		if (this.bankoviProdukti.contains(primerenDeposit) && suma >= 100) {
			BankProduct.Deposit deposit = new BankProduct.Deposit(primerenDeposit.getIme(),
					primerenDeposit.getGodishenLihvenProcent(), primerenDeposit.getPeriodNaProdukta(), suma);
			if (!this.klienti.contains(klient)) {
				this.klienti.add(klient);
			}
			klient.dobavianeNovDepozitVSpisaka(deposit);
			this.parichnaNalichnost += deposit.getNalichnost();
			this.bankovRezerv += 0.9 * deposit.getNalichnost();
		}
	}

	void izplashtaneLihvitePoVsichkiDepoziti() {
		for (Klient k : this.klienti) {
			for (BankProduct.Deposit d : k.getBankoviDepoziti()) {
				if (d.getNalichnost() != 0) {
					this.parichnaNalichnost -= d.getMesechnaLihvaDepozit();
					d.setNalichnost(d.getNalichnost() + d.getMesechnaLihvaDepozit());
				}
			}
		}
	}

	void otpuskaneKredit(BankProduct.Credit primerenKredit, int periodVMeseci, double suma, Klient klient) {
		if (this.bankoviProdukti.contains(primerenKredit) && suma >= 100) {
			BankProduct.Credit credit = new BankProduct.Credit(primerenKredit.getIme(),
					primerenKredit.getGodishenLihvenProcent(), periodVMeseci, suma);
			if (klient.getObshtaMesechnaVnoskaKrediti() <= 0.5 * klient.getZaplata()
					&& -credit.getNalichnost() <= this.bankovRezerv) {
				if (this.parichnaNalichnost - suma > izchisliavaObshtaSumaPoDepositiVBankata() * 0.1) {
					if (!this.klienti.contains(klient)) {
						this.klienti.add(klient);
					}
					klient.dobavianeNovKreditVSpisaka(credit);
					this.parichnaNalichnost -= -credit.getNalichnost();
				} else
					credit = null;
			}
		}
	}

	void poluchavaVnoskaKredit(BankProduct.Credit credit, double suma) {
		credit.setNalichnost(credit.getNalichnost() + suma);
		this.parichnaNalichnost += suma;
	}

	public void addBankProduct(BankProduct bankProduct) {
		this.bankoviProdukti.add(bankProduct);
	}

	public void showWholeInfoAboutTheBank() {
		System.out.println("\n-----\n" + this.ime + "\n-----");
		showParichnaNalichnostIRezerv();
		showBankoviProdukti();
		showKlienti();
	}

	public void showParichnaNalichnostIRezerv() {
		System.out.println("\nPARICHNA NALICHNOST: " + this.parichnaNalichnost);
		System.out.println("PARICHEN REZERV:: " + this.bankovRezerv + "\n");
	}

	void showBankoviProdukti() {
		System.out.println("\nPRODUKTI: ");
		for (BankProduct product : this.bankoviProdukti) {
			((BankProduct) product).getIme();
			System.out.print(((BankProduct) product).getIme() + ": "
					+ ((BankProduct) product).getGodishenLihvenProcent() + "% lihva");
			if (product instanceof BankProduct.Deposit) {
				System.out.print(", " + ((BankProduct) product).getPeriodNaProdukta() + " meseca period");
			}
			System.out.println();
		}
	}

	void showKlienti() {
		System.out.println("\nKLIENTI::");
		for (Klient klient : this.klienti) {
			klient.showWholeInfo();
		}
	}

	private double izchisliavaObshtaSumaPoDepositiVBankata() {
		double obshtaSumaPoDepoziti = 0;
		for (Klient klient : this.klienti) {
			obshtaSumaPoDepoziti += klient.getObshtaSumaDepoziti();
		}
		return obshtaSumaPoDepoziti;
	}

	
}
