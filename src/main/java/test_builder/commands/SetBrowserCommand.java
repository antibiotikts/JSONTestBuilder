package test_builder.commands;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Configuration.browser;

public class SetBrowserCommand extends BaseCommand {

	private static final Logger logger = LoggerFactory.getLogger(SetBrowserCommand.class);

	public SetBrowserCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			browser = jsonObject.getString("browser");
		} catch (JSONException e) {
			logger.error("Parameter with browser name not found ", e);
		}
	}
}
