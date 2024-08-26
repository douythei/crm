package testcases;

import common.SetupSignInSuccess;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import common.WebUI;
import listeners.ReportListener;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LeadManagementPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Listeners(ReportListener.class)
@Epic("Regression Test Create Lead Management")
@Feature("Feat Lead Management Page")
public class CreateManagementLeadTest extends SetupSignInSuccess {
    private LeadManagementPage leadManagementPage;
    private String title_create_lead, msg_error_empty, txtProspect, txtContacted, txtEstimated, txtFollow, txtReceived, txtMsgReceived, alert_create_success;

    @Test(priority = 1, description = "Check popup Create Lead Management")
    public void CreateLead() {
        leadManagementPage = new LeadManagementPage(driver);
        leadManagementPage.clickCreateLead();
        title_create_lead = WebUI.getElementText(By.xpath(Locator.title_create_lead));
        Assert.assertEquals(title_create_lead, "Create Lead", "Note popup Create Lead");
    }

    @Test(priority = 2, description = "Create lead when empty lead name")
    public void CreateEmptyLead() {
        leadManagementPage.clickCreateLead();
        leadManagementPage.inputLeadName("");
        leadManagementPage.clickCreatePopup();
        msg_error_empty = WebUI.getElementText(By.xpath(Locator.msg_error_empty_leadname));
        Assert.assertEquals(msg_error_empty, "This field is required!", "Message error is not correct");
    }

    @Test(priority = 3, description = "Select status apply correct Status field")
    public void CheckOptionStatus() {
        leadManagementPage.clickCreateLead();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        title_create_lead = WebUI.getElementText(By.xpath(Locator.title_create_lead));
        Assert.assertEquals(title_create_lead, "Create Lead", "Not popup create lead");
        //select Prospect
        leadManagementPage.clickStatusProspect();
        leadManagementPage.selectStatusProspect();
        txtProspect = WebUI.getElementText(By.xpath(Locator.get_status_prospect));
        Assert.assertEquals(txtProspect, "Prospect", "Status is not Prospect");
        //select Contacted
        leadManagementPage.clickStatusProspect();
        leadManagementPage.selectStatusContact();
        txtContacted = WebUI.getElementText(By.xpath(Locator.get_status_contacted));
        Assert.assertEquals(txtContacted, "Contacted", "Status is not Contacted");
        //select Estimate
        leadManagementPage.clickStatusContact();
        leadManagementPage.selectStatusEstimate();
        txtEstimated = WebUI.getElementText(By.xpath(Locator.get_status_estimate));
        Assert.assertEquals(txtEstimated, "Estimate", "Status is not Estimated");
        //select Follow up
        leadManagementPage.clickStatusEstimate();
        leadManagementPage.selectStatusFollow();
        txtFollow = WebUI.getElementText(By.xpath(Locator.get_status_follow));
        Assert.assertEquals(txtFollow, "Follow up", "Status is not Follow up");
        //select Project received
        leadManagementPage.clickStatusFollow();
        leadManagementPage.selectStatusReceived();
        txtReceived = WebUI.getElementText(By.xpath(Locator.get_status_received));
        Assert.assertEquals(txtReceived, "Project received", "Status is not Project received");
        txtMsgReceived = WebUI.getElementText(By.xpath(Locator.message_option_received));
        Assert.assertEquals(txtMsgReceived, "If you select this status, the project will be moved to the PIMS Page.\n" +
                "You can not change the status again.", "Not display message warning when change status to Project received");
    }

    @Test(priority = 4, description = "Create new client when empty client name")
    public void CreateNewClientEmptyClientName(){
        leadManagementPage = new LeadManagementPage(driver);
        leadManagementPage.clickCreateLead();
        leadManagementPage.inputLeadName("Onsiter");
        leadManagementPage.clickHereCreateNewClient();
        leadManagementPage.inputClientName("");
        leadManagementPage.clickCreatePopup();
        msg_error_empty = WebUI.getElementText(By.xpath(Locator.msg_error_empty_clientname));
        Assert.assertEquals(msg_error_empty, "This field is required!", "Message error is not correct");
    }

    @Test(priority = 5, description = "Create new client success")
    public void CreateNewClientSuccess() throws InterruptedException {
        leadManagementPage = new LeadManagementPage(driver);
        leadManagementPage.clickCreateLead();
        leadManagementPage.inputLeadName("Onsiter1.7");
        leadManagementPage.clickHereCreateNewClient();
        leadManagementPage.inputClientName("FTEL1");
        leadManagementPage.inputClientCode("IT01");
        leadManagementPage.inputWebsite("https://tms-dev.tgl-cloud.com/");
        leadManagementPage.clickCreatePopup();
        Thread.sleep(1500);
        alert_create_success = WebUI.getElementText(By.xpath(Locator.alert_create_success));
        driver.navigate().refresh();
        Assert.assertEquals(alert_create_success, "Create success\n" +
                "The lead has been created successfully!", "Message error is not correct");
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
            // Xác định phần tử bảng bằng CSS Selector
            WebElement table = driver.findElement(By.xpath("//tbody[contains(@class, 'ant-table-tbody')]"));

            // Tìm tất cả các hàng trong bảng (trừ hàng tiêu đề)
            List<WebElement> rows = table.findElements(By.xpath("//body[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[*]/td[5]/span[1]"));
            System.out.println("Count of rows: " + rows.size());

            List<String> rowTexts = new ArrayList<>();
            // Lặp qua từng hàng và lấy dữ liệu từ cột cụ thể
            for (WebElement row : rows) {
                rowTexts.add(row.getText());
            }
            Assert.assertTrue(rowTexts.contains("FTEL1"), "NotSame");
        }finally{
            System.out.println("Done");;
        }
    }
}

