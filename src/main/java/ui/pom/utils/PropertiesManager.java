package ui.pom.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager
{
	private static Properties properties;

	static
	{
		properties = new Properties();
		try (FileInputStream inputStream = new FileInputStream("src/main/resources/application.properties"))
		{
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError("Failed to load properties file");
		}
	}

	private static String getProperty(String key)
	{
		return properties.getProperty(key);
	}

	public static String getUser()
	{
		return getProperty("correct.user");
	}

	public static String getPassword()
	{
		return getProperty("correct.password");
	}

	public static String getApiBaseUrl()
	{
		return getProperty("api.base.url");
	}

	public static String getUrl()
	{
		return getProperty("url");
	}

}