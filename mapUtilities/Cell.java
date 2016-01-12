package mapUtilities;
import java.util.*;

import mapObjects.ExistListener;
/**
 * 
 */
public class Cell implements ExistListener{
	private int id;
	private Point coord;
	private ArrayList<MapObject> object;
	
	Cell(int x, int y, int z, int id){
		this.coord = new Point(x,y,z);
		this.id = id;
		this.object = new ArrayList<MapObject>();
	};
	
	
	public int retId(){
		return this.id;
	}
	
	public Point retCoord(){
		return this.coord;
	}
	
	public void update(int x, int y, int z, int id){
		this.id = id;
		this.coord = new Point(x,y,z);
	}
	
	public void addObject(MapObject obj){
		this.object.add(obj);
	}
	
	public void removeObject(MapObject obj){
			object.remove(obj);
	}

	public MapObject getObject(int i){
		return object.get(i);
	}
	
	public ArrayList<MapObject> getAllObjects(){
		return object;
	}

	@Override
	/**
	 * When exist of initiator object will change to false,
	 * the MapObject deletes this MapObject from object array,
	 */
	public void existChanged(MapObject initiator) { //someone said hello
		// TODO Auto-generated method stub
		if(!initiator.exist){
			object.remove(initiator);
		}
	}
}
