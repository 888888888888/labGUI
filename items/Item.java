package items;

public abstract class Item {
	protected int id;
	protected int weight;
	protected String name;
	abstract public void use();
	abstract public boolean equals(Object obj);
	abstract public int getWeight();
}
