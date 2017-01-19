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

public class LevelDisplayerGUI extends Canvas implements Displayable{
	
	LevelObject[][] levelData;
	StringProperty wallFileName,boxFileName,backFileName,destinationFileName,playerFileName;
	Level lvl;
	int cCol,cRow;
	
	
	public LevelDisplayerGUI() {
		
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
		redraw();
	}
	
	public void redraw()
	{
		if(levelData!=null)
		{
			double W = getWidth();
			double H = getHeight();
			double w = W / levelData[0].length;
			double h = H / levelData.length;
			GraphicsContext gc = getGraphicsContext2D();
			Image wall,box,destination,back,player=null;
			try {
				wall = new Image(new FileInputStream(wallFileName.get()));
				/*
				box = new Image(new FileInputStream(boxFileName.get()));
				back = new Image(new FileInputStream(backFileName.get()));
				destination = new Image(new FileInputStream(destinationFileName.get()));
				player = new Image(new FileInputStream(playerFileName.get()));
				
			*/
			
			gc.clearRect(0, 0, W, H);
			
			for(int i=0;i<levelData.length;i++)
			{
				for(int j=0;j<levelData[i].length;j++)
				{
					if(levelData[i][j]!=null){
						
						if(levelData[i][j] instanceof Wall)
						gc.drawImage(wall, j*w, i*h, w, h);
					//else if(levelData[i][j] instanceof Box)
					//	gc.drawImage(box, j*w, i*h, w, h);
					//else if(levelData[i][j] instanceof model.data.Destination)
					//	gc.drawImage(destination, j*w, i*h, w, h);
				//	else if(levelData[i][j] instanceof Player)
					//	gc.drawImage(player,j*w,i*h,w,h);
						
						
						
					}
					//else gc.drawImage(back, j*w, i*h, w, h);
				}
			}
			
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			gc.setFill(Color.AQUA);
			gc.fillOval(cCol*w, cRow*h, w, h);
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

	@Override
	public void display() {
		redraw();
	}

	

	@Override
	public void setLevel(Level lvl) {
		this.lvl=lvl;
		setLevelData(lvl.getMap());
		cCol = lvl.getPlayerPos().getX();
		cRow = lvl.getPlayerPos().getY();
		
	}

	

}
