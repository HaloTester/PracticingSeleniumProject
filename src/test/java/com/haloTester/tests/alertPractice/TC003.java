package com.haloTester.tests.alertPractice;


import com.haloTester.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*
    1. Go to:  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
    2. Login with username: Tester, password: test
    3. Click  Order button
    4. Verify under Product Information, selected option is “MyMoney”
    5. Then select FamilyAlbum, make quantity 2, and click Calculate,
    6. Then verify Total is equal to Quantity*PricePerUnit
    */
public class TC003 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(2000);
        //driver.quit();
    }

    @Test
    public void TC003_01() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        driver.findElement(By.xpath("//*[contains(@id,'_username')]")).sendKeys("Tester");

        driver.findElement(By.xpath("//*[contains(@id,'_password')]")).sendKeys("test");

        driver.findElement(By.xpath("//*[contains(@id,'_button')]")).click();

        driver.findElement(By.xpath("//a[text()='Order']")).click();

        WebElement dropdownElement = driver.findElement(By.xpath("//*[contains(@id,'_ddlProduct')]"));

        Select productDropdown = new Select(dropdownElement);

        String expectedOption = "MyMoney";
        String actualOption = productDropdown.getFirstSelectedOption().getText();

        System.out.println("expectedOption = " + expectedOption);
        System.out.println("actualOption = " + actualOption);

        Assert.assertEquals(actualOption,expectedOption,"verify first selected option");
    }

    @Test
    public void TC003_02() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        driver.findElement(By.xpath("//*[contains(@id,'_username')]")).sendKeys("Tester");

        driver.findElement(By.xpath("//*[contains(@id,'_password')]")).sendKeys("test");

        driver.findElement(By.xpath("//*[contains(@id,'_button')]")).click();

        driver.findElement(By.xpath("//a[text()='Order']")).click();

        WebElement dropdownElement = driver.findElement(By.xpath("//*[contains(@id,'_ddlProduct')]"));

        Select productDropdown = new Select(dropdownElement);
        productDropdown.selectByValue("FamilyAlbum");

        WebElement quantity = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity"));
        quantity.sendKeys(Keys.chord(Keys.CONTROL,"a"),"2");

        driver.findElement(By.xpath("//*[@value='Calculate']")).click();

        String expectedTotal = "160";
        String actualTotal = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtTotal")).getAttribute("value");

        System.out.println("expectedTotal = " + expectedTotal);
        System.out.println("actualTotal = " + actualTotal);

        Assert.assertEquals(actualTotal, expectedTotal, "verify total price is equal to expected price");
    }

}
