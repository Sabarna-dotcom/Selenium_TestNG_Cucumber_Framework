package org.example.pages;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent{

    WebDriver driver;

    public LandingPage( WebDriver driver ) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public ProductCatalogue loginApplication(String email, String password) {
        sendKeys(userEmail,email);
        sendKeys(userPassword,password);
        clickByJS(submit);
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage()
    {
        waitForElementToAppear(errorMessage);
        return getText(errorMessage);
    }

}
