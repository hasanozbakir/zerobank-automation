package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AccountActivityStepDefs {

    @Then("the dropdown default option should be {string}")
    public void the_dropdown_default_option_should_be(String accountType) {
        Select select=new Select(new AccountActivityPage().accountDropdown);
        Assert.assertEquals("default type is not"+accountType,accountType,select.getFirstSelectedOption().getText());
    }

    @Then("the dropdown should have the following options")
    public void the_dropdown_should_have_the_following_options(List<String> dropDownOptions) {
        Select select=new Select(new AccountActivityPage().accountDropdown);
        List<String> actualOptions=new ArrayList<>();
        for (WebElement option : select.getOptions()) {
            if(!option.getText().isEmpty()){
                actualOptions.add(option.getText());
            }
        }
        Assert.assertTrue("dropdown options different from expected",dropDownOptions.equals(actualOptions));
    }

    @Then("Transactions table should have the following column names")
    public void transactions_table_should_have_the_following_column_names(List<String> transactionsColumns) {
        List<String> actualTransactionsHeaders=new ArrayList<>();
        for (WebElement tableHeader : new AccountActivityPage().tarnsactionsTableHeaders) {
            if(!tableHeader.getText().isEmpty()){
                actualTransactionsHeaders.add(tableHeader.getText());
            }
        }
        Assert.assertTrue("transactions columns not as expected",transactionsColumns.equals(actualTransactionsHeaders));
    }

}
