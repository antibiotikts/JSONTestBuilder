package test_builder;

import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestSuit2 extends BaseTest {

	@DataProvider(name = "jsonFiles")
	public Object[][] getJsonFiles() {
		String currentDirectory = System.getProperty("user.dir");
		Path testDirectory = Paths.get(currentDirectory, "src", "test", "java", "test_builder", "tests_config", "old");
		File directory = testDirectory.toFile();

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

	@Test(dataProvider = "jsonFiles", description = "Negative")
	public void testRunJson(Path jsonFiles) {
		TestBuilder tests = new TestBuilder(jsonFiles);
		tests.buildTests();
		tests.executeCommands();
		Allure.addAttachment("INFO", jsonFiles.getFileName().toString());
	}
}
