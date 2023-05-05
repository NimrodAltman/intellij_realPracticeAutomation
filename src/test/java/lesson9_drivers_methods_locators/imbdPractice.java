package lesson9_drivers_methods_locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class imbdPractice
{

    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.navigate().refresh();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");
    }

    @Test
    public void imdbVerifyTitle()
    {

        if(driver.getTitle().equals("Some Title"))
            System.out.println("Title correct");
        else
            System.out.println("Title failed");


     /*   String actualTitle = driver.getTitle();
        String expectedTitle = ("IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows");
        Assert.assertEquals(actualTitle,expectedTitle);
        String actualURL = driver.getCurrentUrl();
        String expectedURL = ("https://www.imdb.com/");
        Assert.assertEquals(actualURL,expectedURL); */

    }


    @Test
    public void imdbVerifyURL()
    {

        if(driver.getCurrentUrl().equals("Some URL"))
            System.out.println("URL passed");
        else
            System.out.println("URL failed");

     /*   String actualTitle = driver.getTitle();
        String expectedTitle = ("IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows");
        Assert.assertEquals(actualTitle,expectedTitle);
        String actualURL = driver.getCurrentUrl();
        String expectedURL = ("https://www.imdb.com/");
        Assert.assertEquals(actualURL,expectedURL); */

    }

    @AfterClass
      public void closeSession()
    {
        driver.quit();
    }

}
