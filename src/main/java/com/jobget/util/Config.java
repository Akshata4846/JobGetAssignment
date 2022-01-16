package com.jobget.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author AVK2E5744
 * This class reads configurations from config.properties
 *
 */
public class Config {
	public static Properties props;

	
	/**
	 * @param data
	 * @return
	 * @throws IOException
	 * This method reads configuration.properties file and returns the value for each key
	 */
	public static String getProperty(String propName) throws IOException {
		FileInputStream input =  null;
		props = new Properties();
		try {
			input = new FileInputStream(
					System.getProperty("user.dir")+ "/src/main/java/com/jobget/config/config.properties");
		}
		catch(IOException e){
			System.out.println("config.properties file not found");
			throw e;
		}
		props.load(input);
		String propValue = props.getProperty(propName);
		return propValue;
	}

}

