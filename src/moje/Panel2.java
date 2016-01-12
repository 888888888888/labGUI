package moje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import artur.Cell;

class Panel2 extends JPanel {

	private static final long serialVersionUID = 1L;
	Cell[][] mapToPrint = null;

		Panel2(Cell[][] mapToPrint) {
            //ustawiam preferowany rozmiar daje mniejszy zeby bylo widac console i nie trzeba bylo 
			//minimalizowac xD
//            setPreferredSize(new Dimension(1400,700));
			setPreferredSize(new Dimension(500,500));
            //przekazuje do klasy wygenerowana macierz
            this.mapToPrint = mapToPrint;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
                          
            //zwykla funkcja for do rysowania mapy w naszym GUI
            for (int i = 0; i < 1400; i++) {         
                for (int j = 0; j < 700; j++) {    
                    if (mapToPrint[i][j].retId().equals("1")) {  
                    	g.setColor(Color.BLUE); 
                        g.fillRect(i, j, 1,1);              
                    }else if (mapToPrint[i][j].retId().equals("2")){
                    	g.setColor(Color.GREEN);
                    	g.fillRect(i, j, 1,1);  
                    }else if (mapToPrint[i][j].retId().equals("3")){
                    	g.setColor(new Color(205,133,63));
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint[i][j].retId().equals("4")){
                    	g.setColor(Color.GRAY);
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint[i][j].retId().equals("5")){
                    	g.setColor(Color.DARK_GRAY);
                    	g.fillRect(i, j, 1, 1);
                    }else if(mapToPrint[i][j].retId().equals("@")){
                    	g.setColor(Color.BLUE);
                    	g.fillRect(i, j, 1, 1);
                    }
                }     
            }     
       } 
}
