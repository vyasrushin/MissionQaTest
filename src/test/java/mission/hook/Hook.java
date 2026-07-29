package mission.hook;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import mission.Base.BasePage;
import mission.Base.BrowserSetup;
import mission.WebPages.CartPage;
import mission.WebPages.CheckoutPage;
import mission.WebPages.HomePage;
import mission.WebPages.LoginPage;
import mission.WebPages.ProductsPage;
import mission.utils.LoadProp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;

public class Hook extends BasePage {
	
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static CartPage cartPage;
	public static CheckoutPage checkoutPage;
	public static ProductsPage productPage;
	
    BrowserSetup browsersetup = new BrowserSetup();
    public static Scenario scenario;
    private static final int WAIT_SEC = 20;


    @Before("@Restapi")
    public void beforeScenario(Scenario scenario) {
        Hook.scenario = scenario;
        System.out.println("Before hook executed: " + scenario.getName());
    }
    
    @Before("@ui")
    public void initializeTest() {

        BrowserSetup browserSetup = new BrowserSetup();
        browserSetup.selectBrowser();

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_SEC));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SEC));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(WAIT_SEC));

        // Initialize Page Objects AFTER driver is created
        loginPage = new LoginPage(getDriver());
        productPage = new ProductsPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
    }

    /**
     * Executed after each UI tagged scenario
     */
    
    @AfterStep("@ui")
    public void screenshot(Scenario scenario) {
        String screenShotFilename = scenario.getName().replace(" ", "")
                + new Timestamp(new Date().getTime()).toString().replaceAll("[^a-zA-Z0-9]", "")
                + "_" + LoadProp.getProperty("Browser") + ".jpg";
        
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(LoadProp.getProperty("ScreenshotLocation") + screenShotFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @After("@ui")
    public void tearDown() 
    {
       	quitDriver();
    }
}

