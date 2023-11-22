package test_builder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import test_builder.commands.TestCommand;
import test_builder.logger.MyLog;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

public class TestBuilder {
	private static final MyLog logger = new MyLog(TestBuilder.class);
	private final Queue<TestCommand> commandQueue = new LinkedList<>();

	public void buildTests(Path path) {
		try {
			JSONArray testSteps = JsonReader.getJsonArray(path);
			if(testSteps == null) {
				String errorMessage = "JSONArray is empty";
				logger.errorLog(errorMessage);
				return;
			}

			for (int i = 0; i < testSteps.length(); i++) {
				JSONObject step = testSteps.getJSONObject(i);
				String action = step.getString("action");

				Class<? extends TestCommand> commandClass = RegistrarOfCommands.getCommandMap().get(action.toLowerCase());
				if (commandClass == null) {
					String errorMessage = "Unknown action: " + action;
					logger.errorLog(errorMessage);
					continue;
				}
				logger.infoLog(step.toString());
				commandQueue.add(commandClass.getConstructor(JSONObject.class).newInstance(step));
			}
		} catch (JSONException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			String errorMessage = "An error occurred while building tests";
			logger.errorLog(errorMessage, e);
		}
	}

	public void buildTests(JSONArray testSteps) {
		try {
			if(testSteps == null) {
				String errorMessage = "JSONArray is empty";
				logger.errorLog(errorMessage);
				return;
			}

			for (int i = 0; i < testSteps.length(); i++) {
				JSONObject step = testSteps.getJSONObject(i);
				String action = step.getString("action");

				Class<? extends TestCommand> commandClass = RegistrarOfCommands.getCommandMap().get(action.toLowerCase());
				if (commandClass == null) {
					String errorMessage = "Unknown action: " + action;
					logger.errorLog(errorMessage);
					continue;
				}
				logger.infoLog(step.toString());
				commandQueue.add(commandClass.getConstructor(JSONObject.class).newInstance(step));
			}
		} catch (JSONException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			String errorMessage = "An error occurred while building tests";
			logger.errorLog(errorMessage, e);
		}
	}

	public void executeCommands() {
		if(commandQueue.isEmpty()) {
			String errorMessage = "The command queue is empty";
			logger.errorLog(errorMessage);
			return;
		}

		while (!commandQueue.isEmpty()) {
			TestCommand command = commandQueue.poll();
			command.execute();
		}
	}
}
