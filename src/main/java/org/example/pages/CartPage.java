package org.example.pages;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage( WebDriver driver ) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    WebElement checkoutBtn;

    public boolean productValidate(String productName) {
        return cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage clickCheckout() {
        clickByJS(checkoutBtn);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }



}
