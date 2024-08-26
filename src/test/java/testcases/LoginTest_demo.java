package testcases;

import common.BaseSetup;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.ReportListener;
import locators.Locator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SigninPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

@Listeners(ReportListener.class)
@Epic("Regression Test login")
@Feature("Feat Login")
public class LoginTest_demo extends BaseSetup {
    SigninPage signinPage;
    public static int rowIndexToRun = 0;

    public LoginTest_demo() {
        rowIndexToRun += 1;
    }

    @Test(priority = 1)
    public void loginEmptyUS() {
        // Đường dẫn tới file Excel
        String excelFilePath = "file/testcase_login.xlsx";
        signinPage = new SigninPage(driver);
        signinPage.clickTimezone();
        try {
            // Đọc file Excel
            FileInputStream fis = new FileInputStream(new File(excelFilePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
//            LoginTest_demo obj1 = new LoginTest_demo();
            // Lặp qua từng hàng của sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Bỏ qua hàng tiêu đề
                    continue;
                }
                // Chỉ thực hiện với hàng có chỉ số bạn muốn
                if (row.getRowNum() == rowIndexToRun) {
                    System.out.println("Row Index to run: " + LoginTest_demo.rowIndexToRun);
                    Cell emailCell = row.getCell(0);
                    Cell passwordCell = row.getCell(1);
                    Cell resultCell = row.createCell(2); // Tạo cell cho kết quả nếu chưa tồn tại

                    String email = emailCell.getStringCellValue();
                    String password = passwordCell.getStringCellValue();

                    signinPage.setEmail(email);
                    signinPage.setPassword(password);
                    signinPage.clickLoginButton();

                    try {
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                        //waiting for element exists in the DOM and is displayed (not hidden by css or other elements)
                        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
                        String alertText = alert.getText();
                        try {
                            Assert.assertEquals(alertText, "Username or password is incorrect", "Allert message not match");
                            resultCell.setCellValue("PASS");
                        } catch (AssertionError e) {
                            resultCell.setCellValue("FAIL: " + e.getMessage());
                        }
                    } catch (TimeoutException e) {
                        resultCell.setCellValue("FAIL: TimeoutException");
                        e.printStackTrace();
                    } catch (NoAlertPresentException e) {
                        resultCell.setCellValue("FAIL: NoAlertPresentException");
                        e.printStackTrace();
                    }
                }
            }
            // Đóng workbook đầu vào
            fis.close();
            // Ghi kết quả vào file Excel
            FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
            workbook.write(fos);
            fos.close();
            // Đóng workbook
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void loginEmptyUser() {
        // Đường dẫn tới file Excel
        String excelFilePath = "file/testcase_login.xlsx";
        // Chỉ định hàng bạn muốn chạy (ví dụ: hàng 2, lưu ý hàng đầu tiên là tiêu đề)

        signinPage.clickTimezone();
        try {
            // Đọc file Excel
            FileInputStream fis = new FileInputStream(new File(excelFilePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            LoginTest_demo obj2 = new LoginTest_demo();
            // Lặp qua từng hàng của sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Bỏ qua hàng tiêu đề
                    continue;
                }
                // Chỉ thực hiện với hàng có chỉ số bạn muốn
                if (row.getRowNum() == rowIndexToRun) {
                    System.out.println("Row Index to run 2: " + LoginTest_demo.rowIndexToRun);
                    Cell emailCell = row.getCell(0);
                    Cell passwordCell = row.getCell(1);
                    Cell resultCell = row.createCell(2); // Tạo cell cho kết quả nếu chưa tồn tại

                    String email = emailCell.getStringCellValue();
                    String password = passwordCell.getStringCellValue();

                    signinPage.setEmail(email);
                    signinPage.setPassword(password);
                    signinPage.clickLoginButton();

                    try {
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                        //waiting for element exists in the DOM and is displayed (not hidden by css or other elements)
                        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator.alertMessage_loginfail)));
                        String alertText = alert.getText();
                        try {
                            Assert.assertEquals(alertText, "Username or password is incorrect", "Allert message not match");
                            resultCell.setCellValue("PASS");
                        } catch (AssertionError e) {
                            resultCell.setCellValue("FAIL: " + e.getMessage());
                        }
                    } catch (TimeoutException e) {
                        resultCell.setCellValue("FAIL: TimeoutException");
                        e.printStackTrace();
                    } catch (NoAlertPresentException e) {
                        resultCell.setCellValue("FAIL: NoAlertPresentException");
                        e.printStackTrace();
                    }
                }
            }
            // Đóng workbook đầu vào
            fis.close();
            // Ghi kết quả vào file Excel
            FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
            workbook.write(fos);
            fos.close();
            // Đóng workbook
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
