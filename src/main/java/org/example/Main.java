package org.example;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import test_builder.classes_containers.ParametersPosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws JSONException {
		String jsonString = "[{\"parametrz\": true},{\"action\":\"action1\",\"value\":[\"1\",\"2\",\"3\"]},{\"action\":\"action2\",\"value2\":[\"a\",\"b\",\"c\"]},{\"action\":\"action3\",\"value\":\"D\"},{\"action\":\"action3\"}]";
		JSONArray jsonArray = new JSONArray(jsonString);
		List<JSONArray> jsonArrayList = new ArrayList<>();

		List<ParametersPosition> parametersPositions = getParametersPosition(jsonArray);

		int size = parametersPositions.get(0).getArraySize();

		for (int i = 0; i < size; i++) {
			jsonArrayList.add(modificationJsonArray(jsonString, parametersPositions, i));
		}

		for (JSONArray jsonArray2 : jsonArrayList) {
			for (int i = 0; i < jsonArray2.length(); i++) {
				System.out.println(jsonArray2.get(i).toString());

			}
			System.out.println("---------------------");
		}

		for (ParametersPosition parameter : parametersPositions) {
			System.out.println(parameter.toString());
		}

	}

	private static List<ParametersPosition> getParametersPosition(JSONArray jsonArray) throws JSONException {

		List<ParametersPosition> parametersPositions = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);

			for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
				String key = it.next().toString();
				if (jsonObject.get(key) instanceof JSONArray) {

					JSONArray value = jsonObject.getJSONArray(key);
					int size = value.length();
					parametersPositions.add(new ParametersPosition(i, key, size));
				}
			}
		}
		return parametersPositions;
	}

	private static JSONArray modificationJsonArray(String jsonString, List<ParametersPosition> parametersPosition, int valueIndex) throws JSONException {
		JSONArray jsonArray = new JSONArray(jsonString);



			for(ParametersPosition parameterPosition : parametersPosition) {
				int actionIndex = parameterPosition.getActionIndex();
				String arrayKey = parameterPosition.getArrayKey();

			JSONObject jsonObject = jsonArray.getJSONObject(actionIndex);
			String value = jsonObject.getJSONArray(arrayKey).getString(valueIndex);
			jsonObject.put(arrayKey, value);
			jsonArray.put(actionIndex, jsonObject);
		}
		return jsonArray;
	}

}