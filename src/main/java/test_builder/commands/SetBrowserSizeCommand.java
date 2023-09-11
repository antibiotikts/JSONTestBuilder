package test_builder.commands;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Configuration.browserSize;

public class SetBrowserSizeCommand extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(SetBrowserSizeCommand.class);

	public SetBrowserSizeCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			browserSize = jsonObject.getString("browser size");
		} catch (JSONException e) {
			logger.error("Parameter with browser size not found ", e);
		}
	}
}
