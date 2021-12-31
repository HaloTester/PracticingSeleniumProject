package com.haloTester.tests.techlistic;

import com.github.javafaker.Faker;
import com.haloTester.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC004 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter email address and click Register button.
    4. Enter incorrect values in fields like., enter numbers in first and last name, city field etc.,
    and enter alphabets in Mobile no, Zip postal code etc., and click on 'Register' button.
    5. Verify that error messages fpr respective fields are displaying.
    */

    WebDriver driver;
    Faker faker;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        faker = new Faker();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        //driver.quit();
    }

    @Test
    public void TC004() {
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.linkText("Sign in")).click();

        driver.findElement(By.id("email_create")).sendKeys(faker.internet().emailAddress());

        driver.findElement(By.id("SubmitCreate")).click();

        driver.findElement(By.name("customer_firstname")).sendKeys("1234");
        driver.findElement(By.name("customer_lastname")).sendKeys("1234");
        driver.findElement(By.id("passwd")).sendKeys("123asd");

        driver.findElement(By.name("address1")).sendKeys("Blambee 5");
        driver.findElement(By.name("city")).sendKeys("5263");

        WebElement statedropdown=driver.findElement(By.name("id_state"));
        Select oSelect=new Select(statedropdown);
        oSelect.selectByValue("4");

        driver.findElement(By.name("postcode")).sendKeys("abcde");

        WebElement country = driver.findElement(By.name("id_country"));
        Select cSelect = new Select(country);
        cSelect.selectByValue("21");

        driver.findElement(By.name("phone_mobile")).sendKeys("asdfghjkl");

        driver.findElement(By.name("alias")).clear();
        driver.findElement(By.name("alias")).sendKeys("Office");

        driver.findElement(By.id("submitAccount")).click();

        List<WebElement> marker = driver.findElements(By.xpath("//div[@id='center_column']//div//ol//li"));

        for (WebElement each : marker) {
            String actualResult = each.getText();
            Assert.assertTrue(actualResult.contains("is invalid"),"Verify that error messages contain invalid.");
        }
    }
}
