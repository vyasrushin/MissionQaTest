package mission.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import mission.utils.LoadProp;
import mission.utils.WaitUtils;

public class CartPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By itemName = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, LoadProp.getInt("explicitWait"));
    }

    public String getItemName() {
    	waitUtils.waitForSeconds(1);
        return driver.findElement(itemName).getText();
    }

    public void clickCheckout() {
    	waitUtils.waitForSeconds(1);
        driver.findElement(checkoutButton).click();
        waitUtils.waitForSeconds(1);
    }
}
