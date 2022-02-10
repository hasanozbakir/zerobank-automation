package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    public WebElement user_login;
    public WebElement user_password;
    public WebElement submit;

    @FindBy(css = ".alert.alert-error")
    public WebElement wrongEntryText;


    public void login(){
        login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
    }

    public void login(String username,String password){
        new AccountSummaryPage().signinButtonBase.click();
        user_login.sendKeys(username);
        user_password.sendKeys(password);
        submit.click();
    }



}
