package com.haloTester.tests.homeWork;

import com.haloTester.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationForm {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void TC001() {
        driver.findElement(By.name("birthday")).sendKeys("32/32/2025");

        String expectedResult = "The date of birth is not valid";
        String actualResult = driver.findElement(By.xpath("//small[@*='date']")).getText();

        Assert.assertEquals(actualResult, expectedResult, "Verify that warning message is displayed");
    }

    @Test
    public void TC002() {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[contains(@for,'inlineCheckbox')]"));

        List<String> actualResult = new ArrayList<>();
        for (WebElement checkBox : checkBoxes) {
            actualResult.add(checkBox.getText());
        }

        String[] expectedResult = {"C++", "Java", "JavaScript"};

        for(int i=0; i<=2; i++) {
            Assert.assertEquals(expectedResult[i], actualResult.get(i), "Verify that following options for \n" +
                        "programming languages are displayed");
        }
    }

}
