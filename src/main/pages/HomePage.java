import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) { super(driver); }

    @FindBy(xpath = "//a[@title = 'Hepsiburada' and @href = '/']")
    private WebElement logoLink;

    @FindBy(xpath = "//input[@placeholder = 'Ürün, kategori veya marka ara']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[text() = 'ARA']")
    private WebElement searchBut;


    public boolean checkLogoVisibility() {
        return waitElementToBeVisible(driver, this.logoLink).isDisplayed();
    }

    public void searchProduct(String productName) {
        this.searchInput.sendKeys(productName);
        this.searchBut.click();
    }
}
