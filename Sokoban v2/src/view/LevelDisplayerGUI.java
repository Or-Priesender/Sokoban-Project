package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class LevelDisplayerGUI extends Canvas{
	
	char[][] levelData;
	StringProperty wallFileName;
	int cCol,cRow;
	
	public LevelDisplayerGUI() {
		wallFileName = new SimpleStringProperty();
		cCol=0;
		cRow=1;
	}

	public char[][] getLevelData() {
		return levelData;
	}

	public void setLevelData(char[][] levelData) {
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
			Image wall=null;
			try {
				wall = new Image(new FileInputStream(wallFileName.get()));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			gc.clearRect(0, 0, W, H);
			
			for(int i=0;i<levelData.length;i++)
			{
				for(int j=0;j<levelData[i].length;j++)
				{
					if(wall==null)
						gc.fillRect(j*w, i*h, w, h);
					
					else if(levelData[i][j]=='#')
						gc.drawImage(wall, j*w, i*h, w, h);
					
				}
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

	

}
