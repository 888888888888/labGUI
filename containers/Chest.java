package containers;

public class Chest extends Container{
	String desc;
	Chest(int id, int size){
		super(id, size);
		this.desc = "This is a chest";
	}
	public String toString(){
		return desc;
	}
}
