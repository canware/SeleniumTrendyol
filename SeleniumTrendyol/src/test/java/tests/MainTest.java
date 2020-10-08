package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.MainPage;
import pages.CartPage;
import java.util.Random;

public class MainTest extends BaseTest {
    @Test(priority = 1)
    public void SearchTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.Search();
    }
    @Test(priority = 2)
    public void ProductSelection(){

        Random r=new Random();
        int randomOrder = r.nextInt(27);

        BasePage basePage = new BasePage(driver,wait);
        basePage.SelectProduct(randomOrder);
    }
    @Test(priority = 3)
    public void GetProductNameAndPrice(){
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.getProductName("ProductNameAndPrice.txt");
        mainPage.getProductPrice("ProductNameAndPrice.txt");
    }
}
