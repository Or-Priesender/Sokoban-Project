package model.data.level;

import java.io.Serializable;

public class LevelObject implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Point position;
	protected boolean wasDestination;
	
	public LevelObject()
	{
		this.position.setX(0);
		this.position.setY(0);
		wasDestination=false;
	}
	public LevelObject(Point position) {
		this.position=position;
		
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	//checks if this object is on a destination
	public boolean isWasDestination() {
		return wasDestination;
	}
	public void setWasDestination(boolean wasDestination) {
		this.wasDestination = wasDestination;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().toString());
		sb.append("(");
		sb.append(this.getPosition().getX());
		sb.append(",");
		sb.append(this.getPosition().getY());
		sb.append(")");
		return sb.toString();
	}

	
}
