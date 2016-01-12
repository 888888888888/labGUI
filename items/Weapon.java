package items;

public class Weapon extends Item{
	int baseDamage;
	@Override
	/**
	 * when using a weapon
	 */
	public void use() {
		// TODO Auto-generated method stub
		class Damage{
			
		}
		
	}
	/**
	 * Overrides the equals method. Return true if all attributes of class object has the same values and it is the instantation of same Class
	 */
	public boolean equals(Object obj){
		boolean same = false;
		if(obj!=null &&obj instanceof Weapon){
			same = (this.id ==((Weapon)obj).id && this.baseDamage ==((Weapon)obj).baseDamage && this.weight ==((Weapon)obj).weight);
		}
		return same;
	}
	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
