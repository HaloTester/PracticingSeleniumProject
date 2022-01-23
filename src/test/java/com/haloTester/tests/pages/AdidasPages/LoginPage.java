package com.haloTester.tests.pages.AdidasPages;

import com.haloTester.utilities.ConfigurationReader;
import com.haloTester.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePageForAdidas{

    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "loginusername")
    public WebElement usernameInputBox;

    @FindBy(id = "loginpassword")
    public WebElement passwordInputBox;

    @FindBy(css = "button[onclick='logIn()']")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[@id='nameofuser']")
    public WebElement actualUsername;

    public void login(){
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        loginBtn.click();
    }
}
