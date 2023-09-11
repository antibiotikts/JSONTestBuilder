package test_builder.commands;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class TypeCommand extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(TypeCommand.class);

	public TypeCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			logger.info("Selector: " + jsonObject.getString("selector") + "; Value:" + jsonObject.getString("value"));

			//$(By.xpath(jsonObject.getString("selector"))).setValue(jsonObject.getString("value")).pressEnter();
			getElement(jsonObject).setValue(jsonObject.getString("value")).pressEnter();

		} catch (JSONException e) {
			logger.error("Element or value not found", e);
		}
	}
}
