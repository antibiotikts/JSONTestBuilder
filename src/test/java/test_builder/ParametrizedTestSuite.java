package test_builder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import test_builder.paths.MyPaths;

import java.nio.file.Path;

public class ParametrizedTestSuite {
	@DataProvider(name = "param")
	public static Object[][] testParameters() {
		return BuildJsonTestsMultiArray.getJsonFiles(MyPaths.getJsonParameterizedTestDirectory());
	}

	@Factory(dataProvider = "param")
	public Object[] createTestInstances(Path parameter) {
		return new Object[]{new ParametrizedTest(parameter)};
	}
}
