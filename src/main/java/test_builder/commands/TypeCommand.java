package test_builder.commands;

import io.qameta.allure.Allure;
import org.json.JSONObject;

public class TypeCommand extends BaseCommand {

	public TypeCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		String info = getInfo(jsonObject);

		if (!info.equals("")) {
			Allure.addAttachment("Info", getInfo(jsonObject));
		}
		getElement(jsonObject).setValue(getValue(jsonObject)).pressEnter();
	}
}
