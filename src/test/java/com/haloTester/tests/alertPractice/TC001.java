package com.haloTester.tests.alertPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    /*
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Click me, to Open an alert after 5 seconds"
    3. Explicitly wait until alert is present
    4. Then handle the Javascript alert
    */

public class TC001 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void TC001() {
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");

        driver.findElement(By.id("alert")).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
