package model.data;

public class TextLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		
		return new MyTextLevelLoader();
	}

}
