package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindTransactionsPage extends AccountActivityPage{

    public WebElement description;
    public WebElement fromDate;
    public WebElement toDate;
    public WebElement fromAmount;
    public WebElement toAmount;

    @FindBy(name = "type")
    public WebElement typeDropdown;

    @FindBy(css = "[type='submit']")
    public WebElement findButton;

}
