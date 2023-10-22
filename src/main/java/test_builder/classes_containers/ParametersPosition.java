package test_builder.classes_containers;

public class ParametersPosition {
	private final int actionIndex;
	private final String arrayKey;
	private final int arraySize;

	public ParametersPosition(int actionIndex, String arrayKey, int arraySize){
		this.actionIndex = actionIndex;
		this.arrayKey = arrayKey;
		this.arraySize = arraySize;
	}

	public int getActionIndex() {
		return actionIndex;
	}

	public String getArrayKey() {
		return arrayKey;
	}

	public int getArraySize() {
		return arraySize;
	}

	@Override
	public String toString() {
		return "ParametersPosition{" +
				"actionIndex=" + actionIndex +
				", arrayKey='" + arrayKey + '\'' +
				", arraySize=" + arraySize +
				'}';
	}
}
