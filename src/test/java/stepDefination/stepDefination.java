package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class stepDefination extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public OrderConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException
    {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password)
    {
        productCatalogue = landingPage.loginApplication(username,password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName)
    {
        CartPage cartPage = productCatalogue.goToCart();

        boolean match = cartPage.productValidate(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.placeOrder();
    }


    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
        String confirmMessage = confirmationPage.validateOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable
    {
        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }


}
