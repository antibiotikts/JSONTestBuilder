package test_builder.commands;

import com.codeborne.selenide.Selenide;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

public class OpenUrl extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(OpenUrl.class);

	public OpenUrl(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		try {
			open(jsonObject.getString("url"));
			String charset = Selenide.executeJavaScript("return document.characterSet");
			logger.info("Page encoding: " + charset);
		} catch (JSONException e) {
			logger.error("Url not found", e);
		}

	}
}
