package model.data.files;

public class TextLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		
		return new MyTextLevelLoader();
	}

}
