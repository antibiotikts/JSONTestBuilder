package test_builder;

import org.json.JSONArray;
import org.json.JSONException;
import test_builder.logger.MyLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader {

	private static final MyLog logger = new MyLog(JsonReader.class);

	public static JSONArray getJsonArray(Path path) {
		try {
			String jsonContent = Files.readString(path);
			try {
				JSONArray jsonArray = new JSONArray(jsonContent);
				logger.infoLog("build JSON array successfully");
				return jsonArray;
			} catch (JSONException e) {
				String  errorMessage = "JSON array does not building";
				logger.errorLog(errorMessage, e);
				assert false: errorMessage;
				return null;
			}
		} catch (IOException e) {
			String  errorMessage = "File does not read";
			logger.errorLog(errorMessage, e);
			assert false: errorMessage;
			return null;
		}
	}

	public static JSONArray getJsonArray(String jsonContent) {
			try {
				JSONArray jsonArray = new JSONArray(jsonContent);
				logger.infoLog("build JSON array successfully");
				return jsonArray;
			} catch (JSONException e) {
				String  errorMessage = "JSON array does not building";
				logger.errorLog(errorMessage, e);
				assert false: errorMessage;
				return null;
			}
	}

	public static String getJsonString(Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			String  errorMessage = "File does not read";
			logger.errorLog(errorMessage, e);
			assert false: errorMessage;
			return null;
		}
	}
}
