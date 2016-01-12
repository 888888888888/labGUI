package items;

public class Potion extends Item{
	int restore;
	
	public Potion(int id, int restore,String name){
		this.id = id;
		this.restore = restore;
		this.name = name;
		this.weight = 1;
	}
	public String getName(){
		return name;
	}
	public int getId(){
		return id;
	}
	public int getRestore(){
		return restore;
	}
	@Override
	/**
	 * When using an potion
	 */
	public void use() {
		// TODO Auto-generated method stub
	}
	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}
	/**
	 * Overrides equals method
	 */
	public boolean equals(Object obj){
		boolean same = false;
		if(obj!=null &&obj instanceof Potion){
			same = (this.id ==((Potion)obj).id && this.restore ==((Potion)obj).restore && this.name.equals(((Potion) obj).name));
		}
		return same;
	}
}
