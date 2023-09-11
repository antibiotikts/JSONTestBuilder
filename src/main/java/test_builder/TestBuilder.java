package test_builder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_builder.commands.TestCommand;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

public class TestBuilder {
	private static final Logger logger = LoggerFactory.getLogger(TestBuilder.class);
	private final Queue<TestCommand> commandQueue = new LinkedList<>();
	private final Path path;

	public TestBuilder(Path path) {
		this.path = path;
	}

	public void buildTests() {
		try {
			JSONArray testSteps = JsonReader.getJsonArray(path);
			if(testSteps == null) {
				logger.error("JSONArray is empty");
				return;
			}

			for (int i = 0; i < testSteps.length(); i++) {
				JSONObject step = testSteps.getJSONObject(i);
				String action = step.getString("action");

				Class<? extends TestCommand> commandClass = RegistrarOfCommands.getCommandMap().get(action.toLowerCase());
				if (commandClass == null) {
					logger.error("Unknown action: " + action);
					continue;
				}
				logger.info(step.toString());
				commandQueue.add(commandClass.getConstructor(JSONObject.class).newInstance(step));
			}
		} catch (JSONException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			logger.error("An error occurred while building tests", e);
		}
	}

	public void executeCommands() {
		if(commandQueue.isEmpty()) {
			logger.error("The command queue is empty");
			return;
		}

		while (!commandQueue.isEmpty()) {
			TestCommand command = commandQueue.poll();
			command.execute();
		}
	}
}
