package test_builder.commands;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseCommand extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(CloseCommand.class);

	public CloseCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		Allure.addAttachment("Info", getInfo(jsonObject));
		Allure.addAttachment("Command", "Close");
		logger.info("The web driver is close");
		Selenide.closeWebDriver();
	}
}
