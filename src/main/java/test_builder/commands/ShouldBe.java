package test_builder.commands;

import com.codeborne.selenide.Condition;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShouldBe extends BaseCommand {
	Logger logger = LoggerFactory.getLogger(ShouldBe.class);

	public ShouldBe(JSONObject jsonObject) {
		super(jsonObject);
	}

	private String getValue(String jsonValue) {
		try {
			String value;
			value = jsonObject.getString(jsonValue);
			return value;
		} catch (JSONException e) {
			logger.error("value not found");
			return null;
		}
	}

	@Override
	public void execute() {
		String condition;
		try {
			 condition = jsonObject.getString("condition");
		} catch (JSONException e) {
			logger.error("Condition not found", e);
			return;
		}

		switch (condition) {
			case "visible":
				getElement(jsonObject).shouldBe(Condition.visible);
				break;
			case "hidden":
				getElement(jsonObject).shouldBe(Condition.hidden);
				break;
			case "exist":
				getElement(jsonObject).shouldBe(Condition.exist);
				break;
			case "empty":
				getElement(jsonObject).shouldBe(Condition.empty);
				break;
			case "selected":
				getElement(jsonObject).shouldBe(Condition.selected);
				break;
			case "text":
				String textValue = getValue("value");
				if (textValue == null) {
					break;
				}
				getElement(jsonObject).shouldBe(Condition.text(textValue));
				break;
			case "exactText":
				String exactTextValue = getValue("value");
				if (exactTextValue == null) {
					break;
				}
				getElement(jsonObject).shouldBe(Condition.exactText(exactTextValue));
				break;
			default:
				logger.error("Unknown condition");
		}
	}
}
