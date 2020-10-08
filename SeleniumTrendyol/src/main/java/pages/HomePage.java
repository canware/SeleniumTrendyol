package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import java.util.List;

public class HomePage extends BasePage {

    String url = "https://www.trendyol.com/";
    String title = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";
    String signIn = "accountBtn";

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void NavigateUrl() {
        driver.get(url);
    }

    public void MainPageAssertion() { //Ana ekranda olduğunu kontrol eder
        wait.until(ExpectedConditions.titleIs(title));
        AssertJUnit.assertEquals(driver.getTitle(), title);
    }

    public void ClickSignInButton() { //Giriş yap butonuna tıklar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(signIn)));
        Click(By.id(signIn));
    }

    //ÇIKAN POPUPLARI KAPATIR
    public void CloseAllPopups() throws InterruptedException {
        CloseDiscountPopup();
        CloseAdPopups();
    }
    public void CloseAdPopups() throws InterruptedException {
        try {
            By selector = By.cssSelector(".fancybox-wrap a[title=\"Close\"]");
            WebElement adModalEl = driver.findElement(selector);
            adModalEl.click();
            Thread.sleep(3000);
        } catch (NoSuchElementException | ElementNotInteractableException ignored) {
        }
    }
    public void CloseDiscountPopup() throws InterruptedException {
        try {
            By selector = By.cssSelector(".modal-close");
            WebElement adModalEl = driver.findElement(selector);
            adModalEl.click();
            Thread.sleep(3000);
        } catch (NoSuchElementException | ElementNotInteractableException ignored) {
        }
    }

}