package test_builder;

import io.qameta.allure.Allure;
import test_builder.classes_containers.ParametersPosition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildTestsList {
	private static final Logger logger = LoggerFactory.getLogger(BuildTestsList.class);

	public static List<JSONArray> getJsonArrayList(Path path) {
		String jsonContent = JsonReader.getJsonString(path);
		JSONArray jsonArray = JsonReader.getJsonArray(path);
		List<JSONArray> jsonArrayList = new ArrayList<>();

		List<ParametersPosition> parametersPositions = getParametersPosition(jsonArray);

		if(parametersPositions == null) {
			String errorMassage = "Parameters not found";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false;
			return null;
		}

		if(!isAllArraySizeEqual(parametersPositions)) {
			String errorMassage = "Different number of parameters, it is not possible to build a test";
			logger.error(errorMassage);
			Allure.addAttachment("Error", errorMassage);
			assert false;
			return null;
		}

		int size = parametersPositions.get(0).getArraySize();

		for (int i = 0; i < size; i++) {
			jsonArrayList.add(modificationJsonArray(jsonContent, parametersPositions, i));
		}

		return jsonArrayList;
	}

	private static List<ParametersPosition> getParametersPosition(JSONArray jsonArray) {
		List<ParametersPosition> parametersPositions = new ArrayList<>();

		if(jsonArray == null) {
			String errorMessage = "JSON array is null";
			logger.error(errorMessage);
			Allure.addAttachment("Error", errorMessage);
			return null;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
					String key = it.next().toString();
					if (jsonObject.get(key) instanceof JSONArray) {

						JSONArray value = jsonObject.getJSONArray(key);
						int size = value.length();
						parametersPositions.add(new ParametersPosition(i, key, size));
					}
				}
			} catch (JSONException e) {
				String errorMessage = "Error getting JSONObject, index -  " + i;
				logger.error(errorMessage, e);
				Allure.addAttachment("Error", errorMessage);
				Allure.addAttachment("Exception", e.getMessage());
				assert false;
				return null;
			}
		}
		return parametersPositions;
	}

	private static JSONArray modificationJsonArray(String jsonString, List<ParametersPosition> parametersPosition, int valueIndex) {
		JSONArray jsonArray = JsonReader.getJsonArray(jsonString);

		for(ParametersPosition parameterPosition : parametersPosition) {
			int actionIndex = parameterPosition.getActionIndex();
			String arrayKey = parameterPosition.getArrayKey();

			try {
				JSONObject jsonObject = jsonArray.getJSONObject(actionIndex);
				String value = jsonObject.getJSONArray(arrayKey).getString(valueIndex);
				jsonObject.put(arrayKey, value);
				jsonArray.put(actionIndex, jsonObject);
			} catch (JSONException e) {
				String errorMessage = "modification error, action index - " + actionIndex;
				logger.error(errorMessage, e);
				Allure.addAttachment("Error", errorMessage);
				Allure.addAttachment("Exception", e.getMessage());
			}
		}
		return jsonArray;
	}

	private static boolean isAllArraySizeEqual(List<ParametersPosition> parametersList) {
		if (parametersList.isEmpty()) {
			return true;
		}

		int firstArraySize = parametersList.get(0).getArraySize();

		for (ParametersPosition parameter : parametersList) {
			if (parameter.getArraySize() != firstArraySize) {
				return false;
			}
		}
		return true;
	}
}
