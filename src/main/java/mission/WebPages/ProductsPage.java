package mission.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import mission.utils.LoadProp;
import mission.utils.WaitUtils;

public class ProductsPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;
    
    private final By title = By.className("title");
    // Intentional bug: wrong inventory item button id
    private final By backpackAddToCart = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, LoadProp.getInt("explicitWait"));
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCart).click();
        waitUtils.waitForSeconds(1);
    }

    public void openCart() {
        driver.findElement(cartLink).click();
        waitUtils.waitForSeconds(1);
    }
}
