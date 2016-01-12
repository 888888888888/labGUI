package items;

public class Resource extends Item{
	public Resource(int id,String name, int weight){
		this.id = id;
		this.name = name;
		this.weight = weight;
	}
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

}
