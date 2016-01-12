package lab;

import java.net.*;
import java.io.*;

public class ClientTCP {

	/**
	 * Metoda main Klienta ktora odbiera pytania od serwera, a takze wysyla
	 * odpowiedzi Klienta na serwer
	 * 
	 * @param arg
	 * @throws IOException
	 */
	public static void main(String arg[]) throws IOException {

		String odpoweidz;
		String pytanie;

		BufferedReader odUsera = new BufferedReader(new InputStreamReader(System.in));
		Socket gniazdo = new Socket("localhost", 4322);
		DataOutputStream doSerwera = new DataOutputStream(gniazdo.getOutputStream());
		BufferedReader pytanieSerwera = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));

		for (int i = 0; i <= 3; i++) {

			pytanie = pytanieSerwera.readLine();
			System.out.println("Pytanie: " + pytanie);
			odpoweidz = odUsera.readLine();
			doSerwera.writeBytes(odpoweidz + '\n');
		}

		for (int i = 0; i <= 3; i++) {
			pytanie = pytanieSerwera.readLine();
			System.out.println(pytanie);
		}

		gniazdo.close();
	}
}