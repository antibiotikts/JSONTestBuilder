package test_builder.commands;

import com.codeborne.selenide.Selenide;
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
		Selenide.closeWebDriver();
	}
}
