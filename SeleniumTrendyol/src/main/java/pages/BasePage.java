package pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.io.*;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String productId;
    public static String productPrice = null;
    static Logger log = Logger.getLogger(BasePage.class);

    public String getProductId() {
        return productId;
    }

    public BasePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public void Log() {
        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\resources\\log4j.properties");
    }
    public void waitSeconds(int sec){
        try {Thread.sleep(sec * 1000);}
        catch (InterruptedException e) {e.printStackTrace();}
    }
    public void Click(By element)
    {
        driver.findElement(element).click();
    }
    public void SetText(By element,String text)
    {
        driver.findElement(element).sendKeys(text);
    }
    public void SelectProduct(int productNum) {
        //Ürün Seçmek için kullanılır productNumarası ürünün sırasıdır Örnek 1 veya 5 gibi
        WebElement productElement = driver.findElement(By.cssSelector("#search-app div.srch-prdcts-cntnr div.p-card-wrppr:nth-child(" + productNum + ")"));
        String preSelectionPrice = productElement.findElement(By.cssSelector(".prc-cntnr .prc-box-sllng")).getText();
        productElement.findElement(By.cssSelector(".p-card-chldrn-cntnr a")).click();
        String preCartPrice = driver.findElement(By.cssSelector("#product-detail-app div.pr-cn div.pr-cn-in span.prc-slg")).getText();
        Assert.assertEquals(preSelectionPrice, preCartPrice);
    }

    public void AddProductToCartAndCheck() {
        //Ürünü Sepete ekler ve Sepete gider
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-detail-app button.add-to-bs")));
        driver.findElement(By.cssSelector("#product-detail-app button.add-to-bs")).click();
        waitSeconds(1);
        driver.findElement(By.cssSelector("#myBasketListItem a")).click();
    }
    public static void txtWriter(String txtName, String text, Boolean append) { //txt dosyasına veri yazma
        try{
            FileWriter writer = new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\" + txtName, append);
            writer.write(text);
            writer.write(";");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @NotNull
    public static String[] txtReader(String txtName) throws IOException { //txt dosyasından veri okuma
            String line = "";
            String cvsSplitBy = ";";
            FileReader read = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\" + txtName);
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\" + txtName));
            line = br.readLine();
            String[] txtList = line.split(cvsSplitBy);
            return  txtList;
    }
    public void getProductName(String txtName) { //Ürünün sayfasından adını çeker
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='pr-nm']")));

        WebElement name = driver.findElement(By.xpath("//*[@class='pr-in-br']/a"));
        WebElement title = driver.findElement(By.xpath("//*[@class='pr-nm']"));
        String innerText= name.getText() + " " + title.getText();
        BasePage.txtWriter(txtName,innerText,false);
    }
    public void getProductPrice(String txtName) { //Ürünün sayfasından fiyatını çeker
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-detail-app div.pr-cn div.pr-cn-in span.prc-slg")));
        WebElement price = driver.findElement(By.cssSelector("#product-detail-app div.pr-cn div.pr-cn-in span.prc-slg"));
        String innerText= price.getText();
        productPrice = CartPage.productPriceSplit(innerText);
        BasePage.txtWriter(txtName,innerText,true);
    }
}
