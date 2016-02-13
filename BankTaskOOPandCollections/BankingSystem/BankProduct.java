package BankingSystem;

public abstract class BankProduct {
	private final String ime;
	private final boolean kredit;
	private final double godishenLihvenProcent;
	private final int periodNaProdukta;
	private final double mesechnaVnoskaKredit;
	private final double mesechnaLihvaDeposit;
	private double nalichnost;

	BankProduct(String ime, boolean kredit, double godishenLihvenProcent, int periodNaProdukta, double suma) {
		this.ime = ime;
		this.kredit = kredit;
		this.godishenLihvenProcent = godishenLihvenProcent;
		this.periodNaProdukta = periodNaProdukta;
		if (kredit) {
			this.nalichnost = -suma;
			this.mesechnaVnoskaKredit = izchisliavaMesechnaVnoskaDepositIliMesechnaVnoskaKredit(suma);
			this.mesechnaLihvaDeposit = 0;
		} else {
			this.nalichnost = suma;
			this.mesechnaVnoskaKredit = 0;
			this.mesechnaLihvaDeposit = izchisliavaMesechnaVnoskaDepositIliMesechnaVnoskaKredit(suma);
		}
	}

	void showWholeInfo() {
		System.out.print(this.ime + ", godishna lihva " + this.godishenLihvenProcent + "%, period "
				+ this.periodNaProdukta + " meseca, ");
		System.out.println((this.kredit ? ("mesechna lihva " + this.mesechnaVnoskaKredit)
				: ("mesechna vnoska " + this.mesechnaLihvaDeposit)) + " lv.");
	}

	private double izchisliavaMesechnaVnoskaDepositIliMesechnaVnoskaKredit(double suma) {
		return (suma + this.godishenLihvenProcent / 100 * suma) / this.periodNaProdukta;
	}

	String getIme() {
		return this.ime;
	}

	double getGodishenLihvenProcent() {
		return this.godishenLihvenProcent;
	}

	int getPeriodNaProdukta() {
		return this.periodNaProdukta;
	}

	double getNalichnost() {
		return this.nalichnost;
	}

	void setNalichnost(double nalichnost) {
		this.nalichnost = nalichnost;
	}

	double getMesechnaLihvaDepozit() {
		if (!this.kredit) {
			return izchisliavaMesechnaVnoskaDepositIliMesechnaVnoskaKredit(this.nalichnost);
		}
		return 0;
	}

	double getMesechnaVnoskaKredit() {
		return this.mesechnaVnoskaKredit;
	}

	public static class Deposit extends BankProduct {
		public Deposit(String ime, double godishenLihvenProcent, int periodNaProdukta, double suma) {
			super(ime, false, godishenLihvenProcent, periodNaProdukta, suma);
		}
	}

	public static class Credit extends BankProduct {
		public Credit(String ime, double godishenLihvenProcent, int periodNaProdukta, double suma) {
			super(ime, true, godishenLihvenProcent, periodNaProdukta, suma);
		}

	}
}
