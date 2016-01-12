package lab;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;

/**
 * W¹tek serwera obsluguj¹cy komunikacje klient-serwer oraz zwracjacy idGracza który zakoñczy quiz 
 * @author Krystian Kêdroñ
 *
 */
public class SerwerTCP implements Callable<int[]>{

	private String dane = "";
	private Connection conn = null;
	private int idGracz;
	
	private int wynikPkt[] = new int[4];

	Socket mySocket;
	private int counter;

	private static String wynik;

	public SerwerTCP(Socket socket, int counter){
		
		mySocket = socket;
		this.counter = counter;
	}

	/**
	 * Funkcja tworzaca rekord Gracz dla danego uzytkownik, pozwalajacy przeprowadzic dla niego quiz
	 * @param counter licznik bedacy jednoczesnie nickiem gracza (licznik odpowida liczbie aktywnych watkow)
	 * @return
	 */
	public boolean utworzGracza(int counter) {

		try {

			String nick = String.valueOf(counter);

			java.sql.PreparedStatement statement = conn.prepareStatement("INSERT INTO gracz (nick) VALUES (?);");
			statement.setString(1, nick);
			statement.execute();

			statement = conn.prepareStatement("SELECT id_gracz FROM gracz WHERE nick = ?;");
			statement.setString(1, nick);

			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();
			while (resultset.next()) {
				idGracz = resultset.getInt("id_gracz");
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * Funkcja odczytujaca pytania z bazy danych
	 * @param nrPytanie  jest to numer pytania jakie chcemy odczytac
	 * @throws IOException
	 */
	public void odczytZBazyDanych(int numerPytania) throws IOException {

		try {

			java.sql.PreparedStatement statement = conn.prepareStatement("SELECT * FROM `pytanie` WHERE id = ?");
			statement.setInt(1, numerPytania);
			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();
			if (resultset.next()) {
				dane = resultset.getString("pytanie");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Funkcja zapisujaca odpowiedz gracza do pliku
	 * @param odp jest to String bedacy odpowiedzia gracza
	 * @param numerPytania jest to numer pytania na ktore gracz odpowiedzial
	 * @return
	 */
	public boolean zapisDoBazyDanych(int odp, int numerPytania) {
		PreparedStatement prepStmt;
		try {

			prepStmt = conn.prepareStatement("INSERT INTO odpowiedzigracza (id_gracz, id, odpowiedz) VALUES (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			prepStmt.setInt(1, idGracz);
			prepStmt.setInt(2, numerPytania);
			prepStmt.setInt(3, odp);
			prepStmt.execute();

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Funkcja inicjalizujaca sterownik do bazyDanych
	 * @return true/false w zaleznosci czy udalo sie zczytac sterownik czy nie
	 */
	public boolean ladujSterownik() {
		// LADOWANIE STEROWNIKA
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
	}

	/**
	 * Funkacja ³¹cz¹ca siê z baz¹ danych
	 * @param adress 
	 * @param dataBaseName
	 * @param userName
	 * @param password
	 * @return uchwyt do polaczenia
	 */
	public Connection connectToDatabase(String adress, String dataBaseName, String userName, String password) {

		String baza = "jdbc:mysql://" + adress + "/" + dataBaseName;
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection(baza, userName, password);
		} catch (SQLException e) {
			System.exit(1);
		}
		return connection;
	}

	/**
	 * Metoda odczytujaca odpowiedzi gracza, aby pozniej wyslac mu je po zakonczeniu quizu
	 * @throws IOException
	 */
	public void odczytOdpowiedziGracza() throws IOException {

		try {

			java.sql.PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM odpowiedzigracza where id_gracz = ?;");
			statement.setInt(1, idGracz);
			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultset.getMetaData();
			int numcols = metaData.getColumnCount();

			wynik = " ";

			while (resultset.next()) {
				for (int i = 3; i <= numcols; i++) {
					Object obj = resultset.getObject(i);
					if (obj != null)
						wynik += " " + obj.toString() + " |";
					else
						wynik += " ";
				}
			}
			wynik += "\n";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Metoda odczytujaca poprawne odpowiedzi, aby pozniej wyslac mu je po zakonczeniu quizu
	 * @throws IOException
	 */
	public void odczytPoprawnychOdpowiedzi() throws IOException {

		try {

			java.sql.PreparedStatement statement = conn.prepareStatement("SELECT * FROM odpowiedziprawidlowe;");
			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultset.getMetaData();
			int numcols = metaData.getColumnCount();

			wynik = " ";

			while (resultset.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = resultset.getObject(i);
					if (obj != null)
						wynik += " " + obj.toString() + " |";
					else
						wynik += " ";
				}
			}
			wynik += "\n";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void odczytPoprawnejOdpowiedzi(int id) throws IOException {

		try {
		
			java.sql.PreparedStatement statement = conn.prepareStatement("SELECT * FROM odpowiedziprawidlowe WHERE id = ? ;");
			statement.setInt(1, id);
			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultset.getMetaData();
			int numcols = metaData.getColumnCount();

			wynik = " ";

			while (resultset.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = resultset.getObject(i);
					if (obj != null)
						wynik += " " + obj.toString() + " |";
					else
						wynik += " ";
				}
			}
			wynik += "\n";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

	/**
	 * Metoda obsulugujaca polaczenie Klient-Serwer
	 * @param odSerwera
	 * @param odKlienta
	 * @param i
	 * @throws IOException
	 */
	private void komunikacjaKlientSerwer(DataOutputStream odSerwera, BufferedReader odKlienta, int i) throws IOException {
		
		String odpowiedz;
		
		odczytPoprawnejOdpowiedzi(i);
		odczytZBazyDanych(i);
		dane = dane + '\n';
		odSerwera.writeBytes(dane);
		odSerwera.writeBytes(wynik);

		odpowiedz = odKlienta.readLine();

		if (sprawdzOdp(odpowiedz, i)) {
			
			System.out.println("Zapisano pytanie"+ i +"! idGracza-> " + idGracz);
		} else {
			
			if(odpowiedz.equals("koniec")){
				wyslijOdpowiedzi(odSerwera, i);
			}
		}
	}
	
	private boolean sprawdzOdp(String odpowiedz, int i) {
		// TODO Auto-generated method stub
	
		if(odpowiedz.equals("koniec")){
			
			return false;
		}
		
		if (i == 1){
			if (odpowiedz.equals("1")){
				if(zapisDoBazyDanych(1, i)){
					
					System.out.println("ODPOWIEDZ PRAWIDLOWA! idGracza-> " +idGracz);
					wynikPkt[0] = 1;
				} 
				
				return true;
			} else {
				if(zapisDoBazyDanych(0, i)){
					
					System.out.println("ODPOWIEDZ NIEPRAWIDLOWA! idGracza-> " +idGracz);
					wynikPkt[0] = 0;
				}
				
				return true;
			}
		} else if(i == 2){
			
			if(odpowiedz.equals("2")){
				if(zapisDoBazyDanych(1,i)){
					
					System.out.println("ODPOIWEDZ PRAWIDLOWA! idGracza-> " +idGracz);
					wynikPkt[1] = 1;
				} 
				
				return true;
			}else {
				if(zapisDoBazyDanych(0, i)){
					
					System.out.println("ODPOWIEDZ NIEPRAWID£OWA! idGracza-> " +idGracz);
					wynikPkt[1] = 0;
				} 
				
				return true;
			}
		} else if(i==3){
			
			if(odpowiedz.equals("3")){
				if(zapisDoBazyDanych(1, i)){
					
					System.out.println("ODPOWIEDZ PRAWIDLOWA! idGracza-> "+idGracz);
					wynikPkt[2] = 1;
				}
				
				return true;
			} else {
				if(zapisDoBazyDanych(0, i)){
					
					System.out.println("ODPOWIEDZ NIEPRAWIDLOWA! idGracza-> " +idGracz);
					wynikPkt[2]= 0;
				} 
				return true;
			}
		} else if(i==4){
			
			if(odpowiedz.equals("4")){
				if(zapisDoBazyDanych(1, i)){
					
					System.out.println("ODPOWIEDZ PRAWIDLOWA! idGracza-> " +idGracz);
					wynikPkt[3]= 1;
				} 
				
				return true;
			} else {
				 if(zapisDoBazyDanych(0, i)){
					 
					 System.out.println("ODPOWIEDZ NIEPRAWIDLOWA! idGracza-> " +idGracz);
					 wynikPkt[3]=0;
				 } 
				 
				 return true;
			}
		}
		
		return false;
	}

	/**
	 * Funkcja wysylajaca odpowiedzi gracza oraz porpawne odpowiedzi do uzytkownika
	 * @param odSerwera
	 * @throws IOException
	 */
	private void wyslijOdpowiedzi(DataOutputStream odSerwera, int j) throws IOException {
		
		// Wysy³anie wynikow quzia do uzytkownika

		for(int i = 0; i < j; i++){
			
			odSerwera.writeInt(wynikPkt[i]);
		}
		
		for (int i = 0; i < (4-j); i++){
			
			wynikPkt[i+j] = 0; 
			odSerwera.writeInt(wynikPkt[i+j]);
		}
		
	}

	/**
	 * Metoda glowna watku obslugujaca powysze funkcjonalnosci
	 * @param argv
	 * @throws Exception
	 */
	public int[] call() {

		try {
			
			new DataBase();
			BufferedReader odKlienta = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			DataOutputStream odSerwera = new DataOutputStream(mySocket.getOutputStream());

			if (ladujSterownik())
			conn = connectToDatabase("127.0.0.1", "quiz", "root", "");
			if (conn == null)
				System.exit(1);

			if (utworzGracza(counter)) {
				System.out.println("Gracz zosta³ utworzony o id =" + idGracz + "!");
			} else {
				System.out.println("Blad podczas tworzenia gracza!");
			}
			
			for(int i = 1; i <= 4; i++){
				
				komunikacjaKlientSerwer(odSerwera, odKlienta, i);
			}

			wyslijOdpowiedzi(odSerwera, 4);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return wynikPkt;
	}
}