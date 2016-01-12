package moje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import mapUtilities.*;

class Panel2 extends JPanel {

	private static final long serialVersionUID = 1L;
	WorldMap2 mapToPrint = null;

		Panel2(WorldMap2 mapa) {
            //ustawiam preferowany rozmiar daje mniejszy zeby bylo widac console i nie trzeba bylo 
			//minimalizowac xD
//            setPreferredSize(new Dimension(1400,700));
			setPreferredSize(new Dimension(500,500));
            //przekazuje do klasy wygenerowana macierz
            this.mapToPrint = mapa;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //zwykla funkcja for do rysowania mapy w naszym GUI
            int height,red,blue,green;
            for (int i = 0; i < mapToPrint.getXSize(); i++) {         
                for (int j = 0; j < mapToPrint.getYSize(); j++) {
                	height = mapToPrint.getMatrix()[i][j].retId();
                	if(height<25&&height>0){
                		g.setColor(new Color(height*10,255,0)); 
                        g.fillRect(i, j, 4,4);
                	}else if(height>=25&& height<50){
                		g.setColor(new Color(255,255-(height-25)*10,0)); 
                        g.fillRect(i, j, 4,4);
                	}else if(height==50){
                		g.setColor(new Color(255,0,0)); 
                        g.fillRect(i, j, 4,4);
                	}else if(mapToPrint.getMatrix()[i][j].retId()==3564){
                    	g.setColor(Color.BLUE);
                    	g.fillRect(i, j, 4, 4);
                    }else if (mapToPrint.getMatrix()[i][j].retId()==0) {  
                    	g.setColor(Color.BLUE); 
                        g.fillRect(i, j, 4,4);              
                    }else {
                    	g.setColor(Color.BLUE); 
                        g.fillRect(i, j, 4,4); 
                    }
                }
                	/*
                    if (mapToPrint.getMatrix()[i][j].retId()==0) {  
                    	g.setColor(Color.BLUE); 
                        g.fillRect(i, j, 1,1);              
                    }else if(mapToPrint.getMatrix()[i][j].retId()==1){
                    	g.setColor(new Color(0,255,120));
                    	g.fillRect(i, j, 1,1);  
                    }else if(mapToPrint.getMatrix()[i][j].retId()==2){
                    	g.setColor(new Color(0,255,60));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==3){
                    	g.setColor(new Color(0,255,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==4){
                    	g.setColor(new Color(60,255,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==5){
                    	g.setColor(new Color(120,255,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==6){
                    	g.setColor(new Color(180,255,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==7){
                    	g.setColor(new Color(240,255,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==8){
                    	g.setColor(new Color(255,220,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==9){
                    	g.setColor(new Color(255,160,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==10){
                    	g.setColor(new Color(255,100,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==11){
                    	g.setColor(new Color(255,40,0));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint.getMatrix()[i][j].retId()==3564){
                    	g.setColor(Color.BLUE);
                    	g.fillRect(i, j, 1, 1);
                    }
                    */
                }     
            }     
}
