package test_builder.commands;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;

import org.json.JSONObject;

public class ClickCommand extends BaseCommand {

	public ClickCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		String info = getInfo(jsonObject);

		if (!info.equals("")) {
			Allure.addAttachment("Info", getInfo(jsonObject));
		}

		Allure.addAttachment("Command", "Click" );
		SelenideElement element = getElement(jsonObject);
		Allure.addAttachment("element", element.toString());
		element.click();
	}
}
