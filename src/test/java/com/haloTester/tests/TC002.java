package com.haloTester.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.haloTester.utilities.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class TC002 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter invalid email address in the email box and click enter.
    4. Validate that an error message is displaying saying "Invalid email address."
     */
    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.linkText("Sign in")).click();

        driver.findElement(By.cssSelector(" #email_create")).sendKeys("invalid@invalid");

        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        String expectedResult = "Invalid email address.";
        String actualResult = driver.findElement(By.xpath("//*[@id='create_account_error']//ol//li")).getText();

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
