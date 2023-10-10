package test_builder.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import test_builder.MyTest;

public class DescriptionListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		if (result.getInstance() instanceof MyTest) {
			MyTest myTest = (MyTest) result.getInstance();
			result.getMethod().setDescription(myTest.getJsonName());
		}
	}
}
