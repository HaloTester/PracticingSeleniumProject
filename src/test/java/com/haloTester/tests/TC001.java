package com.haloTester.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.haloTester.utilities.WebDriverFactory;

import java.util.concurrent.TimeUnit;


public class TC001 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter your email address in 'Create and account' section.
    4. Click on Create an Account button.
    5. Enter your Personal Information, Address and Contact info.
    6. Click on Register button.
    7. Validate that user is created.
    */

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.linkText("Sign in")).click();

        Faker faker = new Faker();

        driver.findElement(By.name("email_create")).sendKeys(faker.internet().emailAddress());

        driver.findElement(By.name("SubmitCreate")).click();

        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Ahmet");
        driver.findElement(By.name("customer_lastname")).sendKeys("Kirbac");
        driver.findElement(By.id("passwd")).sendKeys("123asd");

        WebElement dayOfBirth = driver.findElement(By.name("days"));
        Select daySelect = new Select(dayOfBirth);
        daySelect.selectByValue("7");

        WebElement monthOfBirth = driver.findElement(By.name("months"));
        Select monthSelect = new Select(monthOfBirth);
        monthSelect.selectByValue("11");

        WebElement yearOfBirth = driver.findElement(By.name("years"));
        Select yearSelect = new Select(yearOfBirth);
        yearSelect.selectByValue("1985");

        driver.findElement(By.name("firstname")).sendKeys("Ahmet");
        driver.findElement(By.name("lastname")).sendKeys("Kirbac");
        driver.findElement(By.name("address1")).sendKeys("Blambee 5");
        driver.findElement(By.name("city")).sendKeys("Kansas");

        WebElement statedropdown=driver.findElement(By.name("id_state"));
        Select oSelect=new Select(statedropdown);
        oSelect.selectByValue("4");

        driver.findElement(By.name("postcode")).sendKeys("79541");

        WebElement country = driver.findElement(By.name("id_country"));
        Select cSelect = new Select(country);
        cSelect.selectByValue("21");

        driver.findElement(By.name("phone_mobile")).sendKeys("0181045678");

        driver.findElement(By.name("alias")).clear();
        driver.findElement(By.name("alias")).sendKeys("Office");

        driver.findElement(By.id("submitAccount")).click();

        String expectedResult = "Ahmet Kirbac";
        String actualResult = driver.findElement(By.xpath("//a[@class='account']")).getText();

        if(actualResult.equals(expectedResult)){
            System.out.println("PASSED");
        }else{
            System.out.println("FAILED");
            System.out.println("expectedResult = " + expectedResult);
            System.out.println("actualResult = " + actualResult);
        }

        driver.quit();
    }
}