package applet;

import javax.swing.JApplet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import mapUtilities.*;
import mapUtilities.WorldMap2;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Mainframe extends JApplet {
	private static final long serialVersionUID = 1L;
	static WorldMap2 mapa = null;
	JPanel panel1;//=new Graph();
	JViewport viewport;
	int dX=0,
	dY=0;
	
	/**
	 * Create the applet.
	 */
	public Mainframe() {
		init();
	}
	
	public void init(){
		getContentPane().setLayout(null);
		panel1 =new Graph();
		panel1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				jPanel1MousePressed(evt);
			}
		});
		panel1.setBounds(0, 0, 5,5);
		//panel_1.addMouseListener(l);
		//viewport.setOpaque(true);
		//viewport.setBounds(0,0 , 800, 800);
		//viewport.add(panel1);
		getContentPane().add(panel1);
	}

	private void jPanel1MousePressed(MouseEvent evt) {
		dX = evt.getX();
		dY = evt.getY();
		System.out.println(-dX + " " + -dY);
		panel1.setBounds(-dX, -dY, 2500, 2500);
		/*
		if(dX<(-2000)){
			if(dY<-2000){
				
			}else{
				panel1.setBounds(-dX,0, 800, 800);
			}
		}else{
			if(dY<-2000){
				panel1.setBounds(0,-dY, 800, 800);
			}else{
				panel1.setBounds(0,0, 800, 800);
			}
		}
		*/
		//panel1.setBounds(-dX, -dY, 2500, 2500);
		//viewport.setOpaque(true);
		//viewport.setBounds(0,0 , 800, 800);
		//viewport.add(panel1);
		//getContentPane().add(viewport);
		System.out.println(-dX + " " + -dY);
	}

}
