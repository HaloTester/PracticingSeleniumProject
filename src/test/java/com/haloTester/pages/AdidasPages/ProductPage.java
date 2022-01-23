package com.haloTester.pages.AdidasPages;

import com.haloTester.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePageForAdidas{

    public ProductPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(tagName = "h2")
    public WebElement productName;

    @FindBy(tagName = "h3")
    public WebElement productPrice;

    @FindBy(xpath = "(//p)[4]")
    public WebElement productDescription;

}
