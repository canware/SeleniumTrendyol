package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class LoginPage extends BasePage {

    String emailName = "email";
    String passwordName = "password";
    String loginButtonId = "loginSubmit";
    String loginAfterPageTitle = "Erkek Giyim, Erkek Kıyafetleri, Erkek Modası | Trendyol";

    public LoginPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}

    public void login(String [] LoginInfo) //Login işlemini gerçekleştirir.
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(emailName)));
        SetText(By.id(emailName),LoginInfo[0]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passwordName)));
        SetText(By.id(passwordName),LoginInfo[1]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginButtonId)));
        Click(By.id(loginButtonId));
    }
    public void LoginSuccessAssert() //Başarıyla login olduktan sonra geçilen sayfanın title'ını kontrol eder
    {
        wait.until(ExpectedConditions.titleIs(loginAfterPageTitle));
        AssertJUnit.assertEquals(driver.getTitle(),loginAfterPageTitle);
        waitSeconds(2);
    }
}
