package BankingSystem;

import java.util.HashSet;

public class Klient {

	private String ime;
	private String adres;
	private double parichnaNalichnost;
	private double zaplata;
	private Bank bank;
	private HashSet<BankProduct.Deposit> bankoviDepoziti;
	private HashSet<BankProduct.Credit> bankoviKrediti;

	public Klient(Bank bank, String ime, double parichnaNalichnost, double zaplata) {
		this.bank = bank;
		this.ime = ime;
		this.parichnaNalichnost = parichnaNalichnost;
		this.zaplata = zaplata;
		this.bankoviDepoziti = new HashSet<BankProduct.Deposit>();
		this.bankoviKrediti = new HashSet<BankProduct.Credit>();
	}

	public void iskaneOtkrivaneDepozit(Bank bank, BankProduct.Deposit deposit, double suma) {
		if (this.bank.equals(bank)) {
			if (this.parichnaNalichnost > 0 && this.parichnaNalichnost >= suma && suma > 0) {
				bank.priemaneDepozit(this, deposit, suma);
			}
		}
	}

	public void iskaneOtpuskaneKredit(Bank bank, BankProduct.Credit credit, int periodVMeseci, double suma) {
		if (this.bank.equals(bank) && suma > 0) {
			bank.otpuskaneKredit(credit, periodVMeseci, suma, this);
		}
	}

	void vnasianePariKredit(Bank bank, BankProduct.Credit credit, double suma) {
		if (this.bank.equals(bank) && this.bankoviKrediti.contains(credit) && this.parichnaNalichnost >= suma
				&& suma == credit.getMesechnaVnoskaKredit() && credit.getNalichnost() != 0) {
			this.parichnaNalichnost -= suma;
			bank.poluchavaVnoskaKredit(credit, suma);
		}
	}

	void dobavianeNovDepozitVSpisaka(BankProduct.Deposit deposit) {
		this.bankoviDepoziti.add(deposit);
		this.parichnaNalichnost -= deposit.getNalichnost();
		System.out
				.println(this.ime + " otkri " + deposit.getIme() + " na stoinost " + deposit.getNalichnost() + " lv.");
	}

	void dobavianeNovKreditVSpisaka(BankProduct.Credit credit) {
		this.bankoviKrediti.add(credit);
		this.parichnaNalichnost += -credit.getNalichnost();
		System.out.println(
				this.ime + " iztegli " + credit.getIme() + " na stoinost " + (-credit.getNalichnost()) + " lv.");
	}

	void showWholeInfo() {
		System.out.println("___");
		System.out
				.println(this.ime + ", parichna nalichnost: " + this.parichnaNalichnost + ", zaplata: " + this.zaplata);
		System.out.println("depoziti: " + this.bankoviDepoziti.size());
		showInfoBankDepositsOfThisClient(this.bankoviDepoziti);
		System.out.println("krediti: " + this.bankoviKrediti.size());
		showInfoBankCreditsOfThisClient(this.bankoviKrediti);
	}

	private void showInfoBankDepositsOfThisClient(HashSet<BankProduct.Deposit> deposits) {
		for (BankProduct.Deposit deposit : deposits) {
			deposit.showWholeInfo();
		}
	}

	void showInfoBankCreditsOfThisClient(HashSet<BankProduct.Credit> credits) {
		for (BankProduct credit : credits) {
			credit.showWholeInfo();
		}
	}

	public double getParichnaNalichnost() {
		return this.parichnaNalichnost;
	}

	double getZaplata() {
		return this.zaplata;
	}

	HashSet<BankProduct.Deposit> getBankoviDepoziti() {
		return this.bankoviDepoziti;
	}

	double getObshtaMesechnaVnoskaKrediti() {
		double obshtaMesechnaVnoska = 0;
		for (BankProduct.Credit credit : this.bankoviKrediti) {
			obshtaMesechnaVnoska += credit.getMesechnaVnoskaKredit();
		}
		return obshtaMesechnaVnoska;
	}

	double getObshtaSumaDepoziti() {
		double obshtaSumaDepozit = 0;
		for (BankProduct.Deposit deposit : this.bankoviDepoziti) {
			obshtaSumaDepozit += deposit.getNalichnost();
		}
		return obshtaSumaDepozit;
	}
}
