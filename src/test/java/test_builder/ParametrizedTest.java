package test_builder;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.TestInstanceParameter;
import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test_builder.listeners.DescriptionListener;

import java.nio.file.Path;
import java.util.List;

@Listeners(DescriptionListener.class)
public class ParametrizedTest extends BaseTest {
	private final Path jsonFilePath;

	@TestInstanceParameter("file name")
	private final String jsonName;

	public ParametrizedTest(Path jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
		this.jsonName = jsonFilePath.getFileName().toString();
	}

	public String getJsonName() {
		return jsonName;
	}

	@DataProvider(name = "listDataProvider")
	public Object[][] listDataProvider() {
		List<JSONArray> data = BuildTestsList.getJsonArrayList(jsonFilePath);
		Object[][] dataArray = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			dataArray[i][0] = data.get(i);
		}

		return dataArray;
	}

	@Test(dataProvider = "listDataProvider", description = "test")
	public void testRunJson(JSONArray jsonArray) {
		TestBuilder tests = new TestBuilder();
		tests.buildTests(jsonArray);
		tests.executeCommands();
		Allure.addAttachment("INFO", jsonFilePath.toString());
	}
}