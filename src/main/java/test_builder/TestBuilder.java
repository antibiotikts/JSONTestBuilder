package test_builder;

import io.qameta.allure.Allure;
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
				String errorMassage = "JSONArray is empty";
				logger.error(errorMassage);
				Allure.addAttachment("Error", errorMassage);
				return;
			}

			for (int i = 0; i < testSteps.length(); i++) {
				JSONObject step = testSteps.getJSONObject(i);
				String action = step.getString("action");

				Class<? extends TestCommand> commandClass = RegistrarOfCommands.getCommandMap().get(action.toLowerCase());
				if (commandClass == null) {
					String errorMassage = "Unknown action: " + action;
					logger.error(errorMassage);
					Allure.addAttachment("Error", errorMassage);
					continue;
				}
				logger.info(step.toString());
				commandQueue.add(commandClass.getConstructor(JSONObject.class).newInstance(step));
			}
		} catch (JSONException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			String errorMassage = "An error occurred while building tests";
			logger.error(errorMassage, e);
			Allure.addAttachment("Error", errorMassage);
			Allure.addAttachment("Exception", e.getMessage());
		}
	}

	public void executeCommands() {
		if(commandQueue.isEmpty()) {
			String errorMassage = "The command queue is empty";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			return;
		}

		while (!commandQueue.isEmpty()) {
			TestCommand command = commandQueue.poll();
			command.execute();
		}
	}
}
