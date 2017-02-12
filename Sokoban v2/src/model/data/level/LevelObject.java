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
	public boolean isWasDestination() {
		return wasDestination;
	}
	public void setWasDestination(boolean wasDestination) {
		this.wasDestination = wasDestination;
	}

	
}
