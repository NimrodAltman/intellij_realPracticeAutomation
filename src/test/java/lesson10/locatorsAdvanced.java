package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class locatorsAdvanced
{
    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeSession()
    {

        driver.quit();
    }


    @Test
    public void findElements()
    {
        WebElement locator1 = driver.findElement(By.id("locator_id"));
        System.out.println(locator1);
        WebElement locator2 = driver.findElement(By.name("locator_name"));
        System.out.println(locator2);
        WebElement locator3 = driver.findElement(By.className("locator_class"));
        System.out.println(locator3);
        WebElement locator4 = driver.findElement(By.linkText("Find my locator (4)"));
        System.out.println(locator4);
        WebElement locator5 = driver.findElement(By.partialLinkText("myLocator"));
        System.out.println(locator5);

        List<WebElement> locator6 = driver.findElements(By.tagName("a"));
        locator6.get(3);
        System.out.println(locator6);

        WebElement locator7 = driver.findElement(By.xpath("//input[@value='Find my locator (7)']"));
        System.out.println(locator7);

        WebElement locator8 = driver.findElement(By.cssSelector("input[value='Find my locator (7)']"));
        System.out.println(locator8);

    }
}



//XPATH LOCATOR
// //html/body/div/form/fieldset/div[@class='search-input']
// //input[@id='searchInput' and @name='search']
// //div[starts-with(@id ,'typeahead')]
// //div[ends-with(@id, '-suggestions')]
// //div[contains(@id, '-suggestions')]

//xpath+cssSelector examples- AMAZON website- shopping cart
//XPATH
// //span[@class='nav-cart-icon nav-sprite']
// //div[@id='nav-cart-count-container']/span[2]
// //div[@id='nav-cart-count-container']//span[@class='nav-cart-icon nav-sprite']
// //*[@id='nav-cart-count-container']/span[2]
//CSS SELECTOR
// #nav-cart-count-container > span.nav-cart-icon.nav-sprite
// #nav-cart-count-container > span[class='nav-cart-icon nav-sprite']

//find all tags that contains "-suggestions"
////*[contains(@id, '-suggestions')]


//cssSELECTOR locator

//find class with space between his words
//.central-featured-lang.lang3
// cssSelector("button[aria-label='התחל חיפוש'][type='button']"))