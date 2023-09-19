package test_builder.commands;

import org.json.JSONObject;

public class TypeCommand extends BaseCommand {

	public TypeCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		getElement(jsonObject).setValue(getValue(jsonObject)).pressEnter();

	}
}
