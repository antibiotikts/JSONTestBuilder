package test_builder;

import test_builder.commands.*;

import java.util.HashMap;
import java.util.Map;

public class RegistrarOfCommands {
	private static final Map<String, Class<? extends TestCommand>> commandMap = new HashMap<>();

		static {
			commandMap.put("click", ClickCommand.class);
			commandMap.put("type", TypeCommand.class);
			commandMap.put("open", OpenUrl.class);
			commandMap.put("close", CloseCommand.class);
			commandMap.put("press enter", PressEnterCommand.class);
			commandMap.put("set value", SetValueCommand.class);
			commandMap.put("assert", AssertCommand.class);
			commandMap.put("scroll to", ScrollToCommand.class);
			commandMap.put("should be", ShouldBe.class);
			commandMap.put("set browser", SetBrowserCommand.class);
			commandMap.put("set browser size", SetBrowserSizeCommand.class);
		}

	public static Map<String, Class<? extends TestCommand>> getCommandMap() {
		return commandMap;
	}
}
