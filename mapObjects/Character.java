package mapObjects;

import containers.Equipment;
import mapUtilities.Cell;
import mapUtilities.MapObject;
import mapUtilities.Point;

public class Character extends MapObject{
	private String name;
	private Equipment inventory;
	private Point coords;
	private int strength=1;
	private int stamina=1;
	private int intelligence=1;
	private int hp=100;
	
	public Character(String name){
		this.name = name;
		inventory = new Equipment(strength);
	}
	public Equipment getEquipment(){
		return inventory;
	}
	@Override
	public void placeObject(Cell cell) {
		// TODO Auto-generated method stub
		cell.addObject(this);
	}

	@Override
	public void removeObject(Cell cell) {
		// TODO Auto-generated method stub
		
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
