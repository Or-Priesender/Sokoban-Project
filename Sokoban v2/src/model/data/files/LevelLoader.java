package model.data.files;


import java.io.IOException;
import java.io.InputStream;

import model.data.level.Level;
/**
 * this interface can load level in any way you want, without a need to change Level class.
 * it answere's the open / closed principle because the class Level doesn't change according to how you load a level.
 * it answere's liskov's substitution principle because you can load level in any way you want, just implement this interface.
 * 
 * the parameter is InputStream in order to be able to get any kind of InputStream, which is more general.
 */
public interface LevelLoader {
	
public	Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;
	

}
