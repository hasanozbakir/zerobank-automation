package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "signin_button")
    public WebElement signinButtonBase;

    @FindBy(css = "#onlineBankingMenu strong")
    public WebElement onlineBanking;

    public WebElement account_summary_link;

    public WebElement getLinkElement(String linkName){
        return Driver.get().findElement(By.partialLinkText(linkName));
    }
}
