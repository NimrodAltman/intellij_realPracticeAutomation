package BMI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class bmiCalculator
{
    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");
    }

    @AfterClass
    public void closeSession()
    {
        driver.quit();
    }

    @AfterMethod()
    public void returnToMainPage(){

    }

    @Test
    public void bmiCalculator()
    {
        try {
            driver.findElement(By.id("ss"));
        }
        catch(AssertionError e){

    }

    }


}
