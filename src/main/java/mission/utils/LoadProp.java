package mission.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProp {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = LoadProp.class.getClassLoader()
                .getResourceAsStream("config/TestData.properties")) {

            if (input == null) {
                throw new RuntimeException("TestData.properties not found.");
            }

            prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load TestData.properties", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String get(String key) {
        return getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(getProperty(key));
    }
}