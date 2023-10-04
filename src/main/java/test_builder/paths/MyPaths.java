package test_builder.paths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MyPaths {

	public static Path getBaseTestConfigPath() {
		String configFileName = "base_test_config.json";
		String currentDirectory = System.getProperty("user.dir");
		return Paths.get(currentDirectory, "src", "test", "java", "test_builder", "tests_config","base_test_config", configFileName);
	}

	public static Path getElementsFilePath() {
		String configFileName = "my_elements.json";
		String currentDirectory = System.getProperty("user.dir");
		return Paths.get(currentDirectory, "src", "test", "java", "test_builder", "resources", configFileName);
	}
}
