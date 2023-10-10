package test_builder;

import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import test_builder.paths.MyPaths;

import java.nio.file.Path;

public class TestSuit3 {
	@DataProvider(name = "param")
	public static Object[][] testParameters() {
		return BuildJsonTestsMultiArray.getJsonFiles(MyPaths.getJsonTestDirectory());
	}

	//private final Object[] jsonFiles = BuildJsonTestsArray.getJsonFiles();

	//@Factory(dataProvider = "param")
//	public Object[] createTestInstances() {
//		if (jsonFiles == null) {
//			return null;
//		}
//		Object[] result = new Object[jsonFiles.length];
//		for (int i = 0; i < jsonFiles.length; i++) {
//			result[i] = new MyTest((Path) jsonFiles[i]); // Здесь используйте свой класс теста и передайте параметры
//		}
//		return result;
//	}

	@Factory(dataProvider = "param")
	public Object[] createTestInstances(Path parameter) {
		return new Object[]{new MyTest(parameter)};
	}
}
