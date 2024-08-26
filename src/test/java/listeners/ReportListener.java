package listeners;

import common.BaseSetup;
import common.SetupSignInSuccess;
import constants.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
//import reports.ExtentTestManager;
import io.qameta.allure.Attachment;

import java.io.File;

public class ReportListener implements ITestListener {

    WebDriver driver;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    //Text attachments for Allure
//    @Attachment(value = "Page screenshot", type = "image/png")
//    public byte[] saveScreenshotPNG(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }

    @Override
    public void onStart(ITestContext iTestContext) {
//        driver = BaseSetup.getDriver();
        try {
            driver = BaseSetup.getDriver();
        } catch (Exception e) {
            try {
                driver = SetupSignInSuccess.getDriver();
            } catch (Exception ex) {
                throw ex;
            }
        }

        Log.info("Start testing " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
//        //Gọi hàm startRecord video trong CaptureHelpers class
//        try {
//            CaptureHelpers.startRecord(iTestContext.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("End testing " + iTestContext.getName());
        //Kết thúc và thực thi Extents Report
//        getExtentReports().flush();
        //Gọi hàm stopRecord video trong CaptureHelpers class
//        CaptureHelpers.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is starting...");
//        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is passed.");
        //ExtentReports log operation for passed tests.
//        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
//        driver = BaseSetup.getDriver();
        try {
            driver = BaseSetup.getDriver();
        } catch (Exception e) {
            driver = SetupSignInSuccess.getDriver();
        }

        Log.error(getTestName(iTestResult) + " test is failed.");

//        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
//
//        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
//        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");

        //Allure Screenshot custom
        Log.error("Screenshot captured for test case: " + getTestName(iTestResult));
//        saveScreenshotPNG(driver);
        //Save a log on Allure report.
        saveTextLog(getTestName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestName(iTestResult) + " test is skipped.");
//        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
//        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }

    public void clearDirectory() {
        File directory = new File("allure-results");
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }else {
            System.out.println("Fail to delete");
        }
    }
}
