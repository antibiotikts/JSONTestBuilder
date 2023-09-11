package test_builder.commands;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class SetValueCommand extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(SetValueCommand.class);

	public SetValueCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			$(By.xpath(jsonObject.getString("selector"))).setValue(jsonObject.getString("value"));
		} catch (JSONException e) {
			logger.error("Element or value not found", e);
		}
	}
}
