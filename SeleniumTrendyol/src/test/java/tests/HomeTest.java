package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

public class HomeTest extends BaseTest {

    @Test
    public void homePageTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver,wait);
        homePage.NavigateUrl();
        homePage.MainPageAssertion();
        homePage.CloseAllPopups();
        homePage.ClickSignInButton();
    }
}
