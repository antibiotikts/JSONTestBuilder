package test_builder.commands;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.json.JSONObject;

public class SetValueCommand extends BaseCommand {
	public SetValueCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		Allure.addAttachment("Command", "Set value");
		SelenideElement element = getElement(jsonObject);
		Allure.addAttachment("Element", element.toString());
		String value = getValue(jsonObject);
		element.setValue(value);
		}
}
