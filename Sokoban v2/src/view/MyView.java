package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

import model.data.Level;
import model.data.LevelObject;

public class MyView extends Observable implements View {
	
	Displayable d;

	public MyView(Displayable d)
	{
		this.d = d;
	}
	
	public MyView()
	{
		d = new LevelDisplayerGUI();
		
	}
	
	@Override
	public void display(LevelObject[][] levelData) {
		
		d.display();
		
		
	}

	@Override
	public void start(InputStream in, OutputStream out) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
