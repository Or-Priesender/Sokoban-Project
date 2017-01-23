package model.data.level;

public class Point2D extends Point{
	
	
	public Point2D()
	{
		super(0,0);
	}
	public Point2D(int y, int x) {
		super(y,x);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
