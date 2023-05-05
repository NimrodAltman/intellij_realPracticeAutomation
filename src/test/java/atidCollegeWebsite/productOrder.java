package atidCollegeWebsite;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class productOrder
{

    WebDriver driver;


    public String getData (String checkOutDetails)
    {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fxmlFile = new File("./atidCollegeXMLpractive.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            try {
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(fxmlFile);
            }
            catch (Exception e) {
                System.out.println("exeption is readind XML file" + e);
            }

            doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(checkOutDetails).item(0).getTextContent();

    }



    @BeforeClass
    public void GetMainPage()
    {
        // define chrome web driver
        //System.setProperty("webdriver.chrome.driver", "C:\\Automation-QA\\selenium-browsers\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://atid.store/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualTitle = driver.getTitle();
        String expectedTitle = "ATID Demo Store – ATID College Demo Store for Practicing QA Automation";
        Assert.assertEquals(expectedTitle,actualTitle);
    }

    /*


    @Test(priority=2)
    public void MoveToProductPage()
    {
        WebElement MoveToProduct = driver.findElement(By.xpath("//a[@href='https://atid.store/product/anchor-bracelet/']//img[@class='attachment-woocommerce_thumbnail size-woocommerce_thumbnail']"));
        MoveToProduct.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualProductPageTitle = driver.getTitle();
        String expectedProductPageTitle ="Anchor Bracelet – ATID Demo Store";
         Assert.assertEquals(expectedProductPageTitle, actualProductPageTitle);
    }
   */

    @Test(priority=1, description = "Get into product page")
    @Description("Test Description: product page")
    public void MoveToProductsPage()
    {
        WebElement shopNowBtn = driver.findElement(By.xpath("//a[@href='https://atid.store/store/']//span[@class='elementor-button-content-wrapper']//span[@class='elementor-button-text'][normalize-space()='Shop Now']"));
        shopNowBtn.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualProductsPageTitle = driver.getTitle();
        String expectedProductsPageTitle = "Products – ATID Demo Store";
        // Assert.assertEquals(expectedProductsPageTitle,actualProductsPageTitle);
    }


    @Test(priority=2)
    public void productsPageActions()
    {

        // filter the products range by products price slider
        WebElement filterBySlideMinPriceRange = driver.findElement(By.xpath("//div[@class='price_slider ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content']//span[1]"));
        Actions actionsMinPrice = new Actions(driver);
        actionsMinPrice.dragAndDropBy(filterBySlideMinPriceRange, 70, 216).release().build().perform();
        filterBySlideMinPriceRange.click();

        WebElement filterBySlideMaxPriceRange = driver.findElement(By.xpath("//div[@class='price_slider ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content']//span[2]"));
        Actions actionsMaxPrice = new Actions(driver);
        actionsMaxPrice.dragAndDropBy(filterBySlideMaxPriceRange,-70,216).release().build().perform();
        filterBySlideMaxPriceRange.click();

        WebElement filterPrice = driver.findElement(By.xpath("//button[normalize-space()='Filter']"));
        filterPrice.click();


        //1. before filter capture the prices
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("woocommerce-Price-amount amount"));
        //1.1 remove the shekel symbol from the prices and convert string to double
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for(WebElement p : beforeFilterPrice)
        {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("₪","")));
        }


        //2. filter the price from the dropdown(select is class for dropdown handle)
        Select dropdown = new Select(driver.findElement(By.className("orderby")));
        dropdown.selectByVisibleText("Sort by price: low to high");


        //3. after filter capture the prices
        List<WebElement> afterFilterPrice = driver.findElements(By.className("woocommerce-Price-amount amount"));
        //3.1 remove the shekel symbol from the prices and convert string to double
        List<Double> afterFilterPriceList = new ArrayList<>();

        for(WebElement p :afterFilterPrice)
        {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("₪","")));
            System.out.println(afterFilterPrice);
        }
        

        //4. compare the values/Assert the values(first we need to sort the values of beforeFilterPrice)
        Collections.sort(beforeFilterPriceList); //it will sort the values in the list
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        // Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        //move to second result page
        WebElement moveNextResultPage = driver.findElement(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[3]/a"));
        moveNextResultPage.click();
    }


    @Test(priority=3)
    public void SelectProduct()
    {
        //select item and move to product page
        WebElement MoveToProduct = driver.findElement(By.xpath("(//h2[normalize-space()='Black Hoodie'])[1]"));
        MoveToProduct.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualProductPageTitle = driver.getTitle();
        String expectedProductPageTitle ="Black Hoodie – ATID Demo Store";
        Assert.assertEquals(expectedProductPageTitle, actualProductPageTitle);
    }


    @Test(priority=4)
    public void addToCart()
    {
        //add to cart
        WebElement addToCart = driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
        addToCart.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }


    @Test(priority=5)
    public void moveToCheckoutPage()
    {
        //focusSumProductPopup enter to check out
        WebElement focusSumProductPopup = driver.findElement(By.xpath("//div[@class='ast-primary-header-bar ast-primary-header main-header-bar site-header-focus-item']"));
        focusSumProductPopup.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        //move to check out
        WebElement sumProductPopup = driver.findElement(By.xpath("//span[normalize-space()='1']"));
        Actions overSumProductPopup = new Actions(driver);
        overSumProductPopup.moveToElement(sumProductPopup).perform();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement moveToCheckoutPage = driver.findElement(By.xpath("//div[@class='ast-site-header-cart-data']//a[@class='button checkout wc-forward'][normalize-space()='Checkout']"));
        moveToCheckoutPage.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }


    //(//a[@class='button checkout wc-forward'][normalize-space()='Checkout'])[1]

    @Test(priority=6)
    public void fillCheckoutPage()
    {
        //fill checkout info

        String firstName = driver.findElement(By.xpath("//h1[normalize-space()='Checkout']")).getText();
        WebElement fillFirstName = driver.findElement(By.xpath("//input[@id='billing_first_name']"));
        //fillFirstName.sendKeys("Nimrod");
        fillFirstName.sendKeys(firstName);
        WebElement fillLastName = driver.findElement(By.xpath("//input[@id='billing_last_name']"));
        fillLastName.sendKeys(getData(("checkOutLastName")));
        //fillLastName.sendKeys("Altman");
        WebElement fillCompanyName = driver.findElement(By.xpath("//input[@id='billing_company']"));
        fillCompanyName.sendKeys(getData(("checkOutCompanyName")));
        //fillCompanyName.sendKeys("Altman Inc");
        WebElement selectCountryDropdown = driver.findElement(By.xpath("//span[@id='select2-billing_country-container']"));
        selectCountryDropdown.click();
        WebElement inputCountry = driver.findElement(By.xpath("//input[@role='combobox']"));
        inputCountry.sendKeys("Israel");
        inputCountry.sendKeys(Keys.ENTER);
        WebElement fillStreet = driver.findElement(By.xpath("//input[@id='billing_address_1']"));
        fillStreet.sendKeys("Harav Levi");
        WebElement fillApartment = driver.findElement(By.xpath("//input[@id='billing_address_2']"));
        fillApartment.sendKeys("1A");
        WebElement fillZipCode = driver.findElement(By.xpath("//input[@id='billing_postcode']"));
        fillZipCode.sendKeys("2568877");
        WebElement fillCity = driver.findElement(By.xpath("//input[@id='billing_city']"));
        fillCity.sendKeys("Bat yam");
        WebElement fillPhoneNumber = driver.findElement(By.xpath("//input[@id='billing_phone']"));
        fillPhoneNumber.sendKeys("055-8874455");
        WebElement fillEmail = driver.findElement(By.xpath("//input[@id='billing_email']"));
        fillEmail.sendKeys("test@test.com");

        //fill checkout info   - different address
        WebElement shipToDifferentAddress = driver.findElement(By.xpath("//input[@id='ship-to-different-address-checkbox']"));
        shipToDifferentAddress.click();
        WebElement shipFirstName = driver.findElement(By.xpath("//input[@id='shipping_first_name']"));
        shipFirstName.sendKeys("Nimrod");
        WebElement shipLastName = driver.findElement(By.xpath("//input[@id='shipping_last_name']"));
        shipLastName.sendKeys("Altman");
        WebElement shipCompanyName = driver.findElement(By.xpath("//input[@id='shipping_company']"));
        shipCompanyName.sendKeys("Altman Inc");
        WebElement shipSelectRegionDropdown = driver.findElement(By.xpath("//span[@id='select2-shipping_country-container']"));
        shipSelectRegionDropdown.click();
        WebElement shipInputRegion = driver.findElement(By.xpath("//input[@role='combobox']"));
        shipInputRegion.sendKeys("Ireland");
        shipInputRegion.sendKeys(Keys.ENTER);
        WebElement shipFillStreet = driver.findElement(By.xpath("//input[@id='shipping_address_1']"));
        shipFillStreet.sendKeys("test street");
        WebElement shipFillApartment = driver.findElement(By.xpath("//input[@id='shipping_address_2']"));
        shipFillApartment.sendKeys("test Apartment");
        WebElement shipFillCity = driver.findElement(By.xpath("//input[@id='shipping_city']"));
        shipFillCity.sendKeys("Dublin");
        WebElement shipSelectCountryDropdown = driver.findElement(By.xpath("//span[@id='select2-shipping_state-container']"));
        shipSelectCountryDropdown.click();
        WebElement shipInputCountry = driver.findElement(By.xpath("//input[@role='combobox']"));
        shipInputCountry.sendKeys("Dublin");
        shipInputCountry.sendKeys(Keys.ENTER);

        //fill checkout info   - shipping method
        WebElement shippingMethod = null;
        try {
            shippingMethod = driver.findElement(By.id("shipping_method_0_flat_rate3"));
            shippingMethod.click();

        } catch (Exception shippingMethodFailed) {
            shippingMethodFailed.printStackTrace();
        }


        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(priority=7)
    public void placeOrder()
    {
        // place order

        WebElement focusOrderArea = driver.findElement(By.xpath("//div[@class='form-row place-order']"));
        focusOrderArea.click();
        WebElement placeOrder = driver.findElement(By.xpath("//button[@id='place_order']"));
        placeOrder.click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }

/*
    @Test(priority=9)
    public void verifyFinalTextDisplayed()
    {
        WebElement actualFinalTextDisplayed = driver.findElement(By.xpath("//*[@id=\"post-40\"]/div/div/section[2]/div/div/div/div/div/div/div/form[3]/div[1]/ul/li[1]"));
        String expectedFinalTextDisplayed = "No shipping method has been selected. Please double check your address, or contact us if you need any help.";
        Assert.assertEquals(expectedFinalTextDisplayed,actualFinalTextDisplayed);
    }



    @AfterClass
    public void AfterTest()
    {
            driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
            driver.quit();
    }

    */


        /*
    @Test(priority=3)
    public void SlidePriceInProductPage() throws InterruptedException {

    }


      WebElement minPriceAfterFilter = driver.findElement(By.xpath("(//span[@class='from'])[1]"));
        WebElement maxPriceAfterFilter = driver.findElement(By.xpath("(//span[@class='to'])[1]"));
        WebElement actualProductsPrices = driver.findElement(By.className("woocommerce-Price-amount amount"));
        minPriceAfterFilter.get
        Assert.assertTrue();
        */







}
