package levelLoadersAndSavers;

public class XMLLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyXMLLevelSaver();
	}

}
