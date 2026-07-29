package mission.Base;

import java.text.MessageFormat;
import java.time.Duration;
import mission.utils.LoadProp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup extends BasePage {

    public static String browser = null;
    WebDriver driver;


    public WebDriver selectBrowser() {
    	
        browser = LoadProp.getProperty("Browser");

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            
        	WebDriverManager.chromedriver().setup();
        	ChromeOptions chromeOptions = new ChromeOptions();
            
        	// Disable Google Password Manager popup
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);

			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-infobars");

			driver = new ChromeDriver(chromeOptions);
            
        } else {
            System.out.println(MessageFormat.format("Wrong Browser: {0}", browser));
        }

        driver.manage().timeouts().implicitlyWait(
        	    Duration.ofSeconds(Integer.parseInt(LoadProp.getProperty("implicitWait"))));
        
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(LoadProp.getProperty("implicitWait"))),null);
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        return BasePage.getDriver(); // Implement for Threading
        //return driver;
    }
}
