package lab;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;


public class GUIClient extends JFrame implements ActionListener{

	private JTextPane pytanie;
	private JCheckBox checkbox;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	private JCheckBox checkbox4;
	private int selectButton;
	
	private JPanel pole;
	private JPanel menu;
	
	private TextField adress;
	private TextField port;
	private JTextPane wynikPolaczenia;
	
	private DataOutputStream doSerwera;
	private BufferedReader pytanieSerwera;
	private DataInputStream integerFromSerwer;
	
	protected int[] wynikPkt = new int[4];
	private String pytanieText;
	private String odpowiedzText;
	
	private int counter = 1;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIClient() {
		
		initComponents();
	}
	
	private boolean connectToServer(String addres, int port) {
		// TODO Auto-generated method stub
		
		try {
			
			@SuppressWarnings("resource")
//			Socket gniazdo = new Socket("localhost", 4322);
			Socket gniazdo = new Socket(addres, port);
			
			doSerwera = new DataOutputStream(gniazdo.getOutputStream());
			pytanieSerwera = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
			integerFromSerwer = new DataInputStream(gniazdo.getInputStream());
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block

			return false;
		}
		
	}
	
	private void odbierzPytanie(){
		
		try {
			pytanieText = pytanieSerwera.readLine();
			odpowiedzText = pytanieSerwera.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * metoda inicjuj¹ca okno
	 */
	private void initComponents() {
		
		JToolBar toolBar = new JToolBar();
		
		JButton buttonConnect =  new JButton("Polacz");
		buttonConnect.setActionCommand("Polacz");
		buttonConnect.addActionListener(this);
		
	    toolBar.add(buttonConnect);
	    toolBar.addSeparator();
	    
	    JButton buttonStart = new JButton("StartQuiz");
	    buttonStart.setActionCommand("StartQuiz");
	    buttonStart.addActionListener(this);
	    
	    toolBar.add(buttonStart);
	    toolBar.addSeparator();
	    
	    JButton buttonWynik = new JButton("Wynik");
	    buttonWynik.setActionCommand("Wynik");
	    buttonWynik.addActionListener(this);
	    toolBar.add(buttonWynik);
	    
	    add(toolBar, BorderLayout.NORTH);
	    
	    initConnection();		
 
	}
	
	private void initConnection() {
		// TODO Auto-generated method stub

		GridLayout layout = new GridLayout(0,1);
		
		pole = new JPanel();
		pole.setBackground(new java.awt.Color(255, 255, 255));
		pole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pole.setLayout(layout);
		
		GridLayout layout2 = new GridLayout(0,3);
		    
		JPanel polaczenie = new JPanel();
		polaczenie.setLayout(layout2);
		
		adress = new TextField();
		adress.setText("adress");
		
		port = new TextField();
		port.setText("port");
		
		wynikPolaczenia = new JTextPane();
		
		polaczenie.add(adress);
		polaczenie.add(port);
		polaczenie.add(wynikPolaczenia);

		pole.add(polaczenie);
		
		JButton button = new JButton("Po³acz z serwerem");
		button.setActionCommand("ConnectSerwer");
		button.addActionListener(this);
		pole.add(button);
		
		add(pole);

		pack();
		setVisible(true);
	}

	private void initGame() {
		
		odbierzPytanie();
		
		GridLayout layout = new GridLayout(0,1);
		GridLayout layout2 = new GridLayout(0,2);
		
		pole = new JPanel();
		
		// ustawiam domyslne bilae tlo
        pole.setBackground(new java.awt.Color(255, 255, 255));
        pole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pole.setLayout(layout);
		
        pytanie = new JTextPane();
        pytanie.setEditable(false);
        pytanie.setText(pytanieText);
        
        pole.add(pytanie);
        
        checkbox = new JCheckBox(odpowiedzText);
        checkbox.setActionCommand("1");
        checkbox.addActionListener(this);
        
        checkbox2 = new JCheckBox(" 2 | a^2 |");
        checkbox2.setActionCommand("2");
        checkbox2.addActionListener(this);
        
        checkbox3 = new JCheckBox("  3 | b^2 |");
        checkbox3.setActionCommand("3");
        checkbox3.addActionListener(this);
        
        checkbox4 = new JCheckBox(" 4 | c^2 |");
        checkbox4.setActionCommand("4");
        checkbox4.addActionListener(this);
        
    	JPanel pole2 = new JPanel();
		pole2.setLayout(layout2);
		
        pole2.add(checkbox);
        pole2.add(checkbox2);
        pole2.add(checkbox3);
        pole2.add(checkbox4);
        
        pole.add(pole2);
        add(pole);
        
        // nowy panel gdzie dodamy nasze klawisze (start i exit)
        menu = new JPanel();
		JButton button = new JButton("Wyslij");
		button.setActionCommand("Start");
		button.addActionListener(this);
		menu.add(button);
		add(menu, "South");

		pack();
		setVisible(true);
	}
	
	public void button(Integer i) {
		
		odpowiedzText = String.valueOf(i);
		
		if(selectButton != 0){
			if(selectButton == 1){
				checkbox.setSelected(false);
			} else if (selectButton == 2){
				checkbox2.setSelected(false);
			} else if (selectButton == 3){
				checkbox3.setSelected(false);
			} else if (selectButton ==4){
				checkbox4.setSelected(false);
			}
		}
		
		if(checkbox.isSelected()){
			selectButton = 1;
		} else if (checkbox2.isSelected()){
			selectButton = 2;
		} else if (checkbox3.isSelected()){
			selectButton = 3;
		} else if (checkbox4.isSelected()){
			selectButton = 4;
		}
		
	}
	
	public void buttonWynik() throws IOException{
		
		doSerwera.writeBytes("koniec\n");
		
		for (int i = 0; i< 4; i++){

			wynikPkt[i] = integerFromSerwer.readInt();
		}	
		
		new GUIKoniecTestu(wynikPkt[0],wynikPkt[1],wynikPkt[2],wynikPkt[3]);
	}
	
	public void buttonConnectSerwer() {
		
		String addresText = adress.getText();
		int portInt = Integer.valueOf(port.getText());
		
		if(connectToServer(addresText, portInt)){

			wynikPolaczenia.setText("OK");
		} else {
			
			wynikPolaczenia.setText("B£AD");
		}
	}
	
	public void buttonStartQuiz() {

		getContentPane().remove(pole);
		initGame();
		
	}
	
	public void buttonPolacz() {

		getContentPane().remove(pole);
		getContentPane().remove(menu);
		initComponents();
	}
	
	public void buttonStart(){
		
		try {
			
			if (counter != 4){
				doSerwera.writeBytes(odpowiedzText + '\n');
				odbierzPytanie();
				pytanie.setText(pytanieText);
				
				counter++;
				
		        if(counter == 2){
		        
		        	checkbox.setSelected(false);
		        	checkbox.setText(" 1 | 2004 |");
		        	
		        	checkbox2.setSelected(false);
		        	checkbox2.setText(odpowiedzText);
		        	
		        	checkbox3.setSelected(false);
		        	checkbox3.setText(" 3 | 1410 |");
		        	
		        	checkbox4.setSelected(false);
		        	checkbox4.setText("  4 | 1916 |");
		        	
		        	selectButton = 0;
		        }
		        
		        if(counter == 3){
		        	
		         	checkbox.setSelected(false);
		        	checkbox.setText("  1 | Napoleon Bonaparte |");
		        	
		        	checkbox2.setSelected(false);
		        	checkbox2.setText(" 2 | Jan Czochralski | ");
		        	
		        	checkbox3.setSelected(false);
		        	checkbox3.setText(odpowiedzText);
		        	
		        	checkbox4.setSelected(false);
		        	checkbox4.setText(" 4 | Leonardo Da Vinici |");
		        	
		        	selectButton = 0;
		        }
		        
		        if(counter == 4){
		        	
		         	checkbox.setSelected(false);
		        	checkbox.setText(" 1 | Struœ |");
		        	
		        	checkbox2.setSelected(false);
		        	checkbox2.setText("  2 | Lew |");
		        	
		        	checkbox3.setSelected(false);
		        	checkbox3.setText(" 3 | Lampart |");
		        	
		        	checkbox4.setSelected(false);
		        	checkbox4.setText(odpowiedzText);
		        	
		        	selectButton = 0;
		        }
		        
			} else {

				doSerwera.writeBytes(odpowiedzText + '\n');
				for (int i = 0; i< 4; i++){

					wynikPkt[i] = integerFromSerwer.readInt();
				}
				new GUIKoniecTestu(wynikPkt[0],wynikPkt[1],wynikPkt[2],wynikPkt[3]);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		try {
				
			if (cmd.equals("Start") || cmd.equals("StartQuiz") || cmd.equals("Polacz") || cmd.equals("ConnectSerwer") || cmd.equals("Wynik")){
				
				Method m = this.getClass().getDeclaredMethod("button"+cmd);// pobranie metody
				m.invoke(this);// wywo³anie metody
			} else {
				
				int liczba = Integer.valueOf(cmd);
				Method m = this.getClass().getDeclaredMethod("button", new Class[]{Integer.class});// pobranie metody
				m.invoke(this,liczba);// wywo³anie metody
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main(String arg[]) throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		new GUIClient();
		}
	}

}
