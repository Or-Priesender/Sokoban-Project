package model.data;

import java.util.Observable;

import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

public class MyModel extends Observable implements Model {

	Level current;
	SokobanPolicy p;

	public MyModel()
	{
		current=null;
		p=new MySokobanPolicy();
	}
	
	
	public MyModel(Level current, SokobanPolicy p) {
		this.current=current;
		this.p = p;
	}
	
	
	@Override
	public SokobanPolicy getPolicy()
	{
		return p;
	}
	
	@Override
	public Level getLevel() {
		
		return current;
	}

	@Override
	public boolean isFinished() {
		// TODO : check if level is finished
		return false;
	}
	public Level getCurrent() {
		return current;
	}
	public void setCurrent(Level current) {
		this.current = current;
	}


	@Override
	public void setPolicy(SokobanPolicy p) {
		this.p=p;
		
	}


	@Override
	public void move(String direction) {
		
		
	}

}
