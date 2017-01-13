package controller;

import java.util.Observable;
import java.util.Observer;

public interface Controller extends Observer {
	public void update(Observable o, Object args);
	public void start();
	public void stop();
	
}
