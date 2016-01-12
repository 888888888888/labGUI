package containers;
import java.util.ArrayList;

import items.Item;
/**
 * Defines an equipment for every player
 * @author Dupasraka
 *
 */
public class Equipment{
	ArrayList<Item> equipment;
	int currentWeight;
	int maxWeight;
	/**
	 * Adds an item to inventory, if item added succefully it returns 0, if inventory is full returns 1;
	 * @param item
	 * @return
	 */
	public Equipment(int strength){
		this.maxWeight = 10+strength*10;
	}
	public int addItem(Item item){
		if(currentWeight<maxWeight && item.getWeight()+currentWeight<=maxWeight){
			equipment.add(item);
			return 0;
		}
		else return 1;
	}
	/**
	 * Removes item from inventory
	 * @param item
	 */
	public void removeItem(Item item){
			equipment.remove(item);
	}
	public void setMaxWeight(int size){
		this.maxWeight = size;
	}
	public void setCurrentWeight(int weightToAdd){
		currentWeight += weightToAdd;
	}
	public int getCurrentWeight(){
		return currentWeight;
	}
	public int getMaxWeight(){
		return maxWeight;
	}
}
