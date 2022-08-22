import com.thoughtworks.gauge.Step;
import static org.junit.jupiter.api.Assertions.*;

public class AddBasket extends BaseTest {

    HomePage homePage;
    ProductListPage productListPage;
    ProductDetail productDetail;
    CheckOutPage checkOutPage;

    @Step("Kullanıcı Hepsiburada.com sitesini ziyaret eder")
    public void shouldOpenHomePage() {
        homePage = new HomePage(driver);
        assertEquals("https://www.hepsiburada.com/", driver.getCurrentUrl());
        assertTrue(homePage.checkLogoVisibility());
    }

    @Step("Kullanıcı ürün <productName> için arama yapar ve ürünler listelenir")
    public void shouldSearchProduct(String productName) {
        productListPage = new ProductListPage(driver);
        homePage.searchProduct(productName);
        assertEquals(productName, productListPage.getProductHeaderText());
        assertEquals("https://www.hepsiburada.com/ara?q=" + productName.replace(' ','+'), driver.getCurrentUrl());
        assertFalse(productListPage.checkProductListIsEmpty());
    }

    @Step("Kullanıcı ürün <productName> listesinden bir ürün seçer ve detay ekranı açılır")
    public void shouldSelectProduct(String productName) {
        productDetail = new ProductDetail(driver);
        productListPage.selectAProduct(productName);
        assertTrue(productDetail.checkProductName(productName, 1));
        //assertTrue(productDetail.checkProductImgDetail(productName));
    }

    @Step("Seçilen ürün sepete eklenir")
    public void shouldAddProductToBasket() {
        productDetail.addProductToCart();
        assertTrue(productDetail.checkVisibilityProductOnBasketHeader());
        productDetail.clickContinueShoppingBut();
    }

    @Step("Ürün listesinden aynı ürün <productName> tekrar seçilerek detay sayfası açılır")
    public void shouldOpenDetailPageAgain(String productName) {
        productListPage.selectAProduct(productName);
        assertTrue(productDetail.checkProductName(productName,2));
    }

    @Step("Farklı bir satıcıdan aynı ürün sepete eklenir")
    public void shouldAddToCartFromOtherSeller() {
        //assertTrue(productDetail.checkOtherSellersExist());
        productDetail.addProductFromOtherSeller();
        assertTrue(productDetail.checkVisibilityProductOnBasketHeader());
    }

    @Step("Sepetim ekranında seçilen ürünlerin <productName> kontrolleri gerçekleştirilir")
    public void shouldCheckProductsOnCheckoutPage(String productName) {
        checkOutPage = new CheckOutPage(driver);
        productDetail.clickGotoBasketBut();
        assertEquals("https://checkout.hepsiburada.com/", driver.getCurrentUrl());

        assertTrue(checkOutPage.checkVisibilityOfCartHeader());
        assertEquals("2", checkOutPage.getQuantityText());
        assertEquals(2, checkOutPage.getQuantityInputCount());
        assertTrue(checkOutPage.checkProductNames(productName));
        assertFalse(checkOutPage.checkSellersMatch());
    }
}
