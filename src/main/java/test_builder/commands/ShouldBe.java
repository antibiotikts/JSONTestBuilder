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
			default:
				logger.error("Unknown condition");
		}


	}
}
