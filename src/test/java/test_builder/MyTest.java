package test_builder;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.testng.TestInstanceParameter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_builder.listeners.DescriptionListener;

import java.nio.file.Path;

@Listeners(DescriptionListener.class)
public class MyTest extends BaseTest {
	private final Path jsonFilePath;

	@TestInstanceParameter("file name")
	private final String jsonName;

	public MyTest(Path jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
		this.jsonName = jsonFilePath.getFileName().toString();
	}

	public String getJsonName() {
		return jsonName;
	}

	@Test(description = "test")
	public void testRunJson() {
		TestBuilder tests = new TestBuilder(jsonFilePath);
		tests.buildTests();
		tests.executeCommands();
		Allure.addAttachment("INFO", jsonFilePath.toString());
	}
}