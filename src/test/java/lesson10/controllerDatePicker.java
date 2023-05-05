package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class controllerDatePicker
{
    WebDriver driver;

    @BeforeClass
            public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.get("https://www.issta.co.il/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }


    @Test
    public void selectDateInDateSearchBox()
    {
        //date picker- issta site
        WebElement dateSelect = driver.findElement(By.xpath("//div[@id='packages_search_dynamic']//input[@id='start_date']"));
        dateSelect.click();
        WebElement monthAreaFucos = driver.findElement(By.xpath("//body//div//se-packages//table[1]"));
        List<WebElement> dateCells = monthAreaFucos.findElements(By.tagName("td"));
        for (WebElement cell : dateCells)
        {
            if(cell.getText().equals("22")) //select 22th date
            {
                cell.click();
                break;
            }
        }

    }








    @AfterClass
    public void endSession()
    {
        driver.quit();
    }
}
