package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountSummaryPage extends BasePage{

    @FindBy(xpath = "//h2")
    public List<WebElement> summaryAccountList;

    public List<WebElement> accountTypesHeaders(String accountType){
        return Driver.get().findElements(By.xpath("//h2[contains(.,'"+accountType+"')]/following-sibling::div[1]//th"));
    }
}
