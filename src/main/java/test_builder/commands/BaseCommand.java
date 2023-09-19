package test_builder.commands;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
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
			String errorMessage = "selector not found";
			logger.error(errorMessage, e);
			Allure.addAttachment("Error", errorMessage);
			assert false: errorMessage;
			return null;
		}

		try {
			selectorType = jsonObject.getString("selector_type").toLowerCase();
		} catch (JSONException e) {
			String errorMessage = "selector type not found";
			logger.error(errorMessage, e);
			Allure.addAttachment("Error", errorMessage);
			assert false: errorMessage;
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
				String errorMessage = "unknown selector type: " + selectorType;
				logger.error(errorMessage);
				Allure.addAttachment("Error", errorMessage);
				assert false: errorMessage;
				return null;
		}
	}

	protected String getValue(JSONObject jsonObject) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			String value;
			value = jsonObject.getString("value");
			Allure.addAttachment("Value", value);
			return value;
		} catch (JSONException e) {
			String errorMessage = "value not found";
			Allure.addAttachment("Error", errorMessage);
			Allure.addAttachment("Exception", e.getMessage());
			logger.error(errorMessage, e);
			assert false: errorMessage;
			return null;
		}
	}

	protected String getCondition(JSONObject jsonObject) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		String condition;

		try {
			condition = jsonObject.getString("condition");
			Allure.addAttachment("Condition", condition);
		} catch (JSONException e) {
			String errorMessage = "Condition not found";
			Allure.addAttachment("Error", errorMessage);
			Allure.addAttachment("Exception", e.getMessage());
			logger.error(errorMessage, e);
			assert false: errorMessage;
			return null;
		}
		return condition;
	}

	protected String getUrl(JSONObject jsonObject) {
		String url;
		Logger logger = LoggerFactory.getLogger(this.getClass());

		try {
			url = jsonObject.getString("url");
			String charset = Selenide.executeJavaScript("return document.characterSet");
			logger.info("Page encoding: " + charset);
			if (charset != null) {
				Allure.addAttachment("Page encoding", charset);
			}
		} catch (JSONException e) {
			url = null;
			String errorMessage = "Url not found";
			Allure.addAttachment("Error", errorMessage);
			Allure.addAttachment("Exception", e.getMessage());
			logger.error(errorMessage, e);
			assert false: errorMessage;
		}
		return url;
	}
}
