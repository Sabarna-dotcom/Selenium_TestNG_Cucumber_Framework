package org.example.pages;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public OrderConfirmationPage( WebDriver driver ) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmationMSG;

    public String validateOrderConfirmationMessage() {
        return getText(confirmationMSG);
    }

}
