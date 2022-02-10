package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class LoginStepDefs {

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        new LoginPage().login();
        if(Driver.get().getTitle().equals("zero.webappsecurity.com")){
            Driver.get().navigate().back();
            OnlineBankingPage onlineBanking = new OnlineBankingPage();
            onlineBanking.onlineBanking.click();
            onlineBanking.account_summary_link.click();
        }
    }

    @Given("the user login page")
    public void the_user_login_page() {
        new AccountSummaryPage().signinButtonBase.click();
    }

    @When("the user type username {string} in username box")
    public void the_user_type_username_in_username_box(String username) {
        new LoginPage().user_login.sendKeys(username);
    }
    @When("the user type password {string} in password box and enter")
    public void the_user_type_password_in_password_box_and_enter(String password) {
        new LoginPage().user_password.sendKeys(password + Keys.ENTER);
    }
    @Then("the user should be on {string} page")
    public void the_user_should_be_on_page(String pageTitle) {
        if(Driver.get().getTitle().equals("zero.webappsecurity.com")){
            Driver.get().navigate().back();
            OnlineBankingPage onlineBanking = new OnlineBankingPage();
            onlineBanking.onlineBanking.click();
            onlineBanking.account_summary_link.click();
        }
        Assert.assertTrue("user on an unexpected page",Driver.get().getTitle().contains(pageTitle));
    }
    @Then("the error message {string} should be displayed")
    public void the_error_message_should_be_displayed(String warningText) {
        Assert.assertEquals("warning message not matched",warningText,new LoginPage().wrongEntryText.getText());
    }

}