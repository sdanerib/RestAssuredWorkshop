package github_exercises;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static String getEnvironmentProperty(String property) throws IOException {
        String propValue = System.getProperty(property);
        String propertiesFileName = "workshop.properties";

        if (propValue == null) {
            Properties properties = new Properties();

            InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream(propertiesFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
            }

            propValue = properties.getProperty(property);

        }
        return propValue;
    }
}
