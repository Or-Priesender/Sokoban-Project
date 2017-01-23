package model.data.files;

public class XMLLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		return new MyXMLLevelLoader();
	}

}
