package test_builder.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import test_builder.ParametrizedTest;

public class DescriptionListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		if (result.getInstance() instanceof ParametrizedTest) {
			ParametrizedTest myTest = (ParametrizedTest) result.getInstance();
			result.getMethod().setDescription(myTest.getJsonName());
		}
	}
}
