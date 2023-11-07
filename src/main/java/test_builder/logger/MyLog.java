package test_builder.logger;

import io.qameta.allure.Allure;
import org.slf4j.Logger;

public class MyLog {
	public static void errorLog(Logger logger, String errorMassage, Exception exception) {
		logger.error(errorMassage, errorMassage);
		Allure.addAttachment("Error", errorMassage);
		Allure.addAttachment("Exception", exception.getMessage());
	}

	public static void errorLog(Logger logger, String errorMassage) {
		logger.error(errorMassage, errorMassage);
		Allure.addAttachment("Error", errorMassage);
	}
}
