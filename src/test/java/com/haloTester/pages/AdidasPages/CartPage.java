package com.haloTester.pages.AdidasPages;

import com.haloTester.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public CartPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(css = "button[class='btn btn-success']")
    public WebElement placeOrderBtn;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "card")
    public WebElement creditCard;

    @FindBy(id = "month")
    public WebElement month;

    @FindBy(id = "year")
    public WebElement year;

    @FindBy(css = "button[onclick='purchaseOrder()']")
    public WebElement purchaseBtn;

    @FindBy(css = "button[class='confirm btn btn-lg btn-primary']")
    public WebElement okBtn;

    public int actualPurchaseAmount = 0;

    public void fillUpForm() {
        Faker faker = new Faker();
        name.sendKeys(faker.name().fullName());
        country.sendKeys(faker.country().name());
        city.sendKeys(faker.country().capital());
        creditCard.sendKeys(faker.finance().creditCard());
        month.sendKeys("" + faker.number().numberBetween(1, 12));
        year.sendKeys("" + faker.number().numberBetween(2022,2025));
    }

    public void logOfPurchase(){
        String purchaseLog = Driver.get().findElement(By.xpath("//div[10]/p")).getText();
        String[] arr = purchaseLog.split(" ");
        int purchaseIDNumber = Integer.parseInt(arr[1].substring(0,7));
        actualPurchaseAmount = Integer.parseInt(arr[2]);
        System.out.println("-------Log of Shopping-------");
        System.out.println("ID number of purchase: " + purchaseIDNumber + "\nAmount of purchase: " + actualPurchaseAmount);
        System.out.println("-----------------------------");
    }
}
