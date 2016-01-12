package artur;
import java.util.*;

public class Cell {
	private String id;
	private Point coord;
	private MapObject object;
	
	Cell(int x, int y, int z, String id, MapObject object){
		this.coord = new Point(x,y,z);
		this.id = id;
		this.object = object;
	};
	public MapObject retCellMapObj(){
		return this.object;
	}
	public String retId(){
		return this.id;
	}
	public Point retCoord(){
		return this.coord;
	}
	public void update(int x, int y, int z, String id, MapObject object){
		this.id = id;
		this.object = object;
		this.coord = new Point(x,y,z);
	}
}
