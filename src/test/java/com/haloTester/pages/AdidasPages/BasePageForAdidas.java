package com.haloTester.pages.AdidasPages;

import com.haloTester.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageForAdidas {

    public BasePageForAdidas(){
        PageFactory.initElements(Driver.get(),this);
    }

    public void navigateTo(String linkStr){
        Driver.get().findElement(By.partialLinkText(linkStr)).click();
    }

    public void navigateTo(By by){
        Driver.get().findElement(by).click();
    }

    public int productAdder(String category,String product){
        navigateTo(category);
        navigateTo(product);
        navigateTo("Add to cart");

        WebElement priceElement = Driver.get().findElement(By.tagName("h3"));
        String priceWholeText = priceElement.getText();
        String[] arr = priceWholeText.split(" ");
        int listPrice = Integer.parseInt(arr[0].substring(1));

        WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = Driver.get().switchTo().alert();
        alert.accept();

        navigateTo("Home");

        return listPrice;
    }

    public int productRemover(String product) throws InterruptedException {
        navigateTo("Cart");

        String productLocator = "//*[contains(text(),'" + product + "')]/../td[4]/a";
        String priceLocator = "//*[contains(text(),'" + product + "')]/../td[3]";

        WebElement priceElement = Driver.get().findElement(By.xpath(priceLocator));
        int listPriceRemove = Integer.parseInt(priceElement.getText());

        WebElement deleteBtn = Driver.get().findElement(By.xpath(productLocator));
        deleteBtn.click();
        Thread.sleep(2000);
        return listPriceRemove;
    }


}
