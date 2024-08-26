package pages;

import common.BaseSetup;
import io.qameta.allure.Step;
import common.WebUI;
import listeners.ReportListener;
import locators.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SigninPage extends BaseSetup {

    private WebDriver driver;
    private WebDriverWait wait;
    private String header_content;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        new WebUI(driver); //bắt buộc
    }

    public void clickTimezone(){
        WebUI.clickElement(By.xpath(Locator.buttonTimezone));
        String text_timezone_VN = WebUI.getElementText(By.xpath(Locator.textTimezoneVN));
        ReportListener.saveTextLog("Click timezone: " + text_timezone_VN);
    }

    public static void clickChangeTimezone() throws Exception{
        WebUI.clickElementsleep(By.xpath(Locator.btn_changeTimezone));
        String text_change_timezone = WebUI.getElementText(By.xpath(Locator.btn_changeTimezone));
        ReportListener.saveTextLog("Click timezone: " + text_change_timezone);
    }

    public static void clickTimezoneJP() throws Exception{
        WebUI.clickElementsleep(By.xpath(Locator.btn_timezoneJP));
        String text_timezone_JP = WebUI.getElementText(By.xpath(Locator.btn_changeTimezone));
        ReportListener.saveTextLog("Click timezone: " + text_timezone_JP);
    }

    @Step("Nhập email: {0}")
    public void setEmail(String email) {
        WebUI.clearData(By.xpath(Locator.inputEmail));
        WebUI.setText(By.xpath(Locator.inputEmail), email);
        ReportListener.saveTextLog("Nhập email: " + email);
    }

    @Step("Nhập password: {0}")
    public void setPassword(String password) {
        WebUI.clearData(By.xpath(Locator.inputPassword));
        WebUI.setText(By.xpath(Locator.inputPassword), password);
        ReportListener.saveTextLog("Nhập password: " + password);
    }

    public void clickLoginButton() {
        WebUI.clickElement(By.xpath(Locator.buttonLogin));
        ReportListener.saveTextLog("Click button Login");
    }

    public void verifyLoginSuccess() {
        header_content = WebUI.getElementText(By.xpath(Locator.header_title_content));
        Assert.assertEquals(header_content,"Lead Management", "Login fail");
        ReportListener.saveTextLog("Login successfully!");
    }
}
