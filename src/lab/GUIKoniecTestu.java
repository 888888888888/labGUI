package lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;



public class GUIKoniecTestu extends Frame{
	
	private int pytanie1;
	private int pytanie2;
	private int pytanie3;
	private int pytanie4; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	
	
	public GUIKoniecTestu(int pytanie1, int pytanie2, int pytanie3, int pytanie4) throws HeadlessException {

		this.pytanie1 = pytanie1;
		this.pytanie2 = pytanie2;
		this.pytanie3 = pytanie3;
		this.pytanie4 = pytanie4;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Dziekujemy za udzial w quizie :)");
				System.exit(0);
			}
		});
		setSize(400, 400);
		setVisible(true);
	}



	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D prostokat1 = new Rectangle2D.Double(50,100,100,100);
		Rectangle2D prostokat2 = new Rectangle2D.Double(250,100,100,100);
		Rectangle2D prostokat3 = new Rectangle2D.Double(50,250,100,100);
		Rectangle2D prostokat4 = new Rectangle2D.Double(250,250,100,100);
		BasicStroke grubaLinia = new BasicStroke(6.0f);
		
		if (pytanie1 == 1 ){
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.green);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("1", 95, 155);
			g2.draw(prostokat1);
		} else {
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.red);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("1", 95, 155);
			g2.draw(prostokat1);
		}
		
		if (pytanie2 == 1 ){
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.green);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("2", 295, 155);
			g2.draw(prostokat2);
		} else {
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.red);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("2", 295, 155);
			g2.draw(prostokat2);
		}
		
		if (pytanie3 == 1 ){
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.green);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("3", 95, 305);
			g2.draw(prostokat3);
		} else {
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.red);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("3", 95, 305);
			g2.draw(prostokat3);
		}
		
		if (pytanie4 == 1 ){
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.green);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("4", 295, 305);
			g2.draw(prostokat4);
		} else {
			
			g2.setStroke(grubaLinia);
			g2.setColor(Color.red);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	        g2.drawString("4", 295, 305);
			g2.draw(prostokat4);
		}
	}

}