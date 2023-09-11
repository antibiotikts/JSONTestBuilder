package test_builder;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
				logger.error("JSON array does not building", e);
				return null;
			}
		} catch (IOException e) {
			logger.error("File does not read", e);
			return null;
		}
	}
}
