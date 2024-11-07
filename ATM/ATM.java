import java.io.IOException;

/**
 * Kelas ATM adalah titik masuk utama untuk aplikasi mesin ATM.
 * Kelas ini menampilkan pengantar dan memulai menu utama.
 */
public class ATM {

	/**
	 * Metode utama yang menjalankan aplikasi ATM.
	 * 
	 * @param args Argumen baris perintah.
	 * @throws IOException Jika terjadi kesalahan input/output.
	 */
	public static void main(String[] args) throws IOException {
		OptionMenu optionMenu = new OptionMenu();
		introduction();
		optionMenu.mainMenu();
	}

	/**
	 * Menampilkan pesan pengantar ke pengguna.
	 */
	public static void introduction() {
		System.out.println("Selamat datang di Project ATM");
	}
}
