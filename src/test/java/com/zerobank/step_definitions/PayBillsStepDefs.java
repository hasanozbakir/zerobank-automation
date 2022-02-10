package com.zerobank.step_definitions;

import com.zerobank.pages.AddNewPayeePage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.pages.PaySavedPayeePage;
import com.zerobank.pages.PurchaseForeignCurrencyPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayBillsStepDefs {


    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> payeeInfo) {
        AddNewPayeePage addNewPayee=new AddNewPayeePage();

        addNewPayee.np_new_payee_name.sendKeys(payeeInfo.get("Payee Name"));
        addNewPayee.np_new_payee_address.sendKeys(payeeInfo.get("Payee Address"));
        addNewPayee.np_new_payee_account.sendKeys(payeeInfo.get("Account"));
        addNewPayee.np_new_payee_details.sendKeys(payeeInfo.get("Payee details"));
        addNewPayee.add_new_payee.click();

    }
    @Then("message {string} should be displayed for new payee")
    public void message_should_be_displayed_for_new_payee(String expextedMessage) {
        String actualText=new PayBillsPage().alert_content.getText();
        Assert.assertEquals("alert message not marched",expextedMessage,actualText);
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> currencyList) {
        Select select = new Select(new PurchaseForeignCurrencyPage().currencyDropdown);
        List<String> actualCurrencyList = new ArrayList<>();
        for (WebElement option : select.getOptions()) {
            actualCurrencyList.add(option.getText());
        }
        Assert.assertTrue("any currency not exist as an option",actualCurrencyList.containsAll(currencyList));
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        PurchaseForeignCurrencyPage foreignCurrency=new PurchaseForeignCurrencyPage();
        foreignCurrency.pc_amount.sendKeys("100");
        foreignCurrency.radioDollar.click();
        foreignCurrency.pc_calculate_costs.click();

    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        boolean isWarningExist=false;
        try {
            Alert alert=Driver.get().switchTo().alert();
            isWarningExist=true;
            alert.accept();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue("warning message not displayed",isWarningExist);
    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        PurchaseForeignCurrencyPage foreignCurrency=new PurchaseForeignCurrencyPage();
        Select select = new Select(foreignCurrency.currencyDropdown);
        select.selectByValue("GBP");
        foreignCurrency.radioSelectedCurrency.click();
        foreignCurrency.pc_calculate_costs.click();
    }

    @When("choose {string} from Payee")
    public void choose_from_Payee(String payeeType) {
        Select select= new Select(new PaySavedPayeePage().sp_payee);
        select.selectByVisibleText(payeeType);
    }
    @When("choose {string} from Account")
    public void choose_from_Account(String payeeAccount) {
        Select select= new Select(new PaySavedPayeePage().sp_account);
        select.selectByVisibleText(payeeAccount);
    }

    @When("type {string} for Amount")
    public void type_for_Amount(String payeeAmount) {
        new PaySavedPayeePage().sp_amount.sendKeys(payeeAmount);
    }
    @When("type {string} for Date")
    public void type_for_Date(String date) {
        new PaySavedPayeePage().sp_date.sendKeys(date);
    }
    @When("type {string} as Description")
    public void type_as_Description(String description) {
        new PaySavedPayeePage().sp_description.sendKeys(description);
    }
    @When("click pay button")
    public void click_pay_button() {
        new PaySavedPayeePage().pay_saved_payees.click();
    }

    @Then("the text {string} should be displayed")
    public void the_text_should_be_displayed(String notificationText) {
        Assert.assertEquals("text is different",notificationText,new PayBillsPage().alert_content.getText());
    }

    @Then("the text {string} should be displayed from Amount box")
    public void the_text_should_be_displayed_from_Amount_box(String warningText) {
        Assert.assertEquals(warningText,new PaySavedPayeePage().sp_amount.getAttribute("validationMessage"));
    }

    @Then("the text {string} should be displayed from Date box")
    public void the_text_should_be_displayed_from_Date_box(String warningText) {
        Assert.assertEquals(warningText,new PaySavedPayeePage().sp_date.getAttribute("validationMessage"));
    }




}
