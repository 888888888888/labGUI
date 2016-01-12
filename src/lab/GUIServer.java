package lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;


public class GUIServer extends JFrame implements ActionListener{
	
	private String dane = "";
	private static Connection conn = null;

	private int counter;

	private static String wynik;
	private static String pytania = "";
	
	private JPanel pole;
	private TextField adress;
	private TextField dataBaseName;
	private TextField userName;
	private JTextPane wynikBd;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIServer() throws SQLException {
		
		new DataBase();
		initComponents();
	}
	
	/**
	 * metoda inicjuj¹ca okno
	 */
	private void initComponents() {
		
		JToolBar toolBar = new JToolBar();
		
		JButton buttonConnectDb = new JButton("PolaczZBaza");
		buttonConnectDb.setActionCommand("PolaczZBaza");
		buttonConnectDb.addActionListener(this);
		
		toolBar.add(buttonConnectDb);
		toolBar.addSeparator();
		
		JButton buttonPytania = new JButton("PytaniaIOdpowiedz");
		buttonPytania.setActionCommand("Pytania");
		buttonPytania.addActionListener(this);
		
		toolBar.add(buttonPytania);
		toolBar.addSeparator();
		
		JButton buttonEdycjaBd = new JButton("EdytujBaze");
		buttonEdycjaBd.setActionCommand("Edycja");
		buttonEdycjaBd.addActionListener(this);
		
		toolBar.add(buttonEdycjaBd);
		toolBar.addSeparator();
		
		JButton buttonStartSerwer = new JButton("StartSerwer");
		buttonStartSerwer.setActionCommand("Start");
		buttonStartSerwer.addActionListener(this);
		
		toolBar.add(buttonStartSerwer);
		
		add(toolBar, BorderLayout.NORTH);
		
		initBd();
	}
	
	public void buttonPolaczZBaza() {
		
		getContentPane().remove(pole);
		initBd();
	}
	
	public void initBd() {
		
		GridLayout layout = new GridLayout(0,1);
		
		pole = new JPanel();
		
		// ustawiam domyslne bilae tlo
        pole.setBackground(new java.awt.Color(255, 255, 255));
        pole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pole.setLayout(layout);
        
        GridLayout layout2 = new GridLayout(0,3);
        
        JPanel polaczenie = new JPanel();
        polaczenie.setLayout(layout2);
        
        adress = new TextField();
        adress.setText("adress");
        dataBaseName = new TextField();
        dataBaseName.setText("dataBase");
        userName = new TextField();
        userName.setText("userName");
        
        wynikBd = new JTextPane();
        
        polaczenie.add(adress);
        polaczenie.add(dataBaseName);
        polaczenie.add(userName);
        polaczenie.add(wynikBd);
        
        pole.add(polaczenie);
        
		JButton button = new JButton("PO£¥CZ Z BD");
		button.setActionCommand("ConnectDb");
		button.addActionListener(this);
		pole.add(button);
		

		add(pole);
        
		pack();
		setVisible(true);
	}
	
	public void buttonConnectDb(){
		
		String addresText = adress.getText();
		String dataBaseText = dataBaseName.getText();
		String userNameText = userName.getText();
		
		if (ladujSterownik())
			System.out.print("sterownik OK");
		else
			wynikBd.setText("B³ad po³¹czenia");
		
		conn = connectToDatabase(addresText, dataBaseText, userNameText, "");
		if (conn != null){
			wynikBd.setText("Polaczenie OK");
			System.out.print(" polaczenie OK\n");
		} else {
			wynikBd.setText("B³ad po³¹czenia");
		}
	}
	
	public void buttonPytania(){
	
		try {
			for (int i = 1; i <= 4; i++){
				odczytZBazyDanych(i);
				pytania += String.valueOf(i) + "."+ dane+"\n";
			}
			odczytPoprawnychOdpowiedzi();
			getContentPane().remove(pole);
			initPytania();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initPytania() {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout(0,1);
		
		pole = new JPanel();
		
		// ustawiam domyslne bilae tlo
        pole.setBackground(new java.awt.Color(255, 255, 255));
        pole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pole.setLayout(layout);
        pole.setPreferredSize(new Dimension(300, 300));
        
        JTextArea polePytania = new JTextArea();
        polePytania.setText(pytania);
        
		JTextArea poleWynik = new JTextArea();
		poleWynik.setText(wynik);
		
		pole.add(polePytania);
		pole.add(poleWynik);
		add(pole);
		
		pack();
		setVisible(true);
	}
	
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
	
	public static boolean ladujSterownik() {
		// LADOWANIE STEROWNIKA
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
	}
	
	public static Connection connectToDatabase(String adress, String dataBaseName, String userName, String password) {
		System.out.print("\nLaczenie z baza danych:");
		String baza = "jdbc:mysql://" + adress + "/" + dataBaseName;
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection(baza, userName, password);
		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
		}
		return connection;
	}
	
	public void odczytPoprawnychOdpowiedzi() throws IOException {

		try {

			java.sql.PreparedStatement statement = conn.prepareStatement("SELECT * FROM odpowiedziprawidlowe;");
			java.sql.ResultSet resultset = null;
			resultset = statement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultset.getMetaData();
			int numcols = metaData.getColumnCount();

			wynik = "";

			while (resultset.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = resultset.getObject(i);
					if (obj != null)
						wynik += obj.toString() + " ";
					else
						wynik += " ";
				}
			}
			wynik += "\n";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buttonStart() throws IOException {
		
		getContentPane().remove(pole);
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(4322);
		ExecutorService exec = Executors.newFixedThreadPool(10);
		
		while (true) {
			/*
			 * czekamy na zg³oszenie klienta ...
			 */
			System.out.println("Czekam na polaczenie....");
			Socket socket = serverSocket.accept();
			counter++;

			exec.execute( new FutureTask<int[]>(new SerwerTCP(socket, counter)){
				public void done(){
					
					try {
						
						int wynikiPkt[] = get();
						new GUIWynikGracza(wynikiPkt);
						
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		try {
			Method m = this.getClass().getDeclaredMethod("button"+cmd);// pobranie metody
			m.invoke(this);// wywo³anie metody
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main(String arg[]) throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException{

		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		new GUIServer();
		}
	}

}