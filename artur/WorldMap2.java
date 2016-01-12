package artur;
import java.util.*;
import java.math.*;
/**
 * This class implements World map. 
 */
public class WorldMap2{
	protected Point size;
	protected Cell matrix[][];
	private River2 allRivers;
	private int maxHeight;
	
	public WorldMap2(int sizex,int sizey, int sizez){
		this.size= new Point(sizex,sizey,sizez);
		this.matrix = new Cell[sizex][sizey];
		this.maxHeight = 0;
	}
	public WorldMap2(int sizex,int sizey){
		this.size= new Point(sizex,sizey,0);
		this.matrix = new Cell[sizex][sizey];
		this.maxHeight = 0;
	}
	/**
	 * Creates a world map, by placing seeds, growing them and other complex things. This is actualy the core of this class
	 */
	public void create() {
		fillWith("0",0);
		int count = (int)(size.retX()*size.retY()*0.33);
		bedRock((int)Math.sqrt(size.retX()),count,1,"1","0");
		
		for(int i = 0; i<50;i++){
			count = (int)(count*0.95);
			randomSeeds((int)Math.sqrt(size.retX()),i+2,""+(i+2),""+(i+1));
			growSeeds((int)(count),i+2,""+(i+2),""+(i+1));
			System.out.println(i);
		}
		
		for(int i=0;i<50;i++){
		allRivers = new River2("@");
		allRivers.riverSeed(this);
		allRivers.growRiver(this);
		}
		this.toString();
	}
	/**
	 * Placing the bedrock seeds and bedrock,
	 * wchich means, this is the lowest terrain and every 
	 * other types of terrains shoul be plced on it.
	 * Its not only placing the seeds, but every time it plant a seed
	 * its aslo placing the proper amount of Cells around the seed.
	 * That provides more random shapes of continents, than placing all seeds first
	 * and then placing terains around them.
	 * 
	 * @param seeds - number of seeds wchich have to be placed
	 * @param count - number of Cells which needs to be placed around the seeds, it should be around 33% of WorldMap Cells
	 * @param z - height of the lowest terrain on the map
	 * @param type - type of terrain to place, the value should be like "2" or "1" for easier iteration
	 * @param onType - type of terrain on which this bed rock needs to be placed, method {@link fillWith} would be helpfull
	 */
	private void bedRock(int seeds,int count,int z, String type, String onType){
		Random random = new Random();
		int seedx;
		int seedy;
		if(maxHeight<z){//checking if the height is greater than notified maxHeight to make sure info about highest level is up to date
			maxHeight = z;
		}
		int numSeeds = seeds;
		while(seeds>0){//setting up seeds
		seedx = random.nextInt((int)(size.retX()*0.8))+(int)(size.retX()*0.1);
		seedy = random.nextInt((int)(size.retY()*0.4))+(int)(size.retY()*0.2);
		//System.out.println(seedx + "  " + seedy+"   "+(int)(sizex*0.70)+"  "+(int)(sizex*0.15));
		if(matrix[seedx][seedy].retId().equals(onType)) {
			matrix[seedx][seedy] = new Cell(seedx,seedy,z,type,new MapObject(type));
			growSeeds(count/numSeeds,z,type,onType);
			seeds--;
			}
		}
	}

	/**
	 * Placing the seeds in random positions of specified type
	 * @param seeds-number of seeds that needs to be placed
	 * @param z - the heigth of this tile
	 * @param type-type of seed for example: "forest", "hills" etc.
	 * @param onType - on what type of terrain it needs to be placed
	 */
	private void randomSeeds(int seeds,int z, String type, String onType){
		Random random = new Random();
		int seedx;
		int seedy;
		if(maxHeight<z){//checking if the height is greater than notified maxHeight to make sure info about highest level is up to date
			maxHeight = z;
		}
		while(seeds>0){//setting up seeds
		seedx = random.nextInt((int)(size.retX()*0.9))+(int)(size.retX()*0.05);
		seedy = random.nextInt((int)(size.retY()*0.9))+(int)(size.retY()*0.05);
		//System.out.println(seedx + "  " + seedy+"   "+(int)(sizex*0.70)+"  "+(int)(sizex*0.15));
		if(matrix[seedx][seedy].retId().equals(onType)) {
			matrix[seedx][seedy].update(seedx,seedy,z,type,null);// = new Cell(seedx,seedy,z,type,new MapObject(type));
			seeds--;
			}
		}
	}
	/**
	 * Growing the seeds on matrix.
	 * @param count
	 * @param type
	 * @return returns 0 if everything is ok, 1 if there was not enough space to place all
	 */
	public int growSeeds(int count,int z, String type,String onType){
		int x;
		int y;
		int previousCount= count;
		Random random = new Random();
		if(maxHeight<z){ //checking if the height is greater than notified maxHeight to make sure info about highest level is up to date
			maxHeight = z;
		}
		while(count>0){
			for(y=0;y<size.retY();y++){
				for(x =0; x<size.retX(); x++){
					if(matrix[x][y].retId().equals(type) && matrix[x][y].retCoord().retZ() == z && count>0){ //the previous instance must be the same type and the same height, count needs to be greater than 0
						int way = random.nextInt(7); //random choosing map cell in neighbour of existing one
						switch (way){//trying to put new map cell
						case 0:
							if (((x - 1)>0) && ((y - 1)>0) && (matrix[x - 1][y - 1].retId().equals(onType)))
							{
								matrix[x - 1][y - 1]= new Cell(x-1,y-1,z,type,null); 
								count--;
							}
							break;
						case 1:
							if (((y - 1)>0) && (matrix[x][y - 1].retId().equals(onType)))
							{
								matrix[x][y - 1]= new Cell(x,y-1,z,type,null); 
								count--;
							}
							break;
						case 2:
							if (((x + 1) < (size.retX() - 1)) && ((y - 1) > 0) && (matrix[x + 1][y - 1].retId().equals(onType)))
							{
								matrix[x + 1][y - 1]= new Cell(x+1,y-1,z,type,null); 
								count--;
							}
							break;
						case 3:
							if (((x + 1)< (size.retX() - 1)) && (matrix[x + 1][y].retId().equals(onType)))
							{
								matrix[x + 1][y] = new Cell(x+1,y,z,type,null); 
								x++;
								count--;
							}
							break;
						case 4:
							if (((x + 1)< (size.retX() - 1)) && ((y + 1) < (size.retY() - 1)) && (matrix[x + 1][y + 1].retId().equals(onType)))
							{
								matrix[x + 1][y + 1] = new Cell(x+1,y+1,z,type,null);
								count--;
							}
							break;
						case 5:
							if (((y + 1) < (size.retY() - 1)) && (matrix[x][y + 1].retId().equals(onType)))
							{
								matrix[x][y + 1]= new Cell(x,y+1,z,type,null);
								count--;
							}
							break;
						case 6:
							if (((x - 1) > 0) && ((y + 1) < (size.retY() - 1)) && (matrix[x - 1][y + 1].retId().equals(onType)))
							{
								matrix[x - 1][y + 1] = new Cell(x-1,y+1,z,type,null);
								count--;
							}
							break;
						case 7:
							if (((x - 1) > 0) && (matrix[x - 1][y].retId().equals(onType)))
							{
								matrix[x - 1][y] = new Cell(x-1,y,z,type,null);
								count--;
							}
							break;
						}
				}
			}
		}
		if (previousCount == count) return 1; //there is no more space to place a cell it needs to leave the while loop otherwise it stay there forever
		else previousCount = count; //here its updates the previous count to know how many tiles was placed
		x = 0;
		y = 0;
		}
		return 0;
	}

	/**
	 * Fills whole matrix with Cells defined by argument.
	 * It should be the first "layer" of map before bedRock. 
	 * This layer should represent the oceans if 
	 * you want to create WorldMap with some continents on it
	 * @param id - id of fill type
	 * @param z - height of cells
	 */
	public void fillWith(String id, int z){
		if(maxHeight<z){//checking if the height is greater than notified maxHeight to make sure info about highest level is up to date
			maxHeight = z;
		}
		for(int y = 0;y<size.retY();y++){//filling whole map with one cell type and height
			for(int x = 0; x<size.retX();x++){
				matrix[x][y] = new Cell(x,y,z,id,new MapObject(null));
			}
		}
		System.out.println("Skonczylem fill");
	}
	/**
	 * This method lets creator to change every single cell of world map
	 * @param x -x position of cell
	 * @param y - y positio of cell
	 * @param z - z positon of cell
	 * @param id - id of cell which needs to be placed
	 */
	public void update(int x, int y, int z, String id) {
		if(maxHeight<z){//checking if the height is greater than notified maxHeight to make sure info about highest level is up to date
			maxHeight = z;
		}
		this.matrix[x][y].update(x,y,z,id, null);// = new Cell(x,y,z,id,new MapObject(id));
	}
	
	/**
	 * Return Cell matrix of map
	 * @return
	 */
	public Cell[][] getMatrix(){
		return matrix;
	}
	/**
	 * Returns X size of world map
	 */
	public int getXSize(){ 
		return size.retX();
	}
	/**
	 * Returns Y size of world map
	 */
	public int getYSize(){ 
		return size.retY();
	}
	/**
	 * Returns Z size of world map
	 */
	public int getZSize(){ 
		return size.retZ();
	}
	/**
	 * String output of the map
	 */
	public String toString(){
		String result = "";
		for(int y=0;y<size.retY();y++){
			for(int x=0;x<size.retX();x++){
				System.out.print(matrix[x][y].retId());
			}
			System.out.println("");
		}
		return result;
			
	}
}
