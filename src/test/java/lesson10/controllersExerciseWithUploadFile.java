package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class controllersExerciseWithUploadFile
{
    WebDriver driver;

    @BeforeClass
    public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void fillFields() throws InterruptedException {
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Nimrod");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Altman");

        WebElement exoerienceAreaFucos = driver.findElement(By.xpath("//div[@class='control-group']//p[contains(text(),'1')]"));
        WebElement exoerienceRD = driver.findElement(By.xpath("//input[@id='exp-2']"));
        exoerienceRD.click();

        //datePicker
        WebElement datePicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
        datePicker.click();
        WebElement datePickerFucos = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"));
        List<WebElement> dateCells = driver.findElements(By.tagName("td"));

        for (WebElement cell : dateCells)
        {
            if(cell.getText().equals("15"))
            {
                cell.click();
                break;
            }
        }

        WebElement professionAreaFucos = driver.findElement(By.xpath("//div[@class='control-group']//p[contains(text(),'1')]"));
        WebElement professionCB = driver.findElement(By.xpath("//input[@id='profession-1']"));
        professionCB.click();

        //uploadFile
        WebElement uploadFile = driver.findElement(By.xpath("//input[@id='photo']"));
        uploadFile.sendKeys("C:\\Users\\Nimrod\\Pictures\\Saved Pictures\\Golden-Gate-Bridge-San-Francisco.webp");


        //select from dropdown
        Select choosenContinent = new Select(driver.findElement(By.id("continents")));
        choosenContinent.selectByVisibleText("Australia");


        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        String url = driver.getCurrentUrl();
        if(url.contains("Nimrod") && url.contains("Altman"))
        System.out.println("test passed");
        else
            System.out.println("test failed");

        Thread.sleep(500);

        String[] arr = driver.getCurrentUrl().split("%");
        String day = arr[0].substring(arr[0].length() - 2);
        String mounth = arr[1].substring(arr[1].length() - 2);
        String year = arr[2].substring(2,6);
        System.out.println(year + "-" + mounth + "-" + day);
        // Or :
        System.out.println(driver.getCurrentUrl().split("%")[2].substring(2,6) + "-" + driver.getCurrentUrl().split("%")[1].substring(arr[1].length() - 2) + "-" + driver.getCurrentUrl().split("%")[0].substring(arr[0].length() - 2));

    }



    @AfterClass
    public void endSession()
    {
        driver.quit();
    }




}
