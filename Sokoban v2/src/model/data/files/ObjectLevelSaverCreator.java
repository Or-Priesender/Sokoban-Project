package model.data.files;

public class ObjectLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyObjectLevelSaver();
	}

}
