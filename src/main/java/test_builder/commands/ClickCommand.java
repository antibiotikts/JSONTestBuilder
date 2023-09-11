package test_builder.commands;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class ClickCommand extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(CloseCommand.class);

	public ClickCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			$(jsonObject.getString("selector")).click();
		} catch (JSONException e) {
			logger.error("selector not found", e);
		}
	}
}
