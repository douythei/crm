package testcases;

import common.BaseSetup;
import common.ExcelUtils;
import locators.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SigninPage;

import java.io.IOException;
import java.time.Duration;

public class LoginCSV extends BaseSetup {
    SigninPage signinPage;
    private String excelFilePath = "file/testcase_login.xlsx";
    private String sheetName = "Sheet1";
    public static int rowIndexToRun = 0;

    public LoginCSV() {
        rowIndexToRun += 1;
    }

    @Test (priority = 1)
    public void testLogin() throws IOException {
        ExcelUtils.setExcelFile(excelFilePath, sheetName);
        // Kiểm tra nếu hàng chỉ định nằm ngoài phạm vi
        int rowCount = ExcelUtils.getRowCount();
        if (rowIndexToRun >= rowCount) {
            System.out.println("Row index out of bounds");
            return;
        }

        // Lấy dữ liệu từ hàng chỉ định
        String email = ExcelUtils.getCellData(rowIndexToRun, 0);
        String password = ExcelUtils.getCellData(rowIndexToRun, 1);
        System.out.println("Row to run: "+rowIndexToRun);

        signinPage = new SigninPage(driver);
        signinPage.clickTimezone();
        signinPage.setEmail(email);
        signinPage.setPassword(password);
        signinPage.clickLoginButton();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            String alertText = alert.getText();

            try {
                Assert.assertEquals(alertText, "Username or password is incorrect", "Alert message not match");
                ExcelUtils.setCellData(excelFilePath, "PASS", rowIndexToRun, 2);
            } catch (AssertionError e) {
                ExcelUtils.setCellData(excelFilePath, "FAIL: " + e.getMessage(), rowIndexToRun, 2);
            }
        } catch (TimeoutException e) {
            ExcelUtils.setCellData(excelFilePath, "FAIL: TimeoutException", rowIndexToRun, 2);
            e.printStackTrace();
        } catch (NoAlertPresentException e) {
            ExcelUtils.setCellData(excelFilePath, "FAIL: NoAlertPresentException", rowIndexToRun, 2);
            e.printStackTrace();
        }

        ExcelUtils.closeWorkbook();
    }

    @Test (priority = 2)
    public void testLogin1() throws IOException {
        ExcelUtils.setExcelFile(excelFilePath, sheetName);
        LoginCSV obj2 = new LoginCSV();

        // Kiểm tra nếu hàng chỉ định nằm ngoài phạm vi
        int rowCount = ExcelUtils.getRowCount();
        if (rowIndexToRun >= rowCount) {
            System.out.println("Row index out of bounds");
            return;
        }

        // Lấy dữ liệu từ hàng chỉ định
        String email = ExcelUtils.getCellData(rowIndexToRun, 0);
        String password = ExcelUtils.getCellData(rowIndexToRun, 1);
        System.out.println("Row to run: "+rowIndexToRun);

        signinPage = new SigninPage(driver);
        signinPage.clickTimezone();
        signinPage.setEmail(email);
        signinPage.setPassword(password);
        signinPage.clickLoginButton();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
            String alertText = alert.getText();

            try {
                Assert.assertEquals(alertText, "Username or password is incorrect", "Alert message not match");
                ExcelUtils.setCellData(excelFilePath, "PASS", rowIndexToRun, 2);
            } catch (AssertionError e) {
                ExcelUtils.setCellData(excelFilePath, "FAIL: " + e.getMessage(), rowIndexToRun, 2);
            }
        } catch (TimeoutException e) {
            ExcelUtils.setCellData(excelFilePath, "FAIL: TimeoutException", rowIndexToRun, 2);
            e.printStackTrace();
        } catch (NoAlertPresentException e) {
            ExcelUtils.setCellData(excelFilePath, "FAIL: NoAlertPresentException", rowIndexToRun, 2);
            e.printStackTrace();
        }

        ExcelUtils.closeWorkbook();
    }
}
