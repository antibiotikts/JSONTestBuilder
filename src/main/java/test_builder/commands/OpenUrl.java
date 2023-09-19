package test_builder.commands;

import io.qameta.allure.Allure;
import org.json.JSONObject;

import static com.codeborne.selenide.Selenide.open;

public class OpenUrl extends BaseCommand {

	public OpenUrl(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		Allure.addAttachment("Command", "Open");
		open(getUrl(jsonObject));
	}
}
