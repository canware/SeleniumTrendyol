package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.BasePage;

public class LoginTest extends BaseTest {

    @Test
    public void loginPageTest() throws Exception
    {
        BasePage basePage = new BasePage(driver,wait);
        basePage.Log();
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.login(basePage.txtReader("Login.csv"));
        loginPage.LoginSuccessAssert();
        HomePage homePage = new HomePage(driver,wait);
        homePage.CloseAllPopups();
    }
}