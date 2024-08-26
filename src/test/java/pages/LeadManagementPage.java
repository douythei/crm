package pages;

import common.SetupSignInSuccess;
import io.qameta.allure.Step;
import common.WebUI;
import listeners.ReportListener;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeadManagementPage extends SetupSignInSuccess {
    private static WebDriver driver;
    private WebDriverWait wait;
    public LeadManagementPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        new WebUI(driver);
    }

    public void clickCreateLead(){
        WebUI.clickElement(By.xpath(Locator.btn_create_lead));
        ReportListener.saveTextLog("Click button Create");
    }

    public void clickCancel(){
        WebUI.clickElement(By.xpath(Locator.btn_popup_cancel));
        ReportListener.saveTextLog("Click button Cancel");
    }

    public void clickCreatePopup(){
        WebUI.clickElement(By.xpath(Locator.btn_popup_create_lead));
        ReportListener.saveTextLog("Click button Create");
    }

    @Step("Input lead name: {0}")
    public void inputLeadName(String leadName){
//        WebUI.clearData(By.xpath(Locator.input_lead_name));
        WebUI.setText(By.xpath(Locator.input_lead_name), leadName);
        ReportListener.saveTextLog("Input lead name: "+ leadName);
    }

    public void inputClientName(String clientName){
        WebUI.setText(By.xpath(Locator.input_client_name), clientName);
        ReportListener.saveTextLog("Input client name: "+ clientName);
    }

    public void inputClientCode(String clientCode){
        WebUI.setText(By.xpath(Locator.input_client_code), clientCode);
        ReportListener.saveTextLog("Input client name: "+ clientCode);
    }

    public void inputWebsite(String website){
        WebUI.setText(By.xpath(Locator.input_website), website);
        ReportListener.saveTextLog("Input client name: "+ website);
    }

    public void inputContactName(String contactName){
        WebUI.setText(By.xpath(Locator.input_contact_name), contactName);
        ReportListener.saveTextLog("Input contact name: "+ contactName);
    }

    public void inputEmail(String email){
        WebUI.setText(By.xpath(Locator.input_email), email);
        ReportListener.saveTextLog("Input email: "+ email);
    }

    public void clickStatusProspect (){
        WebUI.clickElement(By.xpath(Locator.get_status_prospect));
        ReportListener.saveTextLog("Click dropdown status: Prospect");
    }

    public void clickStatusContact (){
        WebUI.clickElement(By.xpath(Locator.get_status_contacted));
        ReportListener.saveTextLog("Click dropdown status: Contact");
    }

    public void clickStatusEstimate () {
        WebUI.clickElement(By.xpath(Locator.get_status_estimate));
        ReportListener.saveTextLog("Click dropdown status: Estimate");
    }

    public void clickStatusFollow (){
        WebUI.clickElement(By.xpath(Locator.get_status_follow));
        ReportListener.saveTextLog("Click dropdown status: Follow up");
    }

    public void clickStatusReceived (){
        WebUI.clickElement(By.xpath(Locator.get_status_received));
        ReportListener.saveTextLog("Click dropdown status: Project received");
    }

    public void selectStatusProspect(){
        WebUI.clickElement(By.xpath(Locator.select_status_prospect));
        ReportListener.saveTextLog("Select status: Prospect");
    }

    public void selectStatusContact(){
        WebUI.clickElement(By.xpath(Locator.select_status_contacted));
        ReportListener.saveTextLog("Select status: Contacted");
    }

    public void selectStatusEstimate(){
        WebUI.clickElement(By.xpath(Locator.select_status_estimate));
        ReportListener.saveTextLog("Select status: Estimate");
    }

    public void selectStatusFollow(){
        WebUI.clickElement(By.xpath(Locator.select_status_follow));
        ReportListener.saveTextLog("Select status: Follow up");
    }

    public void selectStatusReceived(){
        WebUI.clickElement(By.xpath(Locator.select_status_received));
        ReportListener.saveTextLog("Select status: Project received");
    }

    public void clickHereCreateNewClient(){
        WebUI.clickElement(By.xpath(Locator.click_here_create_new_client));
        ReportListener.saveTextLog("Click here of Create new client");
    }

    public void clickHereCreateNewContact(){
        WebUI.clickElement(By.xpath(Locator.click_here_create_new_contact));
        ReportListener.saveTextLog("Click here of Create new contact");
    }

    public void selectClient(String client){
        Select dropdown = new Select(driver.findElement(By.xpath(Locator.dropdown_client)));
        dropdown.selectByValue(client);
        ReportListener.saveTextLog("Select client: "+ client);
    }

    public void selectContact(String contact){
        WebElement dropdown_contact = driver.findElement(By.xpath("//span[@title='Contacted']"));
        Select select_contact = new Select(dropdown_contact);
        select_contact.selectByValue(contact);
        ReportListener.saveTextLog("Select contact: "+ contact);
    }

}
