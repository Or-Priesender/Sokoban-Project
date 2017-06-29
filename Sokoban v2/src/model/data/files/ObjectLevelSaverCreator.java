package model.data.files;

/**
 * Creates an object level saver.
 * @author Or Priesender
 *
 */
public class ObjectLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyObjectLevelSaver();
	}

}
