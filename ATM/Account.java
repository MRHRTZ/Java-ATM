import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Kelas Account merepresentasikan akun bank dengan nomor pelanggan, nomor pin, 
 * saldo rekening giro, dan saldo tabungan. Kelas ini menyediakan metode untuk 
 * melakukan penarikan, penyetoran, dan transfer dana antar rekening.
 */
public class Account {
	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'Rp'###,##0.00");

	/**
	 * Konstruktor default untuk kelas Account.
	 */
	public Account() {}

	/**
	 * Konstruktor untuk kelas Account dengan nomor pelanggan dan nomor pin.
	 * 
	 * @param customerNumber Nomor pelanggan.
	 * @param pinNumber Nomor pin.
	 */
	public Account(int customerNumber, int pinNumber) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
	}

	/**
	 * Konstruktor untuk kelas Account dengan beberapa parameter.
	 * 
	 * @param customerNumber Nomor pelanggan.
	 * @param pinNumber Nomor pin.
	 * @param checkingBalance Saldo rekening giro.
	 * @param savingBalance Saldo tabungan.
	 */
	public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}

	/**
	 * Mengatur nomor pelanggan.
	 * 
	 * @param customerNumber Nomor pelanggan.
	 * @return Nomor pelanggan yang telah diatur.
	 */
	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	/**
	 * Mengambil nomor pelanggan.
	 * 
	 * @return Nomor pelanggan.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Mengatur nomor pin.
	 * 
	 * @param pinNumber Nomor pin.
	 * @return Nomor pin yang telah diatur.
	 */
	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

	/**
	 * Mengambil nomor pin.
	 * 
	 * @return Nomor pin.
	 */
	public int getPinNumber() {
		return pinNumber;
	}

	/**
	 * Mengambil saldo rekening giro.
	 * 
	 * @return Saldo rekening giro.
	 */
	public double getCheckingBalance() {
		return checkingBalance;
	}

	/**
	 * Mengambil saldo tabungan.
	 * 
	 * @return Saldo tabungan.
	 */
	public double getSavingBalance() {
		return savingBalance;
	}

	/**
	 * Menghitung penarikan dari rekening giro.
	 * 
	 * @param amount Jumlah yang akan ditarik.
	 * @return Saldo rekening giro setelah penarikan.
	 */
	public double calcCheckingWithdraw(double amount) {
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}

	/**
	 * Menghitung penarikan dari tabungan.
	 * 
	 * @param amount Jumlah yang akan ditarik.
	 * @return Saldo tabungan setelah penarikan.
	 */
	public double calcSavingWithdraw(double amount) {
		savingBalance = (savingBalance - amount);
		return savingBalance;
	}

	/**
	 * Menghitung penyetoran ke rekening giro.
	 * 
	 * @param amount Jumlah yang akan disetor.
	 * @return Saldo rekening giro setelah penyetoran.
	 */
	public double calcCheckingDeposit(double amount) {
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;
	}

	/**
	 * Menghitung penyetoran ke tabungan.
	 * 
	 * @param amount Jumlah yang akan disetor.
	 * @return Saldo tabungan setelah penyetoran.
	 */
	public double calcSavingDeposit(double amount) {
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}

	/**
	 * Menghitung transfer dari rekening giro ke tabungan.
	 * 
	 * @param amount Jumlah yang akan ditransfer.
	 */
	public void calcCheckTransfer(double amount) {
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}

	/**
	 * Menghitung transfer dari tabungan ke rekening giro.
	 * 
	 * @param amount Jumlah yang akan ditransfer.
	 */
	public void calcSavingTransfer(double amount) {
		savingBalance = savingBalance - amount;
		checkingBalance = checkingBalance + amount;
	}

	/**
	 * Mengambil input penarikan dari rekening giro dari pengguna.
	 */
	public void getCheckingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
				System.out.print("\nJumlah yang ingin Anda tarik dari Rekening: ");
				double amount = input.nextDouble();
				if ((checkingBalance - amount) >= 0 && amount >= 0) {
					calcCheckingWithdraw(amount);
					System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nSaldo tidak boleh kurang dari 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				input.next();
			}
		}
	}

	/**
	 * Mengambil input penarikan dari tabungan dari pengguna.
	 */
	public void getsavingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSaldo Tabungan Saat Ini: " + moneyFormat.format(savingBalance));
				System.out.print("\nJumlah yang ingin Anda tarik dari Tabungan: ");
				double amount = input.nextDouble();
				if ((savingBalance - amount) >= 0 && amount >= 0) {
					calcSavingWithdraw(amount);
					System.out.println("\nSaldo Tabungan Saat Ini: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nSaldo tidak boleh kurang dari 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				input.next();
			}
		}
	}

	/**
	 * Mengambil input penyetoran ke rekening giro dari pengguna.
	 */
	public void getCheckingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
				System.out.print("\nJumlah yang ingin Anda setor ke Rekening: : ");
				double amount = input.nextDouble();
				if ((checkingBalance + amount) >= 0 && amount >= 0) {
					calcCheckingDeposit(amount);
					System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nSaldo tidak boleh kurang dari 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				input.next();
			}
		}
	}

	/**
	 * Mengambil input penyetoran ke tabungan dari pengguna.
	 */
	public void getSavingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSaldo Tabungan Saat Ini: " + moneyFormat.format(savingBalance));
				System.out.print("\nJumlah yang ingin Anda setorkan ke Rekening Tabungan Anda: ");
				double amount = input.nextDouble();

				if ((savingBalance + amount) >= 0 && amount >= 0) {
					calcSavingDeposit(amount);
					System.out.println("\nSaldo Tabungan Saat Ini: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nSaldo tidak boleh kurang dari 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				input.next();
			}
		}
	}

	/**
	 * Mengambil input transfer dana dari pengguna.
	 * 
	 * @param accType Jenis akun yang akan ditransfer (Checkings atau Savings).
	 */
	public void getTransferInput(String accType) {
		boolean end = false;
		while (!end) {
			try {
				if (accType.equals("Checkings")) {
					System.out.println("\nPilih rekening yang ingin Anda transfer dana:");
					System.out.println("1. Tabungan");
					System.out.println("2. Keluar");
					System.out.print("\nPilihan: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
						System.out.print("\nJumlah yang ingin Anda setorkan ke Rekening Tabungan Anda: ");
						double amount = input.nextDouble();
						if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
							calcCheckTransfer(amount);
							System.out.println("\nSaldo Rekening Tabungan saat ini: " + moneyFormat.format(savingBalance));
							System.out.println(
									"\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
							end = true;
						} else {
							System.out.println("\nSaldo tidak boleh kurang dari 0.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nPilihan tidak valid.");
						break;
					}
				} else if (accType.equals("Savings")) {
					System.out.println("\nPilih rekening yang ingin Anda transfer dana: ");
					System.out.println("1. Rekening Giro");
					System.out.println("2. Keluar");
					System.out.print("\nPilihan: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nSaldo Rekening Tabungan saat ini: " + moneyFormat.format(savingBalance));
						System.out.print("\nJumlah yang ingin Anda setorkan ke rekening tabungan Anda: ");
						double amount = input.nextDouble();
						if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
							calcSavingTransfer(amount);
							System.out.println("\nSaldo Rekening Saat Ini: " + moneyFormat.format(checkingBalance));
							System.out.println("\nSaldo Rekening Tabungan saat ini: " + moneyFormat.format(savingBalance));
							end = true;
						} else {
							System.out.println("\nSaldo tidak boleh kurang dari 0.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nPilihan tidak valid.");
						break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPilihan tidak valid.");
				input.next();
			}
		}
	}
}
