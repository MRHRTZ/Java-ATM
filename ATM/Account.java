import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
	// variables
	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'Rp'###,##0.00");

	public Account() {}

	public Account(int customerNumber, int pinNumber) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
	}

	public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}

	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public double getSavingBalance() {
		return savingBalance;
	}

	public double calcCheckingWithdraw(double amount) {
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}

	public double calcSavingWithdraw(double amount) {
		savingBalance = (savingBalance - amount);
		return savingBalance;
	}

	public double calcCheckingDeposit(double amount) {
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;
	}

	public double calcSavingDeposit(double amount) {
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}

	public void calcCheckTransfer(double amount) {
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}

	public void calcSavingTransfer(double amount) {
		savingBalance = savingBalance - amount;
		checkingBalance = checkingBalance + amount;
	}

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
