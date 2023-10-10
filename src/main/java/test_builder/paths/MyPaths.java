package test_builder.paths;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyPaths {

	private static final String currentDirectory = System.getProperty("user.dir");

	public static Path getBaseTestConfigPath() {
		String configFileName = "base_test_config.json";
		return Paths.get(currentDirectory, "src", "test", "java", "test_builder", "tests_config","base_test_config", configFileName);
	}

	public static Path getElementsFilePath() {
		String configFileName = "my_elements.json";
		return Paths.get(currentDirectory, "src", "test", "java", "test_builder", "resources", configFileName);
	}

	public static Path getJsonTestDirectory() {
		return Paths.get(currentDirectory, "src", "test", "java", "test_builder", "tests_config");
	}
}
