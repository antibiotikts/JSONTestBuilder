package test_builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import test_builder.paths.MyPaths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	String configFileName = "base_test_config.json";
	String currentDirectory = System.getProperty("user.dir");

	private final Path configFilePath = MyPaths.getBaseTestConfigPath();

	@BeforeSuite
	public void runBaseTest() {
		logger.info("Path to base config file: " + configFilePath);
		TestBuilder tests = new TestBuilder(configFilePath);
		tests.buildTests();
		tests.executeCommands();
	}
}
