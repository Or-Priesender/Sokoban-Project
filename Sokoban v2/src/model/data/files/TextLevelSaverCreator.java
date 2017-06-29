package model.data.files;

/**
 * Creates a text level saver.
 * @author Or Priesender
 *
 */
public class TextLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyTextLevelSaver();

	}

}
