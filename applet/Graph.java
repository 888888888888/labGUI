package applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.border.BevelBorder;

import mapUtilities.WorldMap2;

public class Graph extends JPanel{
	//JViewport viewport;
	static int sizex = 50, sizey = 50;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WorldMap2 mapToPrint = null;
	Graph(){
		this.setBackground(new java.awt.Color(255, 255, 255));
		//viewport = new JViewport();
		//viewport.add(this);
		//viewport.setBounds(new Rectangle(500,500));
		setPreferredSize(new Dimension(sizex*50,sizey*50));
		mapToPrint = new WorldMap2(sizex,sizey);
		mapToPrint.create();
	}
	public void chooseGraph(int x,int y,Graphics g){
		File file;
		Graphics2D g2d = (Graphics2D) g;
		
		
		if(mapToPrint.getMatrix()[x][y-1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\101.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(mapToPrint.getMatrix()[x+1][y].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\102.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(mapToPrint.getMatrix()[x][y+1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\103.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mapToPrint.getMatrix()[x-1][y].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\104.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mapToPrint.getMatrix()[x-1][y-1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\109.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mapToPrint.getMatrix()[x+1][y-1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\110.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mapToPrint.getMatrix()[x+1][y+1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\111.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mapToPrint.getMatrix()[x-1][y+1].retCoord().retZ() > mapToPrint.getMatrix()[x][y].retCoord().retZ()){
			file = new File("E:\\Studia\\java2015\\T1\\grafiki\\112.png");
			try {
				BufferedImage image =ImageIO.read(file);
				image.createGraphics();
				g2d.drawImage(image,x*50,y*50,null);
				g2d.drawImage(image,x,y,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int height;
		File file;
		file = new File("E:\\Studia\\java2015\\T1\\grafiki\\113.png");
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < mapToPrint.getXSize(); i++) {
			for (int j = 0; j < mapToPrint.getYSize(); j++) {
				height = mapToPrint.getMatrix()[i][j].retId();
				if (height < 25 && height > 0) {
					// g.setColor(new Color(height*10,255,0));
					// g.fillRect(i*50, j*50, 50,50);
					try {
						BufferedImage image = ImageIO.read(file);
						image.createGraphics();
						g2d.drawImage(image, i * 50, j * 50, null);
						g2d.drawImage(image, i, j, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					chooseGraph(i, j, g);
				} else if (height >= 25 && height < 50) {
					g.setColor(new Color(255, 255 - (height - 25) * 10, 0));
					g.fillRect(i * 50, j * 50, 50, 50);
				} else if (height == 50) {
					g.setColor(new Color(255, 0, 0));
					g.fillRect(i * 50, j * 50, 50, 50);
				} else if (mapToPrint.getMatrix()[i][j].retId() == 3564) {
					g.setColor(Color.BLUE);
					g.fillRect(i * 50, j * 50, 50, 50);
					if (i > 1 && j > 1 && i < sizex - 1 && j < sizey - 1)
						chooseGraph(i, j, g);
				} else if (mapToPrint.getMatrix()[i][j].retId() == 0) {
					g.setColor(Color.BLUE);
					g.fillRect(i * 50, j * 50, 50, 50);
					if (i > 1 && j > 1 && i < sizex - 1 && j < sizey - 1)
						chooseGraph(i, j, g);
				} else {
					g.setColor(Color.BLUE);
					g.fillRect(i * 50, j * 50, 50, 50);
					if (i > 1 && j > 1 && i < sizex - 1 && j < sizey - 1)
						chooseGraph(i, j, g);
				}
			}
			/*
			 * if (mapToPrint.getMatrix()[i][j].retId()==0) {
			 * g.setColor(Color.BLUE); g.fillRect(i, j, 1,1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==1){ g.setColor(new
			 * Color(0,255,120)); g.fillRect(i, j, 1,1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==2){ g.setColor(new
			 * Color(0,255,60)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==3){ g.setColor(new
			 * Color(0,255,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==4){ g.setColor(new
			 * Color(60,255,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==5){ g.setColor(new
			 * Color(120,255,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==6){ g.setColor(new
			 * Color(180,255,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==7){ g.setColor(new
			 * Color(240,255,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==8){ g.setColor(new
			 * Color(255,220,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==9){ g.setColor(new
			 * Color(255,160,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==10){ g.setColor(new
			 * Color(255,100,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==11){ g.setColor(new
			 * Color(255,40,0)); g.fillRect(i, j, 1, 1); }else
			 * if(mapToPrint.getMatrix()[i][j].retId()==3564){
			 * g.setColor(Color.BLUE); g.fillRect(i, j, 1, 1); }
			 */
		}
	}
}
