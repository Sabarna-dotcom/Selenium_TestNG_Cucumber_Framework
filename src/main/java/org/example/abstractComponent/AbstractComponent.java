package org.example.abstractComponent;

import org.example.pages.CartPage;
import org.example.pages.OrderConfirmationPage;
import org.example.pages.OrderPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver ) {
        this.driver=driver;
    }

    @FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
    WebElement cart;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public  void waitForElementToDisappear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(findBy));
    }

    public void clickByJS(WebElement findBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", findBy);
    }

    public void sendKeys(WebElement locator, String keys) {
        locator.sendKeys(keys);
    }

    public void click(WebElement locator) {
        waitForElementToAppear(locator);
        locator.click();
    }

    public String getText(WebElement locator) {
        return locator.getText();
    }

    public void sendKeysToDropdownWithAction(WebElement locator, String option) {
        Actions a = new Actions(driver);
        a.sendKeys(locator,option).build().perform();
    }

    public CartPage goToCart() {
        clickByJS(cart);
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrdersPage()
    {
        clickByJS(orderHeader);
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
