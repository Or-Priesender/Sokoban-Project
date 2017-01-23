package model.data.files;

public class TextLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyTextLevelSaver();

	}

}
