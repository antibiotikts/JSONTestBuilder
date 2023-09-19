package test_builder;

import io.qameta.allure.Allure;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader {
	private static final Logger logger = LoggerFactory.getLogger(JsonReader.class);

	public static JSONArray getJsonArray(Path path) {
		try {
			String jsonContent = Files.readString(path);
			try {
				JSONArray jsonArray = new JSONArray(jsonContent);
				logger.info("build JSON array successfully");
				return jsonArray;
			} catch (JSONException e) {
				String  errorMessage = "JSON array does not building";
				logger.error(errorMessage, e);
				Allure.addAttachment("Error", errorMessage);
				Allure.addAttachment("Exception", e.getMessage());
				assert false: errorMessage;
				return null;
			}
		} catch (IOException e) {
			String  errorMessage = "File does not read";
			logger.error(errorMessage, e);
			Allure.addAttachment("Error", errorMessage);
			Allure.addAttachment("Exception", e.getMessage());
			assert false: errorMessage;
			return null;
		}
	}
}
