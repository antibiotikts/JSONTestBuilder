package test_builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Configuration.*;

public class BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	private final String configFileName = "base_test_config.json";
	private final String currentDirectory = System.getProperty("user.dir");
	private final Path configFilePath = Paths.get(currentDirectory, "src", "test", "java", "test_builder", "tests_config","base_test_config", configFileName);

	@BeforeSuite
	public void runBaseTest() {
		logger.info("Path to base config file: " + configFilePath);
		TestBuilder tests = new TestBuilder(configFilePath);
		tests.buildTests();
		tests.executeCommands();
	}
}
