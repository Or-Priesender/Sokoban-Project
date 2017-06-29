package model.data.files;

/**
 * Creates an object level loader.
 * @author Or Priesender
 *
 */
public class ObjectLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		
		return new MyObjectLevelLoader();
	}

}
