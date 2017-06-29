package model.data.files;

/**
 * Creates a XML level saver.
 * @author Or Priesender
 *
 */
public class XMLLevelSaverCreator implements LevelSaverCreator {

	@Override
	public LevelSaver create() {
		return new MyXMLLevelSaver();
	}

}
