package moje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import artur.Cell;
import artur.WorldMap;

public class GUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static WorldMap mapa = null;
	private int tool = 1;
	int currentX, currentY, oldX, oldY;
	private JPanel jPanel2;
	Cell[][] matrix = null;
	
	/**
	 * metoda inicjuj¹ca okno
	 */
	private void initComponents(WorldMap map) {
		
		//pobieram wygenerowana macierz i przekazuje do klasy rusujacej ja w naszym oknie
		matrix = map.getMatrix();
		jPanel2 = new Panel2(matrix);
		
		// ustawiam domyslne bilae tlo
        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // dodaje obsluge eventow ("Klikniec myszy") i wywoluje metody odpowiedzialne
        // za ich obsluge, zadeklarowane sa ponizej i dodana jest przykladowa logika do nich
        // z czasem ja napewno zmienimy teraz tylko wypisuje na ekranie
        jPanel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        
        // tworze widok naszego GUI (duzo zrobione jak b¹ku robil na swoich przykladach xD)
        add(new JScrollPane(jPanel2));
        // nowy panel gdzie dodamy nasze klawisze (start i exit)
        JPanel panel = new JPanel();
		JButton button = new JButton("Start");
		button.setActionCommand("Start");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("EXIT");
		button.setActionCommand("Stop");
		button.addActionListener(this);
		panel.add(button);;
		add(panel, "South");
        
		//pozniej napewno bedzie trzeba upakowac to w jakis Layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
 
	}
	
	// metody do obslugi klikniec myszy
	 private void jPanel2MouseDragged(MouseEvent evt) {
	        if (tool == 1) {
	            currentX = evt.getX();
	            currentY = evt.getY();
	            oldX = currentX;
	            oldY = currentY;
	            System.out.println(currentX + " " + currentY);
	            System.out.println("Ruch!!!!");
	        }
	    }

	    private void jPanel2MousePressed(MouseEvent evt) {
	        oldX = evt.getX();
	        oldY = evt.getY();
	        System.out.println(oldX + " " + oldY);
	    }


	    //mouse released//
	    private void jPanel2MouseReleased(MouseEvent evt) {
	        if (tool == 2) {
	            currentX = evt.getX();
	            currentY = evt.getY();
	            System.out.println("line!!!! from" + oldX + "to" + currentX);
	        }
	    }

	// generowanie mapy i tworzenie meny
	public GUI(int sizex, int sizey) {
		// TODO Auto-generated constructor stub'
		mapa = new WorldMap(sizex, sizey);
		mapa.create();	
		initComponents(mapa);
		
	}
	
	// wywolanie GUI (tak baku robil)
	public static void main(String arg[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		new GUI(1400,700);
		
	}
		
	}
	
	public void buttonStop(){
		System.out.println("Stop");
	}
	
	public void buttonStart(){
		System.out.println("Start");
	}
	
	// lisiner do klawiszow menu, rowniez tylko zadeklarowany ale juz do tych metod wyzej mozna dodawac logike
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			Method m = this.getClass().getDeclaredMethod("button"+cmd);// pobranie metody
			m.invoke(this);// wywo³anie metody
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
}
