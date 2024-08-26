package testcases;

import common.BaseSetup;
import io.qameta.allure.*;
import common.WebUI;
import listeners.ReportListener;
import locators.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SigninPage;

import java.io.File;
import java.time.Duration;

@Listeners(ReportListener.class)
@Epic("Regression Test login")
@Feature("Feat Login")
public class SinginTest extends BaseSetup {
    SigninPage signinPage;
    String msg_error_empty_us, msg_error_empty_pw, header_content, text_timezone_VN, text_timezone_JP;

    @Test(priority = 1, description = "Set timezone VN")
    public void clickTimezoneVN() {
        signinPage = new SigninPage(driver);
        signinPage.clickTimezone();
        text_timezone_VN = WebUI.getElementText(By.xpath(Locator.textTimezoneVN));
        Assert.assertEquals(text_timezone_VN, "(GMT+07:00)", "Not timezone VN");
    }

    @Test(priority = 2, description = "Change timezone to Japanese")
    public void changetimezoneJP() throws Exception {
        signinPage.clickTimezone();
        signinPage.clickChangeTimezone();
        signinPage.clickTimezoneJP();
        text_timezone_JP = WebUI.getElementText(By.xpath(Locator.btn_changeTimezone));
        Assert.assertEquals(text_timezone_JP, "(GMT+09:00)", "Not timezone Japanese");
    }

    @Test(priority = 3, description = "login when empty username and password")
    public void LoginEmptyUP() {
        signinPage.clickTimezone();
        signinPage.setEmail("");
        signinPage.setPassword("");
        signinPage.clickLoginButton();
        msg_error_empty_us = WebUI.getElementText(By.xpath(Locator.msg_error_empty_username));
        msg_error_empty_pw = WebUI.getElementText(By.xpath(Locator.msg_error_empty_password));
        Assert.assertEquals(msg_error_empty_us, "This field is required!", "Not show message error empty");
        Assert.assertEquals(msg_error_empty_pw, "This field is required!", "Not show message error empty");
    }

    @Test(priority = 4, description = "Login when empty username")
    public void LoginEmptyUsername() {
        signinPage.clickTimezone();
        signinPage.setEmail("");
        signinPage.setPassword("1234");
        signinPage.clickLoginButton();
        msg_error_empty_us = WebUI.getElementText(By.xpath(Locator.msg_error_empty_username));
        Assert.assertEquals(msg_error_empty_us, "This field is required!", "Not show message error empty");
    }

    @Test(priority = 5, description = "Login when empty password")
    public void LoginEmptyPassword() {
        signinPage.clickTimezone();
        signinPage.setEmail("000000");
        signinPage.setPassword("");
        signinPage.clickLoginButton();
        msg_error_empty_pw = WebUI.getElementText(By.xpath(Locator.msg_error_empty_password));
        Assert.assertEquals(msg_error_empty_pw, "This field is required!", "Not show message error empty");
    }

    @Test(priority = 6, description = "Login when wrong username and password")
    public void LoginWrongUP() {
        signinPage.clickTimezone();
        signinPage.setEmail("1234");
        signinPage.setPassword("1234");
        signinPage.clickLoginButton();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            //waiting for element exists in the DOM and is displayed (not hidden by css or other elements)
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "Username or password is incorrect", "Allert message not match");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Alert did not appear within the specified timeout.");
            e.printStackTrace();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException: No alert present.");
            e.printStackTrace();
        }
    }

    @Test(priority = 7, description = "Login when wrong username")
    public void LoginWrongUsername() throws Exception {
        signinPage.clickTimezone();
        signinPage.setEmail("1234");
        signinPage.setPassword("123456");
        signinPage.clickLoginButton();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//            WebElement alert = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "Username or password is incorrect", "Allert message not match");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Alert did not appear within the specified timeout.");
            e.printStackTrace();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException: No alert present.");
            e.printStackTrace();
        }
    }

    @Test(priority = 7, description = "Login when wrong password")
    public void LoginWrongPassword() {
        signinPage.clickTimezone();
        signinPage.setEmail("000000");
        signinPage.setPassword("asdb");
        signinPage.clickLoginButton();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "Username or password is incorrect", "Allert message not match");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Alert did not appear within the specified timeout.");
            e.printStackTrace();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException: No alert present.");
            e.printStackTrace();
        }
    }

    @Test(priority = 9, description = "Login successful")
    public void LoginSuccessful() {
        signinPage.clickTimezone();
        signinPage.setEmail("000000");
        signinPage.setPassword("123456");
        signinPage.clickLoginButton();
        header_content = WebUI.getElementText(By.xpath(Locator.header_title_content));
        signinPage.verifyLoginSuccess();
    }
}
