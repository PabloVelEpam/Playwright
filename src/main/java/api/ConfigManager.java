package api;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager
{
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("src/main/resources/application.properties"));
		} catch (IOException e) {
			throw new RuntimeException("The configuration file could not be loaded", e);
		}
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getApiBaseUrl() {
		return properties.getProperty("api.base.url");
	}

	public static String getBoardEndpoint() {
		return properties.getProperty("entities.endpoint");
	}

	public static String getBoardsFromMemberEndpoint() {
		return properties.getProperty("entities.from.member.endpoint");
	}
}