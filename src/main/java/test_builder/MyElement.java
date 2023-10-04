package test_builder;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import static com.codeborne.selenide.Selenide.$;

public class MyElement {
	private String element;
	private String xpath;
	private String id;
	private String name;
	private String css_selector;
	private String class_name;

	private static final Logger logger = LoggerFactory.getLogger(MyElement.class);

	public SelenideElement getByXpath() {
		if (xpath == null) {
			String errorMassage = "Element xpath is empty, please add xpath to your selectors file";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		return $(By.xpath(xpath));
	}

	public SelenideElement getById() {
		if (id == null) {
			String errorMassage = "Element id is empty, please add id to your selectors file";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		return $(By.id(id));
	}

	public SelenideElement getByName() {
		if (name == null) {
			String errorMassage = "Element name is empty, please add name to your selectors file";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		return $(By.name(name));
	}

	public SelenideElement getByCss_selector() {
		if (css_selector == null) {
			String errorMassage = "Element css selector is empty, please add css selector to your selectors file";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		return $(By.cssSelector(css_selector));
	}

	public SelenideElement getByClass_name() {
		if (class_name == null) {
			String errorMassage = "Element class name is empty, please add class name to your selectors file";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false: errorMassage;
			return null;
		}

		return $(By.className(class_name));
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCss_selector(String css_selector) {
		this.css_selector = css_selector;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
}
