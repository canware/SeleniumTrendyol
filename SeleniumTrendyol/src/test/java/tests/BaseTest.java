package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;


public class BaseTest{
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeSuite
    public void setUp()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,20);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @AfterSuite
    public void tearDown()
    {
        driver.quit();
    }
}
