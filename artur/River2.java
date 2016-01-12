package artur;
import java.util.*;

import artur.Point;
import artur.WorldMap2;
/**
 * Class implements the river object. River can be placed on WorldMap object
 */
public class River2 extends MapObject{
	private List<Point> river;
	public River2(String id){
		super(id);
		river = new ArrayList<Point>();
	}
	/**
	 * This method places seeds of rivers in random places and random height of terrain greater than 0
	 */
	public void riverSeed(WorldMap2 worldMap){
		Random random = new Random();
		int seedx;
		int seedy;
		int seedz;
		boolean done = false;
		while(true){
			seedx = random.nextInt(worldMap.size.retX());
			seedy = random.nextInt(worldMap.size.retY());
			seedz = worldMap.matrix[seedx][seedy].retCoord().retZ();
			if(seedz>0){
				worldMap.update(seedx, seedy, seedz, super.retValue());
				river.add(new Point(seedx,seedy,seedz));
				break;
			}
		}
		int way;
		int worldZValue;
		while(!done){
		way = random.nextInt(8);
		switch(way){
		case 0:
			worldZValue = worldMap.matrix[seedx-1][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 1:
			worldZValue = worldMap.matrix[seedx][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 2:
			worldZValue = worldMap.matrix[seedx+1][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 3:
			worldZValue = worldMap.matrix[seedx+1][seedy].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy,worldZValue));
				done = true;
			}
			break;
		case 4:
			worldZValue = worldMap.matrix[seedx+1][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 5:
			worldZValue = worldMap.matrix[seedx][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 6:
			worldZValue = worldMap.matrix[seedx-1][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 7:
			worldZValue = worldMap.matrix[seedx-1][seedy].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy,worldZValue));
				done = true;
			}
			break;
		}
		}
	}
	/**
	 * This method places seeds of rivers in random places, but user can specify the highest terrain level seed can be placed
	 */
	public void riverSeed(int highest, WorldMap2 worldMap){
		Random random = new Random();
		int seedx;
		int seedy;
		int seedz;
		boolean done = false;
		while(true){
		seedx = random.nextInt(worldMap.size.retX());
		seedy = random.nextInt(worldMap.size.retY());
		seedz = worldMap.matrix[seedx][seedy].retCoord().retZ();
			if(seedz<=highest && seedz>0){
				worldMap.update(seedx, seedy, seedz, super.retValue());
				river.add(new Point(seedx,seedy,seedz));
				break;
				}
		}
		int way;
		int worldZValue;
		while(!done){
		way = random.nextInt(8);
		switch(way){
		case 0:
			worldZValue = worldMap.matrix[seedx-1][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 1:
			worldZValue = worldMap.matrix[seedx][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 2:
			worldZValue = worldMap.matrix[seedx+1][seedy-1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy-1, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy-1,worldZValue));
				done = true;
			}
			break;
		case 3:
			worldZValue = worldMap.matrix[seedx+1][seedy].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy,worldZValue));
				done = true;
			}
			break;
		case 4:
			worldZValue = worldMap.matrix[seedx+1][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx+1, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx+1,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 5:
			worldZValue = worldMap.matrix[seedx][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 6:
			worldZValue = worldMap.matrix[seedx-1][seedy+1].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy+1, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy+1,worldZValue));
				done = true;
			}
			break;
		case 7:
			worldZValue = worldMap.matrix[seedx-1][seedy].retCoord().retZ();
			if(worldZValue<=seedz){
				worldMap.update(seedx-1, seedy, worldZValue, super.retValue());
				river.add(new Point(seedx-1,seedy,worldZValue));
				done = true;
			}
			break;
		}
		}

	}
	
	/**
	 * This method generates rivers on terrain,
	 */
	public int growRiver(WorldMap2 worldMap){
		int way;
		Random random = new Random();
		Point reference1;
		Point reference2;
		boolean done = false;
		boolean placed = false;
		int dx,dy;
		boolean []flags = new boolean[]{false,false,false,false,false};
		//System.out.println("jestem przed while !done");
		if(river.size()<2) return -1;
	while(!done){
		placed = false;
		reference1 = new Point(river.get(river.size()-1).retX(),river.get(river.size()-1).retY(),river.get(river.size()-1).retZ()); //reference on last river tile
		reference2 = new Point(river.get(river.size()-2).retX(),river.get(river.size()-2).retY(),river.get(river.size()-2).retZ()); //reference on last-1 river tile
		dx = reference1.retX()-reference2.retX(); //here im checking where was the river point before the last one
		dy = reference1.retY()-reference2.retY(); //
		System.out.println(" ref1X: " + reference1.retX() + "ref2X:  "+reference2.retX());
		System.out.println(" ref1Y: " + reference1.retX() + "ref2Y:  "+reference2.retX());
		System.out.println("jestem w while !done" + dx +" "+ dy);
		switch(dx){
		//DX == -1 <--???-WEST
		case -1:
			switch(dy){
			//DX == -1, DY == -1 <--NORTH-WEST
			case -1:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == -1, DY == -1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(-1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(-1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(-1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(0,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
				}
				if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
					done = true; //here we checks if all flags are true; then there is no way river can go
					placed = true;
					for(int i = 0;i< flags.length;i++) flags[i] = false;
				}
				}
				break;
			//DX == -1, DY == 0 <--WEST
			case 0:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == -1, DY == 0  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(0,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(-1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(-1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(-1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(0,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
				}
				break;
			//DX == -1, DY == 1 <--SOUTH-WEST
			case 1:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == -1, DY == 1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(0,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(-1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(-1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(-1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			}
			break;
		//DX == 0 <--SOUTH OR NORTH
		case 0:
			//DX == 0, DY == -1 <--NORTH
			switch(dy){
			case -1:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == 0, DY == -1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(-1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(-1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(0,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			//DX == 0, DY == 0 <--This case should be never reached!
			case 0:
				System.out.println("Error!");
				break;
			//DX == 0, DY == 1 <--SOUTH
			case 1:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == 0, DY == 1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(0,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(-1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(-1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			}
			break;
		//DX == 1 <--???-EAST
		case 1:
			//DX == 1, DY == -1 <--NORTH-EAST
			switch(dy){
			case -1:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == 1, DY == -1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(-1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(0,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			//DX == 1, DY == 0 EAST
			case 0:
				while(!placed){
					way = random.nextInt(5);
					System.out.println("petla DX == 1, DY == 0  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(0,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(0,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			//DX == 1, DY == 1 <--SOUTH-EAST
			case 1:
				while(!placed){
					way = random.nextInt(5); //
					System.out.println("petla DX == 1, DY == 1  " +way);
					switch(way){
					case 0:
						switch(addRiverTile(1,-1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[0] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 1:
						switch(addRiverTile(1,0,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[1] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 2:
						switch(addRiverTile(1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[2] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 3:
						switch(addRiverTile(0,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[3] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					case 4:
						switch(addRiverTile(-1,1,worldMap)){
						case -1:
							done = true;
							placed = true;
							break;
						case 0:
							flags[4] = true;
							break;
						case 1:
							placed = true;
							break;
						}
						break;
					}
					if(flags[0] && flags[1] && flags[2] && flags[3] && flags[4]) {
						done = true; //here we checks if all flags are true; then there is no way river can go
						placed = true;
						for(int i = 0;i< flags.length;i++) flags[i] = false;
					}
					}
				break;
			}
			break;
		}
	}
	return 0;
	}
	public int addRiverTile(int dx, int dy, WorldMap2 worldMap){
		int worldZValue;
		Point reference1;
		reference1 = new Point(river.get(river.size()-1).retX(),river.get(river.size()-1).retY(),river.get(river.size()-1).retZ()); //reference on last river tile
		//reference2 = new Point(river.get(river.size()-2).retX(),river.get(river.size()-2).retY(),river.get(river.size()-2).retZ()); //reference on last-1 river tile
		worldZValue = worldMap.matrix[reference1.retX()+dx][reference1.retY()+dy].retCoord().retZ();
		if(worldZValue<=reference1.retZ() && !river.contains(new Point(reference1.retX()-1,reference1.retY()-1,worldZValue))){
			if(worldZValue==0 || 
				(reference1.retZ()<worldZValue && 
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx-1][reference1.retX()+dy-1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx][reference1.retX()+dy-1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx+1][reference1.retX()+dy-1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx+1][reference1.retX()+dy].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx+1][reference1.retX()+dy+1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx][reference1.retX()+dy+1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx-1][reference1.retX()+dy+1].retCoord().retZ()&&
				reference1.retZ()<worldMap.matrix[reference1.retX()+dx-1][reference1.retX()+dy].retCoord().retZ())){
				System.out.println("zwrocil -1");
				return -1;
			}
			worldMap.update(reference1.retX()+dx, reference1.retY()+dy, worldZValue, super.retValue());
			river.add(new Point(reference1.retX()+dx,reference1.retY()+dy,worldZValue));
			return 1;
		}
		return 0;
	}
}
