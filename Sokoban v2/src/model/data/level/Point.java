package model.data.level;

import java.io.Serializable;

/**
 * This class is used to define a level object's position on the map.
 * @author Or Priesender
 *
 */
public class Point implements Serializable {
	protected int x;
	protected int y;

	public Point() {
		x=0;
		y=0;
	}
	
	public Point(int y,int x){
		this.x=x;
		this.y=y;
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
	
	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return (p.getX() == this.getX() && p.getY() == this.getY());
	}
	
	@Override
	public String toString() {
	
		return ""+y+","+x;
	}
	@Override
	public int hashCode() {
		 return Integer.hashCode(y)+Integer.hashCode(x);
	}
	
}
