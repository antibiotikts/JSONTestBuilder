package test_builder.commands;

import io.qameta.allure.Allure;
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
		String info = getInfo(jsonObject);

		if (!info.equals("")) {
			Allure.addAttachment("Info", getInfo(jsonObject));
		}
		Allure.addAttachment("Command", "Open");
		logger.info("Command open start");
		String url = getUrl(jsonObject);
		logger.info("Open URL: " + url);
		open(url);
		logger.info("Command open is finishing");
	}
}
