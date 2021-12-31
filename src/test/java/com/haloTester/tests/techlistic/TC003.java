package com.haloTester.tests.techlistic;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.haloTester.utilities.WebDriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC003 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter email address and click Register button.
    4. Leave the mandatory fields (marked with *) blank and click Register button.
    5. Verify that error has been displayed for the mandatory fields.
    */

    WebDriver driver;
    Faker faker;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        driver.manage().window().maximize();

        faker = new Faker();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void TC003(){
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();

        driver.findElement(By.cssSelector("#email_create")).sendKeys(faker.internet().emailAddress());

        driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();

        driver.findElement(By.name("submitAccount")).click();

        String expectedResult = "There are 8 errors";
        String actualResult = driver.findElement(By.xpath("//*[contains(text(),'error')]")).getText();

        Assert.assertEquals(expectedResult, actualResult, "Verify that error has been displayed for the mandatory fields");
    }
}
