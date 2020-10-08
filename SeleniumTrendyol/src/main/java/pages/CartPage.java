package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class CartPage extends BasePage {

    String cartPageTitle = "Sepetim - Trendyol";

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void CartPageAssertion() //Sepet ekranında olduğunu kontrol eder
    {
        wait.until(ExpectedConditions.titleIs(cartPageTitle));
        AssertJUnit.assertEquals(driver.getTitle(),cartPageTitle);
    }
    public void productInformationCheck(String[] productInformation) //Sepetteki ürünün isminin ve fiyatının doğru olduğunu kontrol eder
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='pb-item']")));
        //Ürün Adı Kontrolü
        if(driver.findElement(By.xpath("//*[@class='pb-item']")).getText().equals(productInformation[0]))
            log.info("Ürün adı "+productInformation[0]+" doğru gelmiştir.");
        else
            log.error("Eklenen ürün adı: "+productInformation[0]+" ile Sepetteki ürün adı: "+ driver.findElement(By.xpath("//*[@class='pb-item']")).getText() +" eşit değildir.");

        //Ürün Fiyat Kontrolü
        if(driver.findElement(By.cssSelector("#basketAside .shoppingReview .total-price")).getText().equals(productPrice + " TL"))
            log.info("Ürünün Fiyatı "+productInformation[1]+" doğru gelmiştir.");
        else
            log.error("Eklenen ürünün fiyatı: "+productInformation[1]+" ile Sepetteki ürünün fiyatı: "+ driver.findElement(By.cssSelector("#basketAside .shoppingReview .total-price")).getText() +" eşit değildir.");
    }
    public static String productPriceSplit(String price) { //Fiyat Bilgisinin yanında gelen para biriminin silinmesini sağlar (TL)
        price = price.split(" TL")[0];
        return price;
    }
    public void Increase() //Sepetteki ürünün adedini arttırıp kontrolünü sağlar
    {
        String productQuantity;
        try {
            driver.findElement(By.cssSelector("#partial-basket .ty-numeric-counter-button:last-of-type")).click();
            waitSeconds(4);
            productQuantity = driver.findElement(By.cssSelector("#partial-basket input.counter-content")).getAttribute("value");
        }
        catch(Exception e) {
            WebElement selectElement = driver.findElement(By.cssSelector("#basketContent select.basketItemQuantity"));
            Select selectObject = new Select(selectElement);
            selectObject.selectByVisibleText("2");
            waitSeconds(4);
            productQuantity= selectObject.getFirstSelectedOption().getAttribute("value");
        }
        Assert.assertEquals(productQuantity, "2");

        if(productQuantity.equals(2))
            log.info("Ürünün adedi doğru gelmiştir.");
        else
            log.error("Ürünün adedi yanlış gelmiştir.");
    }
    public void EmptyCart() //Sepetteki ürünü siler ve Sepetin boş olduğunu kontrol eder
    {
        waitSeconds(4);
        driver.findElement(By.cssSelector("#partial-basket div.pb-basket-item div.pb-basket-item-actions > button")).click();
        waitSeconds(4);
        driver.findElement(By.cssSelector("#ngdialog1 > div.ngdialog-content button.btn-remove")).click();
        waitSeconds(4);

        try {
            String emptyCartText = driver.findElement(By.cssSelector("#basketNoProductPage span")).getText();
            Assert.assertNotNull(emptyCartText);
        } catch (NoSuchElementException e) {
            String cartTotal = driver.findElement(By.cssSelector("div.checkoutContainer div.total > span.price")).getText();
            Assert.assertEquals("0,00 TL", cartTotal);
        }
    }
}
