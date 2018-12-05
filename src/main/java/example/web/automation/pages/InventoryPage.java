package example.web.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends Page {

    @FindBy(id = "contents_wrapper")
    private WebElement root;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemNamesElements;

    @FindBy(className = "add-to-cart-button")
    WebElement addToCartBtn;

    @FindBy(className = "inventory_item_price")
    WebElement itemPriceElement;


    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Find product named {0}")
    public WebElement findItemByName(String name) {
        WebElement item = null;
        for (WebElement inventoryItem : inventoryItems) {
            if (name.equals(inventoryItem.findElement(By.xpath("//*[@class='inventory_item_name']")).getText()))
                item = inventoryItem;
        }
        if (null == item) {
            throw new IllegalStateException("No item with such name: " + name);
        }
        return item;
    }

    @Step("Navigate to product page. Product index is {0}")
    public ProductDetailsPage goToProductDetailsPage(int index) {
        if (!inventoryItems.isEmpty()) {
            if (inventoryItems.size() <= index) {
                throw new IllegalArgumentException("Invalid index was passed");
            }

            inventoryItems.get(index).click();

            return new ProductDetailsPage(webDriver);
        } else throw new IllegalStateException("Inventory is empty");
    }

    @Step("Navigate to {0} product page")
    public ProductDetailsPage goToProductDetailsPage(String productName) {
        if (!inventoryItems.isEmpty()) {
            findItemByName(productName).click();
            return new ProductDetailsPage(webDriver);
        } else throw new IllegalStateException("Inventory is empty");
    }

    @Step("Add product to cart with name {0}")
    public void addProductToCart(String productName) {
        if (!inventoryItems.isEmpty()) {
            findItemByName(productName).findElement(By.xpath("//*[@class='add-to-cart-button']")).click();
        } else throw new IllegalStateException("Inventory is empty");
    }

    @Step("Add product to cart with inder {0}")
    public void addProductToCart(int index) {
        if (!inventoryItems.isEmpty()) {
            if (inventoryItems.size() <= index) {
                throw new IllegalArgumentException("Invalid index was passed");
            }
            inventoryItems.get(index).findElement(By.xpath("//*[@class='add-to-cart-button']")).click();
        } else throw new IllegalStateException("Inventory is empty");
    }


    @Override
    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(root));
        return root.isDisplayed();
    }
}
