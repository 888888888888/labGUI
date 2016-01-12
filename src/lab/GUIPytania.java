package lab;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
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
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;


public class GUIPytania extends JFrame implements ActionListener{
	
	private String pytania;
	private String wynik;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIPytania(String pytania, String wynik) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		this.pytania = pytania;
		this.wynik = wynik;
		
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
			initComponents();
		}
	}
	
	/**
	 * metoda inicjuj¹ca okno
	 */
	private void initComponents() {
		
		GridLayout layout = new GridLayout(0,1);
		
		JPanel pole = new JPanel();
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}