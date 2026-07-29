package mission.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProp {

    static Properties prop;
    static FileInputStream input;
    public static String testData = "/src/test/resources/config/TestData.properties";
    private static File currentDirectory = new File(System.getProperty("user.dir"));


    public static String getProperty(String key) {
        prop = new Properties();

        try {
        	FileInputStream input = new FileInputStream(currentDirectory + testData);
            prop.load(input);
            input.close();
        } catch (IOException e) {
        	throw new RuntimeException("Unable to read property file", e);
        }
        return prop.getProperty(key);
    }
    
    public static String get(String key) {
        return getProperty(key);
    }
    
    public static int getInt(String key) {
        return Integer.parseInt(getProperty(key));
    }
}
