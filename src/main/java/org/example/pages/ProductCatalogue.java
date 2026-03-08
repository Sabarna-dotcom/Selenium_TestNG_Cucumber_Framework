package org.example.pages;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue( WebDriver driver ) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    WebElement product;

    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css="b")
    WebElement name;

    @FindBy(css="#toast-container")
    WebElement toast;

    @FindBy(css=".ng-animating")
    WebElement animation;


    public List<WebElement> getProductList() {
        waitForElementToAppear(product);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream()
                .filter(p -> getText(p.findElement(By.cssSelector("b"))).equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(productName + " not found"));

        return prod;

    }

    public  void addProductToCart(String productName) {
        WebElement addToCartBtn = getProductByName(productName).findElement(By.cssSelector(".card-body button:last-of-type"));
        waitForElementToAppear(addToCartBtn);
        clickByJS(addToCartBtn);
        waitForElementToAppear(toast);
        waitForElementToDisappear(animation);
    }



}
