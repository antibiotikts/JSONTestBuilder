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
		String info = getInfo(jsonObject);

		if (!info.equals("")) {
			Allure.addAttachment("Info", getInfo(jsonObject));
		}
		Allure.addAttachment("Command", "Set value");
		SelenideElement element = getElement(jsonObject);
		Allure.addAttachment("Element", element.toString());
		String value = getValue(jsonObject);
		element.setValue(value);
		}
}
