package lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;

public class GUIWynikGracza extends Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] wynikPkt;
	private int wynik;
	
	public GUIWynikGracza(int[] wynikiPkt) {
		// TODO Auto-generated constructor stub
		this.wynikPkt = wynikiPkt;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Dziekujemy za udzial w quizie :)");
				System.exit(0);
			}
		});
		setSize(480, 400);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		
		przeliczPkt();
		System.out.println("doszlo3!");
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D prostokat1 = new Rectangle2D.Double(0,0,50,50);
//		Rectangle2D prostokat2 = new Rectangle2D.Double(140,20,100,100);
//		Rectangle2D prostokat3 = new Rectangle2D.Double(260,20,100,100);
//		Rectangle2D prostokat4 = new Rectangle2D.Double(360,20,100,100);
		BasicStroke grubaLinia = new BasicStroke(6.0f);
		
		g2.setStroke(grubaLinia);
		g2.setColor(Color.green);
		g2.draw(prostokat1);
			
	}

	private void przeliczPkt() {
		System.out.println("doszlo!");
		// TODO Auto-generated method stub
		for(int i = 0; i < 4; i++){
			System.out.println("doszlo2!");
			if(wynikPkt[i] == 1){
				wynik++;
				System.out.println(wynik);
			}
		}
	}

}
