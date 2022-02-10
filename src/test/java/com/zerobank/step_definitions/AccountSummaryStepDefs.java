package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.FindTransactionsPage;
import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountSummaryStepDefs {

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_page(String accountType) {
        new AccountSummaryPage().getLinkElement(accountType).click();
    }
    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String pageTitle) {
        Assert.assertTrue(Driver.get().getTitle().contains(pageTitle));
    }
    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String defaultSelection) {
        Select select=new Select(new AccountActivityPage().accountDropdown);
        Assert.assertEquals(defaultSelection,select.getFirstSelectedOption().getText());
    }
    @Given("the user accesses the {string} tab")
    public void the_user_accesses_the_tab(String summarySubPage) {
        new OnlineBankingPage().getLinkElement(summarySubPage).click();
        BrowserUtils.waitFor(2);
    }
    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_beginning_to_end(String beginning, String end) {
        FindTransactionsPage findTransactions=new FindTransactionsPage();
        findTransactions.fromDate.clear();
        findTransactions.toDate.clear();
        findTransactions.fromDate.sendKeys(beginning);
        findTransactions.toDate.sendKeys(end);
    }
    @When("clicks search")
    public void clicks_search() {
        new FindTransactionsPage().findButton.click();
        BrowserUtils.waitFor(1);
    }
    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_between_these_dates(String firstday,String lastday) {
        AccountActivityPage activityPage=new AccountActivityPage();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date first = (Date)formatter.parse(firstday);
            Date last = (Date)formatter.parse(lastday);
            for (WebElement date : activityPage.datesFromFindTransactions) {
                Assert.assertTrue(first.compareTo(formatter.parse(date.getText()))<=0
                        && last.compareTo(formatter.parse(date.getText()))>=0);
            }
        }catch (ParseException p){
            p.printStackTrace();
        }
    }
    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        AccountActivityPage activityPage=new AccountActivityPage();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dates=new ArrayList<>();
        List<Date> forCheck=new ArrayList<>();

        try{
            for (WebElement date : activityPage.datesFromFindTransactions) {
                dates.add(formatter.parse(date.getText()));
                forCheck.add(formatter.parse(date.getText()));
            }
        }catch (ParseException p){
            p.printStackTrace();
        }

        forCheck.sort(Comparator.reverseOrder());

        Assert.assertTrue("dates are not sorted by most recent day", dates.equals(forCheck));

    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated_date(String date) {
        AccountActivityPage activityPage=new AccountActivityPage();
        List<String> dates=new ArrayList<>();

        for (WebElement transactionDate : activityPage.datesFromFindTransactions) {
            dates.add(transactionDate.getText());
        }

        Assert.assertFalse("it contains the date",dates.contains(date));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description_item(String keyWord) {
        FindTransactionsPage findTransactions=new FindTransactionsPage();
        findTransactions.description.clear();
        findTransactions.description.sendKeys(keyWord);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing_keyWord(String keyWord) {
        for (WebElement result : new AccountActivityPage().descriptionResults) {
            Assert.assertTrue("any result does not include " + keyWord,result.getText().contains(keyWord));
        }
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String keyWord) {
        for (WebElement result : new AccountActivityPage().descriptionResults) {
            Assert.assertFalse("any result includes " + keyWord,result.getText().contains(keyWord));
        }
    }

    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_deposit() {
        List<String> deposit=new ArrayList<>();
        for (WebElement depositResult : new AccountActivityPage().depositResults) {
            if(!depositResult.getText().isEmpty()){
                deposit.add(depositResult.getText());
            }
        }
        Assert.assertTrue("no deposit entry",deposit.size()!=0);
    }
    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_withdrawal() {
        List<String> withdrawal=new ArrayList<>();
        for (WebElement withdrawalResult : new AccountActivityPage().withdrawalResults) {
            if(!withdrawalResult.getText().isEmpty()){
                withdrawal.add(withdrawalResult.getText());
            }
        }
        Assert.assertTrue("no withdrawal entry", withdrawal.size()!=0);
    }
    @When("user selects type {string}")
    public void user_selects_type(String type) {
        Select select=new Select(new FindTransactionsPage().typeDropdown);
        select.selectByVisibleText(type);
        BrowserUtils.waitFor(2);
        new FindTransactionsPage().findButton.click();
        BrowserUtils.waitFor(2);
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_deposit() {
        List<String> deposit=new ArrayList<>();
        for (WebElement depositResult : new AccountActivityPage().depositResults) {
            if(!depositResult.getText().isEmpty()){
                deposit.add(depositResult.getText());
            }
        }
        Assert.assertTrue("any deposit entry included",deposit.size()==0);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_withdrawal() {
        List<String> withdrawal=new ArrayList<>();
        for (WebElement withdrawalResult : new AccountActivityPage().withdrawalResults) {
            if(!withdrawalResult.getText().isEmpty()){
                withdrawal.add(withdrawalResult.getText());
            }
        }
        Assert.assertTrue("any withdrawal entry included",withdrawal.size()==0);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String pageTitle) {
        Assert.assertTrue("page title not matched",Driver.get().getTitle().contains(pageTitle));
    }
    @Then("Account Summary page should have to following account types")
    public void account_Summary_page_should_have_to_following_account_types(List<String> accountList) {
        List<String> actualList=new ArrayList<>();
        for (WebElement account : new AccountSummaryPage().summaryAccountList) {
            actualList.add(account.getText());
        }
        Assert.assertTrue("account list not as expected",accountList.equals(actualList));
    }

    @Then("{string} table should have following columns")
    public void table_should_have_following_columns(String accounType, List<String> accountHeaders) {
        List<String> actualAccountHeader=new ArrayList<>();
        for (WebElement header : new AccountSummaryPage().accountTypesHeaders(accounType)) {
            if(!header.getText().isEmpty()){
                actualAccountHeader.add(header.getText());
            }
        }
        Assert.assertTrue(accounType+" list not matched",accountHeaders.equals(actualAccountHeader));
    }


}