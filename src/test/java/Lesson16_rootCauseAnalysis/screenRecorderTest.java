package Lesson16_rootCauseAnalysis;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(listenersWithRecorder.class)

public class screenRecorderTest {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    //monte media library
    //tool for screen casts
    // media player classic is also good tool for play recorded tests

    @BeforeClass()
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @AfterClass()
    public void endSession() {
        driver.quit();
    }

    @BeforeMethod()
    public void actionBeforeMethod(Method method) {
        try {
            monteScreenRecorder.startRecord(method.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod()
    public void actionAfterMethod(){
        try{
            monteScreenRecorder.stopRecord();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(priority = 0)
    public void test01() throws InterruptedException {
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Nimrod");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Altman");

        Thread.sleep(500);
    }

    @Test(priority = 1)
    public void test02() {


        WebElement exoerienceAreaFucos = driver.findElement(By.xpath("//div[@class='control-group']//p[contains(text(),'1')]"));
        WebElement exoerienceRD = driver.findElement(By.xpath("//input[@id='adfsdf-2']"));
        exoerienceRD.click();
        ////input[@id='exp-2']

    }

    @Test(priority = 2)
    public void test03() {

    //datePicker
    WebElement datePicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
        datePicker.click();
    WebElement datePickerFucos = driver.findElement(By.xpath("//div[@id='ui-adfsdf-div']"));
    List<WebElement> dateCells = driver.findElements(By.tagName("td"));

        for(
    WebElement cell :dateCells)

    {
        if (cell.getText().equals("15")) {
            cell.click();
            break;
        }
    }

        //div[@id='ui-datepicker-div']
    }

    @Test(priority = 3)
            public void test04(){
        WebElement professionAreaFucos = driver.findElement(By.xpath("//div[@class='control-group']//p[contains(text(),'1')]"));
        WebElement professionCB = driver.findElement(By.xpath("//input[@id='profession-sf']"));
        professionCB.click();

        ////input[@id='profession-1']
    }


    @Test(priority = 4)
    public void test05(){
        //uploadFile
        WebElement uploadFile = driver.findElement(By.xpath("//input[@id='photo']"));
        uploadFile.sendKeys("C:\\Users\\Nimrod\\Pictures\\Saved Pictures\\Golden-Gate-Bridge-San-Francisco.webp");
    }


    @Test(priority = 5)
    public void test06(){
        //select from dropdown
        Select choosenContinent = new Select(driver.findElement(By.id("continents")));
        choosenContinent.selectByVisibleText("Australia");
    }


    @Test(priority = 6)
    public void test07(){
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }


    @Test(priority = 7)
    public void test08(){
        String url = driver.getCurrentUrl();
        if(url.contains("Nimrod") && url.contains("Altman"))
            System.out.println("test passed");
        else
            System.out.println("test failed");
    }




}
