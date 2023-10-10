package test_builder;

import test_builder.paths.MyPaths;

import java.io.File;

public class BuildJsonTestsArray {

	public static Object[] getJsonFiles() {
		File directory = MyPaths.getJsonTestDirectory().toFile();

		if (directory.isDirectory()) {
			File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
			if (files != null) {
				Object[] paths = new Object[files.length];
				for (int i = 0; i < files.length; i++) {
					paths[i] = files[i].toPath();
				}
				return paths;
			}
		}
		return null;
	}
}
