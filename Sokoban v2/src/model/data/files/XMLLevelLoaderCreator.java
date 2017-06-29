package model.data.files;

/**
 * Creates a XML level loader.
 * @author Or Priesender
 *
 */
public class XMLLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		return new MyXMLLevelLoader();
	}

}
