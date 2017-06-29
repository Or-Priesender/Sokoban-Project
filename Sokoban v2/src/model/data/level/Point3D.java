package model.data.level;

public class Point3D extends Point {

	protected int z;
	
	public Point3D(int x, int y, int z) {
		super(x,y);
		System.out.println("Someone calls a point 3d");
		this.z=z;
	}
}
