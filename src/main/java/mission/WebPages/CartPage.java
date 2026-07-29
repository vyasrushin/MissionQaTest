package mission.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    private final By itemName = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
