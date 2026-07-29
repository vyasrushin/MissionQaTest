package mission.Base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    // private static WebDriver driver;
	protected static ThreadLocal<WebDriver> driver  = new ThreadLocal<>(); // Add ThreadLocal for multiple theading
	
    public static void setDriver(WebDriver webDriver) {
        //driver = webDriver;
    	driver.set(webDriver); //Set Thread wise Web Driver 
    }

    public static WebDriver getDriver() {
        return driver.get();  // Get Thread wise Web Driver
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
