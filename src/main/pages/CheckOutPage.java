import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckOutPage extends BasePage {
    public CheckOutPage(WebDriver driver) { super(driver); }

    @FindBy(xpath = "//h1[text() = 'Sepetim']")
    private WebElement cartHeader;

    @FindBy(id = "basket-item-count")
    private WebElement quantityTxt;

    @FindBy(xpath = "//input[@name = 'quantity' and @value = '1']")
    private List<WebElement> quantityInputs;

    @FindBy(xpath = "//span[text() = 'Satıcı: ']/following-sibling::span/a")
    private List<WebElement> sellerTexts;

    public boolean checkVisibilityOfCartHeader() {
        return waitElementToBeVisible(driver, this.cartHeader).isDisplayed();
    }

    public String getQuantityText() {
        return this.quantityTxt.getText();
    }

    public int getQuantityInputCount() {
        return this.quantityInputs.size();
    }

    public boolean checkProductNames(String productName) {
        String[] textArray = new String[2];
        List<WebElement> productLinks = driver.findElements(By.xpath("//a[text() = '"+ productName +"']"));

        for (int i = 0; i < productLinks.size(); i++) {
            String[] texts = productLinks.get(i).getAttribute("href").split("\\?");
            textArray[i] = texts[0];
        }
        return textArray[0].equals(textArray[1]);
    }

    public boolean checkSellersMatch() {
        return this.sellerTexts.get(0).getText().equals(this.sellerTexts.get(1).getText());
    }
}
