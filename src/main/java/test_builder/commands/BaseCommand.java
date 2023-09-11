package test_builder.commands;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseCommand implements TestCommand {
	protected final JSONObject jsonObject;

	public BaseCommand(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	protected SelenideElement getElement(JSONObject jsonObject) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		String selectorType;
		String selector;

		try {
			selector = jsonObject.getString("selector");
		} catch (JSONException e) {
			logger.error("selector not found", e);
			return null;
		}

		try {
			selectorType = jsonObject.getString("selector_type").toLowerCase();
		} catch (JSONException e) {
			logger.error("selector type not found", e);
			return null;
		}

		switch (selectorType) {
			case "xpath":
				return $(By.xpath(selector));
			case "element id":
				return $(By.id(selector));
			case "element name":
				return $(By.name(selector));
			case "css selector":
				return $(By.cssSelector(selector));
			case "class name":
				return $(By.className(selector));
			default:
				logger.error("unknown selector type: " + selectorType);
				return null;
		}
	}
}
