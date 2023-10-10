package test_builder;

import java.io.File;
import java.nio.file.Path;

public class BuildJsonTestsMultiArray {
	public static Object[][] getJsonFiles(Path path) {
		File directory = path.toFile();
		if (directory.isDirectory()) {
			File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
			if (files != null) {
				Object[][] data = new Object[files.length][1];
				for (int i = 0; i < files.length; i++) {
					data[i][0] = files[i].toPath();
				}
				return data;
			}
		}
		return new Object[0][0];
	}
}
