package test_builder.commands;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_builder.ElementsMap;
import test_builder.MyElement;
import test_builder.keys.Keys;
import test_builder.keys.SelectorTypeKeys;
import test_builder.paths.MyPaths;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseCommand implements TestCommand {
	protected final JSONObject jsonObject;
	protected static Map<String, MyElement> elementMap;

	static {
		elementMap = ElementsMap.getElementsMap(MyPaths.getElementsFilePath());
	}

	public BaseCommand(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	protected SelenideElement getElement(JSONObject jsonObject) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		String selectorType = jsonObject.optString(Keys.SELECTOR_TYPE, null);

		if (selectorType == null) {
			String errorMassage = "Selector type not found";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		String element = jsonObject.optString(Keys.ELEMENT_KEY, null);

		if (element != null) {
			return findElementByTypeInMap(element, selectorType, logger);
		}

		String selector = jsonObject.optString(Keys.SELECTOR_KEY, null);

		if (selector != null) {
			return findElementByType(selector, selectorType, logger);
		}
		String errorMassage = "Element and selector not found";
		logger.error(errorMassage);
		Allure.addAttachment("Error", errorMassage);
		assert false: errorMassage;
		return null;
	}

	protected String getValue(JSONObject jsonObject) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			String value;
			value = jsonObject.getString(Keys.VALUE_KEY);
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
			condition = jsonObject.getString(Keys.CONDITION_KEY);
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
			url = jsonObject.getString(Keys.URL_KEY);
			//Selenide.executeJavaScript("return document.characterSet");
			logger.info("URL is: " + url);

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

	protected String getSecondValue(JSONObject jsonObject) {
		String secondValue;
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			secondValue = jsonObject.getString(Keys.SECOND_VALUE_KEY);
			Allure.addAttachment("second value", secondValue);
			logger.info("Second value: " + secondValue);
		} catch (JSONException e) {
			secondValue = null;
			String errorMassage = "Second value not found";
			Allure.addAttachment("Error", errorMassage);
			Allure.addAttachment("Exception", e.getMessage());
			logger.error(errorMassage, e);
			assert false: errorMassage;
		}
		return secondValue;
	}

	protected String getInfo(JSONObject jsonObject) {
		if(jsonObject == null) {
			return "";
		}
		String info;
		info = jsonObject.optString("info", "");
		return info;
	}

	private SelenideElement findElementByType(String selector, String selectorType, Logger logger) {


		switch (selectorType) {
			case SelectorTypeKeys.XPATH_KEY:
				return $(By.xpath(selector));
			case SelectorTypeKeys.ELEMENT_ID_KEY:
				return $(By.id(selector));
			case SelectorTypeKeys.ELEMENT_NAME_KEY:
				return $(By.name(selector));
			case SelectorTypeKeys.CSS_SELECTOR_KEY:
				return $(By.cssSelector(selector));
			case SelectorTypeKeys.CLASS_NAME_KEY:
				return $(By.className(selector));
			default:
				String errorMessage = "unknown selector type: " + selectorType;
				logger.error(errorMessage);
				Allure.addAttachment("Error", errorMessage);
				assert false: errorMessage;
				return null;
		}
	}

	private SelenideElement findElementByTypeInMap(String element, String selectorType, Logger logger) {
		MyElement myElement = elementMap.get(element);

		switch (selectorType) {
			case SelectorTypeKeys.XPATH_KEY:
				return myElement.getByXpath();
			case SelectorTypeKeys.ELEMENT_ID_KEY:
				return myElement.getById();
			case SelectorTypeKeys.ELEMENT_NAME_KEY:
				return myElement.getByName();
			case SelectorTypeKeys.CSS_SELECTOR_KEY:
				return myElement.getByCss_selector();
			case SelectorTypeKeys.CLASS_NAME_KEY:
				return myElement.getByClass_name();
			default:
				String errorMessage = "unknown selector type: " + selectorType;
				logger.error(errorMessage);
				Allure.addAttachment("Error", errorMessage);
				assert false: errorMessage;
				return null;
		}
	}

}
