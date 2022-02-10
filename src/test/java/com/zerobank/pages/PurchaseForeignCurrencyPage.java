package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PurchaseForeignCurrencyPage extends PayBillsPage{

    public WebElement pc_calculate_costs;
    public WebElement pc_amount;

    @FindBy(id = "pc_currency")
    public WebElement currencyDropdown;

    @FindBy(id = "pc_inDollars_true")
    public WebElement radioDollar;

    @FindBy(id = "pc_inDollars_false")
    public WebElement radioSelectedCurrency;
}
