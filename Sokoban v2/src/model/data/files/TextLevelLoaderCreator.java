package model.data.files;

/**
 * Creates a text level loader.
 * @author Or Priesender
 *
 */
public class TextLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		
		return new MyTextLevelLoader();
	}

}
