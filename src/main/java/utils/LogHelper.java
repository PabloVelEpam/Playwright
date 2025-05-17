package utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogHelper {

	public static void info(String msg, Object... args) {
		log.info(msg, args);
	}

	public static void debug(String msg, Object... args) {
		log.debug(msg, args);
	}

	public static void error(String msg, Object... args) {
		log.error(msg, args);
	}

	public static void error(String msg, Exception exception, Object... args) {
		log.error(msg, args);
		log.error("Exception message {}", exception.getMessage()); // Manejamos la excepción
	}

	public static void warn(String msg, Object... args) {
		log.warn(msg, args);
	}
}
