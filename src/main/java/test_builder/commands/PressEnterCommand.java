package test_builder.commands;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.json.JSONObject;

public class PressEnterCommand extends BaseCommand {

	public PressEnterCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		Allure.addAttachment("Command", "Press enter");
		SelenideElement element = getElement(jsonObject);
		Allure.addAttachment("Element", element.toString());
		element.pressEnter();
	}
}
