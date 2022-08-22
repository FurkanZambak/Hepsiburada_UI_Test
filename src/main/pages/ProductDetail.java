import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetail extends BasePage {
    public ProductDetail(WebDriver driver) { super(driver); }

    @FindBy(id = "product-name")
    private WebElement productName;
    @FindBy(id = "addToCart")
    private WebElement addToBasketBut;
    @FindBy(xpath = "//span[contains(text(), 'Ürün sepetinizde')]")
    private WebElement productOnBasketHeader;
    @FindBy(xpath = "//button[text() = 'Alışverişe devam et']")
    private WebElement continueShoppingBut;
    @FindBy(xpath = "//button[text() = 'Sepete git']")
    private WebElement goToBasketBut;
    @FindBy(xpath = "//div[@class = 'marketplace-list']//tr")
    private List<WebElement> otherSellersList;
    @FindBy(xpath = "//a[text() = 'Onarım paketi istemiyorum']")
    private WebElement denyBut;


    public boolean checkProductName(String productName, int tabNo) {
        switchToNewTab(driver, tabNo);
        return waitElementToBeVisible(driver, this.productName).getText().equals(productName);
    }

    public boolean checkProductImgDetail(String productName) {
        return this.productName.getAttribute("title").equals(productName);
    }

    public void addProductToCart() {
        this.addToBasketBut.click();
    }

    public boolean checkVisibilityProductOnBasketHeader() {
        return waitElementToBeVisible(driver, this.productOnBasketHeader).isDisplayed();
    }

    public void clickContinueShoppingBut() {
        this.continueShoppingBut.click();
    }

    public void clickGotoBasketBut() {
        this.goToBasketBut.click();
        //switchToNewTab(driver, 3);
    }

    public boolean checkOtherSellersExist() {
        return this.otherSellersList.isEmpty();
    }

    public void addProductFromOtherSeller() {
        WebElement addBut = this.otherSellersList.get(0).findElement(By.xpath(".//span[text() = 'Sepete Ekle']"));
        scrollIntoView(driver, addBut);
        Click(driver, addBut);
        //waitElementToBeClickable(driver, this.denyBut).click();
    }
}
