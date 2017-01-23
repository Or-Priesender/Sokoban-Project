package model.data.files;

public class XMLLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyXMLLevelSaver();
	}

}
