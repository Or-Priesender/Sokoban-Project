package model.data;

public class ObjectLevelLoaderCreator implements LevelLoaderCreator {

	@Override
	public LevelLoader create() {
		
		return new MyObjectLevelLoader();
	}

}
