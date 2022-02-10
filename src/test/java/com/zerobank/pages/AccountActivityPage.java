package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountActivityPage extends OnlineBankingPage{

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody/tr/td[1]")
    public List<WebElement> datesFromFindTransactions;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody/tr/td[2]")
    public List<WebElement> descriptionResults;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody/tr/td[3]")
    public List<WebElement> depositResults;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody/tr/td[4]")
    public List<WebElement> withdrawalResults;

    @FindBy(id = "aa_accountId")
    public WebElement accountDropdown;

    @FindBy(tagName = "th")
    public List<WebElement> tarnsactionsTableHeaders;


}
