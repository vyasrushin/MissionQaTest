package mission.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import mission.utils.LoadProp;
import mission.utils.WaitUtils;

public class CheckoutPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;
    
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    // Intentional bug: wrong field id
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By successMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, LoadProp.getInt("explicitWait"));
    }

    public void fillInformation(String fName, String lName, String zip) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(zip);
        waitUtils.waitForSeconds(1);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
        waitUtils.waitForSeconds(1);
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
        waitUtils.waitForSeconds(1);
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
