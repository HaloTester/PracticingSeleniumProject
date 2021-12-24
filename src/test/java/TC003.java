import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class TC003 {
    /*
    1. Open this url  http://automationpractice.com/index.php
    2. Click on sign in link.
    3. Enter email address and click Register button.
    4. Leave the mandatory fields (marked with *) blank and click Register button.
    5. Verify that error has been displayed for the mandatory fields.
    */

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        driver.manage().window().maximize();

        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();

        Faker faker = new Faker();
        driver.findElement(By.cssSelector("#email_create")).sendKeys(faker.internet().emailAddress());

        driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();

        driver.findElement(By.name("submitAccount")).click();

        String expectedResult = "There are 8 errors";
        String actualResult = driver.findElement(By.xpath("//*[contains(text(),'error')]")).getText();

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
