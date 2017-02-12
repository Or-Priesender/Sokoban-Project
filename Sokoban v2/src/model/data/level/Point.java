package model.data.level;

import java.io.Serializable;

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
	
	
}
