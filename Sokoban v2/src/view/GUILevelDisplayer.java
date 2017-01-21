package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.data.Box;
import model.data.Level;
import model.data.LevelObject;
import model.data.Player;
import model.data.Wall;

public class GUILevelDisplayer extends Canvas {
	
	LevelObject[][] levelData;
	StringProperty wallFileName,boxFileName,backFileName,destinationFileName,playerFileName;
	Image wall,box,back,destination,player;
	Level lvl;
	int cCol,cRow;
	
	
	public GUILevelDisplayer() {
		
		wallFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		backFileName = new SimpleStringProperty();
		destinationFileName = new SimpleStringProperty();
		playerFileName = new SimpleStringProperty();
		
		
		
	}

	public LevelObject[][] getLevelData() {
		return levelData;
	}

	public void setLevelData(LevelObject[][] levelData) {
		this.levelData = levelData;
		display(levelData);
	}
	
	public void redraw()
	{
		try {
			wall = new Image(new FileInputStream(wallFileName.get()));
			box = new Image(new FileInputStream(boxFileName.get()));
			back = new Image(new FileInputStream(backFileName.get()));
			destination = new Image(new FileInputStream(destinationFileName.get()));
			player = new Image(new FileInputStream(playerFileName.get()));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(levelData!=null)
		{
			double W = this.getWidth();
			double H = this.getHeight();
			double w = W / levelData[0].length;
			double h = H / levelData.length;
			GraphicsContext gc = getGraphicsContext2D();
			
			gc.clearRect(0, 0, W, H);
			
			for(int i=0;i<levelData.length;i++)
			{
				for(int j=0;j<levelData[i].length;j++)
				{
					if(levelData[i][j]!=null){
						
						if(levelData[i][j] instanceof Wall && wall != null)
						gc.drawImage(wall, j*w, i*h, w, h);
					else if(levelData[i][j] instanceof Box && box != null)
						gc.drawImage(box, j*w, i*h, w, h);
					else if(levelData[i][j] instanceof model.data.Destination && destination != null)
						gc.drawImage(destination, j*w, i*h, w, h);
					else if(levelData[i][j] instanceof Player && player != null)
						gc.drawImage(player,j*w,i*h,w,h);
						
						
						
					}
					else if(back != null) 
						gc.drawImage(back, j*w, i*h, w, h);
					else gc.fillOval(j*w, i*h, w, h);
				}
			}
			
			
			
		}
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public int getcCol() {
		return cCol;
	}

	public void setCharacterPosition(int row,int col)
	{
		cRow=row;
		cCol=col;
		redraw();
	}

	public int getcRow() {
		return cRow;
	}

	
	public void display(LevelObject[][] levelData) {
		setLevelData(levelData);
		redraw();
	}

	public String getBoxFileName() {
		return boxFileName.get();
	}

	public void setBoxFileName(String boxFileName) {
		this.boxFileName.setValue(boxFileName);;
	}

	public String getBackFileName() {
		return backFileName.get();
	}

	public void setBackFileName(String backFileName) {
		this.backFileName.set(backFileName);;
	}

	public String getDestinationFileName() {
		return destinationFileName.get();
	}

	public void setDestinationFileName(String destinationFileName) {
		this.destinationFileName.set(destinationFileName);
	}

	public String getPlayerFileName() {
		return playerFileName.get();
	}

	public void setPlayerFileName(String playerFileName) {
		this.playerFileName.set(playerFileName);
	}

	

	
	//TODO : do i need this?
	/*
	@Override
	public void setLevel(Level lvl) {
		this.lvl=lvl;
		setLevelData(lvl.getMap());
		cCol = lvl.getPlayerPos().getX();
		cRow = lvl.getPlayerPos().getY();
		
	}
*/
	

}
