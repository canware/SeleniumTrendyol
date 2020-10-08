package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import java.io.IOException;

public class CartTest extends BaseTest {
    @Test(priority = 4)
    public void addProductToCartAndCheck()
    {
        BasePage basePage = new BasePage(driver,wait);
        basePage.AddProductToCartAndCheck();
    }
    @Test(priority = 5)
    public void OutputCheck() throws IOException {
        BasePage basePage = new BasePage(driver,wait);
        CartPage cartPage = new CartPage(driver,wait);
        cartPage.CartPageAssertion();
        cartPage.productInformationCheck(basePage.txtReader("ProductNameAndPrice.txt"));
    }
    @Test(priority = 6)
    public void ChanceIncrease()
    {
        CartPage cartPage = new CartPage(driver,wait);
        cartPage.Increase();
    }
    @Test(priority = 7)
    public void CleanCart()
    {
        CartPage cartPage = new CartPage(driver,wait);
        cartPage.EmptyCart();
    }
}

