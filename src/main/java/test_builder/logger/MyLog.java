package test_builder.logger;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {
	private final Logger logger;

	public MyLog(Class<?> myClass) {
		logger = LoggerFactory.getLogger(myClass);
	}

	public void errorLog(String errorMessage, Exception exception) {
		logger.error(errorMessage, exception);
		Allure.addAttachment("Error", errorMessage);
		Allure.addAttachment("Exception", exception.getMessage());
	}

	public void errorLog(String errorMessage) {
		logger.error(errorMessage, errorMessage);
		Allure.addAttachment("Error", errorMessage);
	}

	public void infoLog(String message) {
		logger.info(message);
		Allure.addAttachment("Info", message);
	}
}
