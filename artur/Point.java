package artur;

public class Point {
private int x;
private int y;
private int z;

Point(int x,int y, int z){
	this.x = x;
	this.y = y;
	this.z = z;
}
public int retX(){
	return this.x;
}
public int retY(){
	return this.y;
}
public int retZ(){
	return this.z;
}
public void setZ(int z){
	this.z = z;
}
}
