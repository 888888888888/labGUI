package containers;
import java.awt.Point;
import java.util.*;

import items.Item;
import mapObjects.Character;
import mapUtilities.Cell;
import mapUtilities.MapObject;
public class Container extends MapObject{
	
	ArrayList<Item> objects;
	int size;
	Point coords;
	public Container(int id, int size){
		objects = new ArrayList<Item>();
	}
	public int getSize(){
		return this.size;
	}
	@Override
	public void placeObject(Cell cell) {
		cell.addObject(this);
		
	}
	@Override
	public void removeObject(Cell cell) {
		
	}
	
	@Override
	public void interact(Character character, String arg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
