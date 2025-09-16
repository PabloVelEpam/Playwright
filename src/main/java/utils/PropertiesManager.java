package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager
{

	private Properties properties;

	public PropertiesManager(String filePath) throws IOException
	{
		properties = new Properties();
		try (FileInputStream inputStream = new FileInputStream(filePath))
		{
			properties.load(inputStream);
		}
	}

	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}

	public String getProperty(String key, String defaultValue)
	{
		return properties.getProperty(key, defaultValue);
	}

}