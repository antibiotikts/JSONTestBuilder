package test_builder;

import test_builder.commands.*;
import test_builder.keys.ActionKeys;

import java.util.HashMap;
import java.util.Map;

public class RegistrarOfCommands {
	private static final Map<String, Class<? extends TestCommand>> commandMap = new HashMap<>();

		static {
			commandMap.put(ActionKeys.getClickKey(), ClickCommand.class);
			commandMap.put(ActionKeys.getTypeKey(), TypeCommand.class);
			commandMap.put(ActionKeys.getOpenKey(), OpenUrl.class);
			commandMap.put(ActionKeys.getCloseKey(), CloseCommand.class);
			commandMap.put(ActionKeys.getPressEnterKey(), PressEnterCommand.class);
			commandMap.put(ActionKeys.getSetValueKey(), SetValueCommand.class);
			commandMap.put(ActionKeys.getAssertKey(), AssertCommand.class);
			commandMap.put(ActionKeys.getScrollToKey(), ScrollToCommand.class);
			commandMap.put(ActionKeys.getShouldBeKey(), ShouldBe.class);
			commandMap.put(ActionKeys.getSetBrowserKey(), SetBrowserCommand.class);
			commandMap.put(ActionKeys.getSetBrowserSizeKey(), SetBrowserSizeCommand.class);
		}

	public static Map<String, Class<? extends TestCommand>> getCommandMap() {
		return commandMap;
	}
}
