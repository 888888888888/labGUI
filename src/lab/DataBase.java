package lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Klasa tworzaca baze danych wraz z rekordami oraz umieszczajadzaca w nich
 * przykladowe dane
 * 
 * @author Krystian Kêdroñ
 *
 */

public class DataBase {

	/**
	 * Metoda s³u¿y do po³¹czenia z MySQL bez wybierania konkretnej bazy
	 * 
	 * @return referencja do uchwytu bazy danych
	 */
	public static Connection getConnection(String adres, int port) {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "");

		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + adres + ":" + port + "/", connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to database");
		return conn;
	}

	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	private static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
		}
		return -1;
	}

	public DataBase() throws SQLException {
		// TODO Auto-generated constructor stub
		
		Connection con = getConnection("localhost", 3306);
		Statement st = createStatement(con);

		// utworzenie bazy
		if (executeUpdate(st, "create Database IF NOT EXISTS quiz;") != -1){
			
			System.out.println("Baza utworzona");
			if (executeUpdate(st, "USE quiz;") != -1)
				System.out.println("Baza wybrana");
			else
				System.out.println("Baza niewybrana!");
		
			if (executeUpdate(st,
					"CREATE TABLE Pytanie ( id INT NOT NULL, pytanie VARCHAR(50) NOT NULL,  PRIMARY KEY (id) );") != -1)
				System.out.println("Tabela utworzona");
			else
				System.out.println("Tabela nie utworzona!");
		
			if (executeUpdate(st, "INSERT INTO Pytanie (id, pytanie) VALUES (1, 'Jaki jest wzor pitagorasa?');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st,
					"INSERT INTO Pytanie (id, pytanie) VALUES (2, 'Data odzyskania przez Polske niepodleglosci?');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st,
					"INSERT INTO Pytanie (id, pytanie) VALUES (3, 'Wynalazca dynamitu to... (samo nazwisko)?');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st, "INSERT INTO Pytanie (id, pytanie) VALUES (4, 'Najszybszym ssakiem jest?');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st,
					"CREATE TABLE Gracz ( id_gracz INT NOT NULL AUTO_INCREMENT, nick VARCHAR(50) NOT NULL,  PRIMARY KEY (id_gracz) );") != -1)
				System.out.println("Tabela utworzona");
			else
				System.out.println("Tabela nie utworzona!");
		
			if (executeUpdate(st,
					"CREATE TABLE OdpowiedziGracza ( id_odpowiedz INT NOT NULL AUTO_INCREMENT, id_gracz INT(10), id INT(10), odpowiedz INT NOT NULL,  PRIMARY KEY (id_odpowiedz), FOREIGN KEY (`id`) REFERENCES `Pytanie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (`id_gracz`) REFERENCES `Gracz` (`id_gracz`) ON DELETE CASCADE ON UPDATE CASCADE);") != -1)
				System.out.println("Tabela utworzona");
			else
				System.out.println("Tabela nie utworzona!");
		
			if (executeUpdate(st,
					"CREATE TABLE OdpowiedziPrawidlowe ( id INT NOT NULL UNIQUE, odpowiedz VARCHAR(50) NOT NULL, FOREIGN KEY (`id`) REFERENCES `Pytanie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE )") != -1)
				System.out.println("Tabela utworzona");
			else
				System.out.println("Tabela nie utworzona!");
		
			if (executeUpdate(st, "INSERT INTO OdpowiedziPrawidlowe (id, odpowiedz) VALUES (1, 'a^2+b^2+c^2');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st, "INSERT INTO OdpowiedziPrawidlowe (id, odpowiedz) VALUES (2, '1918');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st, "INSERT INTO OdpowiedziPrawidlowe (id, odpowiedz) VALUES (3, 'Nobel');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
		
			if (executeUpdate(st, "INSERT INTO OdpowiedziPrawidlowe (id, odpowiedz) VALUES (4, 'Gepart');") != -1) {
				System.out.println("Pytanie utworzone");
			} else
				System.out.println("Pytanie nie utworzone!");
			
		}else
			System.out.println("Baza nieutworzona! Mo¿e ju¿ isnieæ");
		
		st.close();
		con.close();
		
	}

}
