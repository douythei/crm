package common;

import constants.ConfigData;
import listeners.ReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.SigninPage;

import java.io.File;
import java.time.Duration;

@Listeners(ReportListener.class)
public class SetupSignInSuccess extends ConfigData{

    public static WebDriver driver;
    SigninPage signinPage;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void deteleReport(){
        File directory = new File("allure-results");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println("Log file: " + file.getName());
                        file.delete();
                    }
                }
            }
        } else {
            System.out.println("Directory 'allure-results' does not exist or is not a directory.");
        }
    }


    @BeforeMethod
    @Parameters({"browser"})
    public void createBrowser(@Optional("chrome") String browserName) {
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        }
        if (browserName.equals("edge")) {
            driver = new EdgeDriver();
        }
        if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        signinPage = new SigninPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //waite to element appears
        driver.get(ConfigData.URL);
        signinPage.clickTimezone();
        signinPage.setEmail(ConfigData.EMAIL);
        signinPage.setPassword(ConfigData.PASSWORD);
        signinPage.clickLoginButton();
    }

    @AfterMethod
    public void closeBrowser(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
