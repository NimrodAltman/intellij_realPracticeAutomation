package lesson9_drivers_methods_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class wikipediaElements

{
    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.get("https://www.wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void identifyElements()
    {
        WebElement searchField = driver.findElement(By.xpath("//input[@id='searchInput']"));
        WebElement languageOnSearchField = driver.findElement(By.xpath("//select[@id='searchLanguage']"));
        WebElement sideBarContent = driver.findElement(By.xpath("//div[@class='footer-sidebar']"));
        WebElement wikimedia = driver.findElement(By.className("central-featured-logo"));




        WebElement[] Elements = {searchField,languageOnSearchField,sideBarContent,wikimedia};
        for(int i = Elements.length-1; i>=0; i--)
        {
            System.out.println(Elements[i]);
        }
    }



    @AfterClass
    public void closeSession()
    {
        driver.quit();
    }


}
