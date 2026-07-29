package mission.StepDefinition;

import mission.hook.Hook;
import mission.utils.LoadProp;
import mission.WebPages.CartPage;
import mission.WebPages.CheckoutPage;
import mission.WebPages.LoginPage;
import mission.WebPages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

public class UISteps {
	private LoginPage loginPage = Hook.loginPage;
	private ProductsPage productsPage = Hook.productPage;
	private CartPage cartPage = Hook.cartPage;
	private CheckoutPage checkoutPage = Hook.checkoutPage;

    @Given("the user opens the SauceDemo login page")
    public void theUserOpensTheSauceDemoLoginPage() {
        loginPage.open();
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        loginPage.loginAs(LoadProp.get("username"), LoadProp.get("password"));
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        Assert.assertEquals(productsPage.getTitle(), "Products", "Match the Products page title");
    }

    @And("the user adds a backpack to the cart")
    public void theUserAddsABackpackToTheCart() {
        productsPage.addBackpackToCart();
    }

    @And("the user opens the cart")
    public void theUserOpensTheCart() {
        productsPage.openCart();
    }

    @Then("the cart should contain the backpack")
    public void theCartShouldContainTheBackpack() {
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack");
    }

    @When("the user completes checkout")
    public void theUserCompletesCheckout() {
        cartPage.clickCheckout();
        checkoutPage.fillInformation("Ved", "Logic", "390001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
    }

    @Then("the order should be completed successfully")
    public void theOrderShouldBeCompletedSuccessfully() {
        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }
}
