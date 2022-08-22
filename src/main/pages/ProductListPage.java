import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BasePage {
    public ProductListPage(WebDriver driver) { super(driver); }

    @FindBy(xpath = "//h1[@class = 'searchResultSummaryBar-bold']")
    private WebElement productHeader;
    @FindBy(xpath = "//h3[@data-test-id = 'product-card-name']")
    private List<WebElement> items;
    @FindBy(xpath = "//div[@data-test-id = 'product-info-button']")
    private WebElement addBasketBut;

    public String getProductHeaderText() {
        return waitElementToBeVisible(driver, this.productHeader, 10).getText();
    }

    public boolean checkProductListIsEmpty() {
        return this.items.isEmpty();
    }

    public void selectAProduct(String productName) {
        for(int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getText().equals(productName)) {
                waitElementToBeClickable(driver, this.items.get(i)).click();
                break;
            }
        }
    }
}
