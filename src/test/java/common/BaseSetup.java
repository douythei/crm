package common;

import constants.ConfigData;
import listeners.ReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.SigninPage;

import java.time.Duration;

@Listeners(ReportListener.class)
public class BaseSetup extends ConfigData{

    public static WebDriver driver;
    SigninPage signinPage;
    ReportListener reportListener;

    public static WebDriver getDriver() {
        return driver;
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
//        signinPage = new SigninPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //waite to element appears
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); //waite to page load
        driver.get(ConfigData.URL);
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
