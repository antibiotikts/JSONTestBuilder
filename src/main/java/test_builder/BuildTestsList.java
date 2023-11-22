package test_builder;

import test_builder.classes_containers.ParametersPosition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import test_builder.logger.MyLog;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildTestsList {
	private static final MyLog logger = new MyLog(BuildTestsList.class);

	public static List<JSONArray> getJsonArrayList(Path path) {
		String jsonContent = JsonReader.getJsonString(path);
		JSONArray jsonArray = JsonReader.getJsonArray(path);
		List<JSONArray> jsonArrayList = new ArrayList<>();

		List<ParametersPosition> parametersPositions = getParametersPosition(jsonArray);

		if(parametersPositions == null) {
			String errorMassage = "Parameters not found";
			logger.errorLog(errorMassage);
			assert false;
			return null;
		}

		if(!isAllArraySizeEqual(parametersPositions)) {
			String errorMassage = "Different number of parameters, it is not possible to build a test";
			logger.errorLog(errorMassage);
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
			String errorMassage = "JSON array is null";
			logger.errorLog(errorMassage);
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
				logger.errorLog(errorMessage, e);
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
				logger.errorLog(errorMessage, e);
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
