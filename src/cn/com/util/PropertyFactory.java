package cn.com.util;
import java.io.IOException;
import java.util.Properties;
public class PropertyFactory {
	static Properties pops = new Properties();
	static Properties vpops = new Properties();
	static Properties errorPops = new Properties();
	static {
		try {
			pops.load(PropertyFactory.class.getClassLoader().getResourceAsStream("system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private PropertyFactory() {
	}; 

	public static String getProperty(String key) {
		return pops.getProperty(key);
	}
}
