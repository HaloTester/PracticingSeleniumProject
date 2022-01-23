package com.haloTester.tests;

import com.haloTester.pages.AdidasPages.CartPage;
import com.haloTester.pages.AdidasPages.LoginPage;
import com.haloTester.pages.AdidasPages.ProductPage;
import com.haloTester.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdidasTaskWithPOM extends TestBase {

    int expectedPurchaseAmount = 0;
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Test
    public void loginTest() throws IOException {
        String expectedUsername = "asdfghjklöä";
        loginPage.navigateTo(By.id("login2"));
        loginPage.login();
        System.out.println("loginPage.actualUsername.getText() = " + loginPage.actualUsername.getText());
        BrowserUtils.waitFor(2);
        String screenShot = "purchase";
        BrowserUtils.getScreenshot(screenShot);
        //Assert.assertTrue(loginPage.actualUsername.getText().contains(expectedUsername));
    }


    @Test
    public void purchaseTest() throws InterruptedException {

        expectedPurchaseAmount += productPage.productAdder("Laptops","Sony vaio i5");

        expectedPurchaseAmount += productPage.productAdder("Laptops","Dell i7 8gb");

        expectedPurchaseAmount -= productPage.productRemover("Dell i7 8gb");

        productPage.navigateTo("Cart");

        cartPage.placeOrderBtn.click();
        cartPage.fillUpForm();
        cartPage.purchaseBtn.click();
        cartPage.logOfPurchase();
        cartPage.okBtn.click();

        Assert.assertEquals(cartPage.actualPurchaseAmount,expectedPurchaseAmount,"Verify that last amount is as expected");
    }
}
