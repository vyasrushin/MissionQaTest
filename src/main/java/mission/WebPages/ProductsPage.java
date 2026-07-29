package mission.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private final WebDriver driver;

    private final By title = By.className("title");
    // Intentional bug: wrong inventory item button id
    private final By backpackAddToCart = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCart).click();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}
