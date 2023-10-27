package test_builder.commands;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class AssertCommand extends BaseCommand{
	private static final Logger logger = LoggerFactory.getLogger(AssertCommand.class);

	public AssertCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		Allure.addAttachment("Command", "Assert");
		SelenideElement element = getElement(jsonObject);
		String actualValue = element.getText();
		String expectedValue = getValue(jsonObject);

		logger.info("Actual value: " + actualValue + "; Expected value: " + expectedValue);
		String info = getInfo(jsonObject);

		if (!info.equals("")) {
			Allure.addAttachment("Info", getInfo(jsonObject));
		}

		Allure.addAttachment("Element", element.toString());
		Allure.addAttachment("Actual value", actualValue);
		Allure.addAttachment("Expected value", expectedValue);

		Assert.assertEquals(actualValue, expectedValue);
	}
}

