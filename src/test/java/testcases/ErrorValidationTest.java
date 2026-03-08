package testcases;

import org.example.pages.CartPage;
import org.example.pages.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;
import testComponents.Retry;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test(groups={"ErrorHandling"})
    public void LoginErrorValidation() throws IOException {
        String email = "rssarkar26@gmail.com";
        String password = "Test@12";
        String error = "Incorrect email or password.";

        landingPage.loginApplication(email,password);
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage, error);
    }

    @Test(retryAnalyzer = Retry.class)
    public void ProductErrorValidation() throws IOException {
        String productName = "ZARA COAT 5";
        String email = "rssarkar26@gmail.com";
        String password = "Test@123";

        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCart();
        boolean match = cartPage.productValidate(productName);
        Assert.assertFalse(match);
    }

}
