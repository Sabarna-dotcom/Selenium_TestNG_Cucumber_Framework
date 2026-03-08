package org.example.pages;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage( WebDriver driver ) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country_dropdown;

    @FindBy(css=".ta-results")
    WebElement dropdown_value;

    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement valueIndia;

    @FindBy(css=".action__submit")
    WebElement placeOrderBtn;

    public void selectCountry(String country) {
        sendKeysToDropdownWithAction(country_dropdown,country);
        waitForElementToAppear(dropdown_value);
        clickByJS(valueIndia);
    }

    public OrderConfirmationPage placeOrder() {
        click(placeOrderBtn);
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);
        return confirmationPage;
    }


}
