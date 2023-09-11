package test_builder.commands;

import org.json.JSONException;
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
		try {
			String actualValue = getElement(jsonObject).getText();
			String expectedValue = jsonObject.getString("value");
			logger.info("Actual value: " + actualValue + "; Expected value: " + expectedValue);
			Assert.assertEquals(actualValue, expectedValue);
		} catch (JSONException e) {
			logger.error("Value not found", e);
		}
	}
}

