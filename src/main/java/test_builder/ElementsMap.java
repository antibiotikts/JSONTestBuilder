package test_builder;

import io.qameta.allure.Allure;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ElementsMap {
	public static Map<String, MyElement> getElementsMap(Path path) {
		Logger logger = LoggerFactory.getLogger(ElementsMap.class);

		Map<String, MyElement> resultMap = new HashMap<>();
		JSONArray jsonArray = JsonReader.getJsonArray(path);

		if(jsonArray == null) {
			return null;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonElement;
			String element;

			try {
				jsonElement = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
				String errorMassage = "JSON Object does not open";
				Allure.addAttachment("Error", errorMassage);
				Allure.addAttachment("Exception", errorMassage);
				logger.error(errorMassage, e);
				continue;
			}

			MyElement myElement = new MyElement();

			try {
				element = jsonElement.getString("element");
			} catch (JSONException e) {
				String errorMassage = "element does not found";
				Allure.addAttachment("Error", errorMassage);
				Allure.addAttachment("Exception", errorMassage);
				logger.error(errorMassage, e);
				continue;
			}

			String xpath = jsonElement.optString("xpath", null);
			String id = jsonElement.optString("id", null);
			String name = jsonElement.optString("name", null);
			String css_selector = jsonElement.optString("css selector", null);
			String class_name =	jsonElement.optString("class name", null);

			myElement.setElement(element);
			myElement.setXpath(xpath);
			myElement.setId(id);
			myElement.setName(name);
			myElement.setCss_selector(css_selector);
			myElement.setClass_name(class_name);

			resultMap.put(element, myElement);
		}
		return resultMap;
	}
}
