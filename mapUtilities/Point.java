package mapUtilities;

public class Point extends java.awt.Point{
	private static final long serialVersionUID = 1L;
private int z;

Point(int x,int y, int z){
	super(x,y);
	this.z = z;
}
public int retZ(){
	return this.z;
}
public void setZ(int z){
	this.z = z;
}
}
