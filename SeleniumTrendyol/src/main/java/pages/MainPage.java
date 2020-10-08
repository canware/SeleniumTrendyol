package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class MainPage extends BasePage {

    String searchBoxId = ".search-box-container input.search-box";
    String searchId = ".search-box-container .search-icon";
    String searchData = "Bilgisayar";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

     public void Search() throws InterruptedException { //Search işlemini gerçekleştirir.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchBoxId)));
        SetText(By.cssSelector(searchBoxId),searchData);
        waitSeconds(1);
        HomePage homePage = new HomePage(driver,wait);
        homePage.CloseAllPopups();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchId)));
        Click(By.cssSelector(searchId));
    }
}
