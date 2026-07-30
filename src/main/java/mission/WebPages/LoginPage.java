package mission.WebPages;

import mission.utils.LoadProp;
import mission.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Intentional bug: username locator works, password locator is incorrect.
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
    	
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, LoadProp.getInt("explicitWait"));
    }

    public void open() {
        driver.get(LoadProp.getProperty("baseUrl"));
    }

    public void enterUsername(String username) {
        WebElement element = driver.findElement(usernameInput);
        waitUtils.waitForVisibility(element);
        
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
        waitUtils.waitForSeconds(1);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        waitUtils.waitForSeconds(1);
        clickLogin();
        waitUtils.waitForSeconds(1);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
