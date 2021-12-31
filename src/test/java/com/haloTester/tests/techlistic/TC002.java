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

public class TC002 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter invalid email address in the email box and click enter.
    4. Validate that an error message is displaying saying "Invalid email address."
     */

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void TC002(){
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.linkText("Sign in")).click();

        driver.findElement(By.cssSelector(" #email_create")).sendKeys("invalid@invalid");

        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        String expectedResult = "Invalid email address.";
        String actualResult = driver.findElement(By.xpath("//*[@id='create_account_error']//ol//li")).getText();

        Assert.assertEquals(expectedResult, actualResult, "Verify that an error message is displayed");
    }
}
