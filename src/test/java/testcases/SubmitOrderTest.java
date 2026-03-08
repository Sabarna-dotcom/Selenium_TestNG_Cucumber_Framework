package testcases;

import org.apache.commons.io.FileUtils;
import org.example.pages.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}};
    }

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(HashMap<String,String> input) throws IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCart();
        boolean match = cartPage.productValidate(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.selectCountry(input.get("country"));
        OrderConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.validateOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(input.get("orderConfirmText")));
    }


    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest() throws IOException {
        String productName = "ZARA COAT 3";
        String email = "rssarkar26@gmail.com";
        String password = "Test@123";

        ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

}
