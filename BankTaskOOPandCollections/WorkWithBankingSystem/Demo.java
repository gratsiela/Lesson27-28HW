package WorkWithBankingSystem;

import java.util.Random;

import BankingSystem.Bank;
import BankingSystem.Klient;
import BankingSystem.BankProduct;
import BankingSystem.BankProduct.Credit;
import BankingSystem.BankProduct.Deposit;

class Demo {
	public static void main(String[] args) {

		Bank ccb = new Bank("CCB", "Balgaria, Sofia, ul. Aleko Konstantinov, 155");

		Deposit shortDeposit = new Deposit("Short Deposit", 3, 3, 0);
		Deposit longDeposit = new Deposit("Long Deposit", 5, 12, 0);
		Credit homeCredit = new Credit("Home Credit", 6, 0, 0);
		Credit consumerCredit = new Credit("Consumer Credit", 10, 0, 0);
		ccb.addBankProduct(shortDeposit);
		ccb.addBankProduct(longDeposit);
		ccb.addBankProduct(homeCredit);
		ccb.addBankProduct(consumerCredit);

		Klient[] clients = new Klient[10];
		String[] imenaNaKlienti = { "Ivan Ivanov", "Georgi Ivanov", "Petar Dimitrov", "Stoian Todorv",
				"Dimitar Konstantinov", "Nikolai Nikolov", "Simeon Georgiev", "Bozhidar Kirilov", "Blagovest Angelov",
				"Kostadin Angelov", "Georgi Georgiev" };
		for (int i = 0; i < clients.length; i++) {
			Random parichnaNalichnost = new Random();
			Random zaplata = new Random();
			clients[i] = new Klient(ccb, imenaNaKlienti[i], parichnaNalichnost.nextInt(5000),
					zaplata.nextInt(5000) + 400);
		}

		for (Klient klient : clients) {
			Random wantShortDeposit = new Random();
			Random procent = new Random();
			klient.iskaneOtkrivaneDepozit(ccb, wantShortDeposit.nextBoolean() ? shortDeposit : longDeposit,
					klient.getParichnaNalichnost() * (((double) (procent.nextInt(100) + 80)) / 100));
		}

		ccb.showParichnaNalichnostIRezerv();

		for (Klient klient : clients) {
			Random wantHomeCredit = new Random();
			Random periodMeseci = new Random();
			Random suma = new Random();
			klient.iskaneOtpuskaneKredit(ccb, wantHomeCredit.nextBoolean() ? homeCredit : consumerCredit,
					periodMeseci.nextInt(24) + 1, suma.nextInt(3000) + 100);
		}

		ccb.showWholeInfoAboutTheBank();
	}
}
