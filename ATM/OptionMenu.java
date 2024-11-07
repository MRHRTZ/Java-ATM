import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'Rp'###,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();

	public void getLogin() throws IOException {
		boolean end = false;
		int customerNumber = 0;
		int pinNumber = 0;
		while (!end) {
			try {
				System.out.print("\nMasukan nomor rekening mu: ");
				customerNumber = menuInput.nextInt();
				System.out.print("\nMasukan kode PIN: ");
				pinNumber = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Account acc = (Account) pair.getValue();
					if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
						getAccountType(acc);
						end = true;
						break;
					}
				}
				if (!end) {
					System.out.println("\nNo rekening atau PIN salah.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nKarakter tidak valid, hanya menerima angka.");
			}
		}
	}

	public void getAccountType(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nPilih akun yang akan kamu akses: ");
				System.out.println(" 1 - Rekening Giro");
				System.out.println(" 2 - Rekening Tabungan");
				System.out.println(" 3 - Keluar");
				System.out.print("\nPilihan: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					getChecking(acc);
					break;
				case 2:
					getSaving(acc);
					break;
				case 3:
					end = true;
					break;
				default:
					System.out.println("\nPilihan tidak valid.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				menuInput.next();
			}
		}
	}

	public void getChecking(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nRekening Giro: ");
				System.out.println(" 1 - Lihat Saldo");
				System.out.println(" 2 - Tarik Tunai");
				System.out.println(" 3 - Setor");
				System.out.println(" 4 - Transfer");
				System.out.println(" 5 - Keluar");
				System.out.print("\nPilihan: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					System.out.println("\nSaldo Rekening Giro: " + moneyFormat.format(acc.getCheckingBalance()));
					break;
				case 2:
					acc.getCheckingWithdrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;

				case 4:
					acc.getTransferInput("Checkings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nPilihan tidak valid.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				menuInput.next();
			}
		}
	}

	public void getSaving(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nRekening Tabungan: ");
				System.out.println(" 1 - Lihat Saldo");
				System.out.println(" 2 - Tarik Tunai");
				System.out.println(" 3 - Setor");
				System.out.println(" 4 - Transfer");
				System.out.println(" 5 - Keluar");
				System.out.print("\nPilihan: ");
				int selection = menuInput.nextInt();
				switch (selection) {
				case 1:
					System.out.println("\nSaldo Rekening Tabungan: " + moneyFormat.format(acc.getSavingBalance()));
					break;
				case 2:
					acc.getsavingWithdrawInput();
					break;
				case 3:
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Savings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nPilihan tidak valid.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				menuInput.next();
			}
		}
	}

	public void createAccount() throws IOException {
		int cst_no = 0;
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nMasukan nomor rekening baru");
				cst_no = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					if (!data.containsKey(cst_no)) {
						end = true;
					}
				}
				if (!end) {
					System.out.println("\nNomor rekening ini telah terdaftar");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				menuInput.next();
			}
		}
		System.out.println("\nMasukan PIN baru");
		int pin = menuInput.nextInt();
		data.put(cst_no, new Account(cst_no, pin));
		System.out.println("\nAkun kamu berhasil terdaftar!");
		System.out.println("\nMengalihkan ke halaman login.............");
		getLogin();
	}

	public void mainMenu() throws IOException {
		data.put(952141, new Account(952141, 191904, 1000, 5000));
		data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\n 1 - Masuk");
				System.out.println(" 2 - Buat Akun");
				System.out.print("\nPilihan: ");
				int choice = menuInput.nextInt();
				switch (choice) {
				case 1:
					getLogin();
					end = true;
					break;
				case 2:
					createAccount();
					end = true;
					break;
				default:
					System.out.println("\nPilihan tidak valid.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				menuInput.next();
			}
		}
		System.out.println("\nTerimakasih telah menggunakan ATM ini.\n");
		menuInput.close();
		System.exit(0);
	}
}
