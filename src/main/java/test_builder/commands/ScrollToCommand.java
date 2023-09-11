package test_builder.commands;

import org.json.JSONObject;


public class ScrollToCommand extends BaseCommand {

	public ScrollToCommand(JSONObject jsonObject) {
		super(jsonObject);
	}

	@Override
	public void execute() {
		getElement(jsonObject).scrollTo();
	}
}
