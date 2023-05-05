package lesson9_drivers_methods_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class seleniumdev

{

    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.get("https://www.selenium.dev/");
    }

    @Test
    public void blogButton()
    {
        WebElement blogButtonByLinkText =  driver.findElement(By.linkText("Blog"));
        System.out.println(blogButtonByLinkText);

        List<WebElement> blog = driver.findElements(By.className("nav-link"));
        blog.get(5);
        System.out.println(blog);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        List<WebElement> SeleniumLinks = driver.findElements(By.partialLinkText("Selenium"));
        System.out.println(SeleniumLinks.size());

        List<WebElement> seleniumLinks = driver.findElements(By.partialLinkText("selenium"));
        System.out.println(seleniumLinks.size());
    }

    @AfterClass
    public void closeSession()
    {
        driver.quit();
    }


}
