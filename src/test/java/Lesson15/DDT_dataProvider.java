package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class DDT_dataProvider {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public String getDataDDT (String checkMBI)
    {
        DocumentBuilder dBuilder;
        org.w3c.dom.Document doc = null;
        File fxmlFile = new File("./lesson15DDT.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fxmlFile);
        }
        catch (Exception e) {
            System.out.println("exeption is readind XML file" + e);
        }

        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(checkMBI).item(0).getTextContent();

    }

    @BeforeClass()
    public void startSession(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
        wait = new WebDriverWait(driver, 7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }

    @DataProvider(name = "data-provider")
    public Object[][] getDataObject(){
        return new Object[][]{
                {"Automation","Automation"},
                {"Israel","Israel"},
                {"BlahBlah","Search results"}
        };
    }

    @DataProvider(name = "data-provider1")
    public Object[][] getDataObj(){
        return new Object[][]{
                {getDataDDT("searchValue0"),getDataDDT("expectedResult0")},
                {getDataDDT("searchValue1"),getDataDDT("expectedResult1")},
                {getDataDDT("searchValue2"),getDataDDT("expectedResult2")}
        };
    }

    @Test(priority = 0, dataProvider = "data-provider")
    @Description("test the main test")
    public void test01(String searchValue, String expectedResult){
        driver.findElement(By.id("searchInput")).sendKeys(searchValue);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("firstHeading")).getText(), expectedResult );
        driver.get("https://www.wikipedia.org/");

    }

    //get dataProvider data from another class
    @Test(priority = 1, dataProvider = "DataProviderArray", dataProviderClass = dataProviderSeperateClass.class)
    public void test02(String searchValue, String expectedResult){
        driver.findElement(By.id("searchInput")).sendKeys(searchValue);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("firstHeading")).getText(), expectedResult );
        driver.get("https://www.wikipedia.org/");

    }

    @Test(priority = 2, dataProvider = "data-provider1")
    public void test03(String searchValue, String expectedResult){
        driver.findElement(By.id("searchInput")).sendKeys(searchValue);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("firstHeading")).getText(), expectedResult );
        driver.get("https://www.wikipedia.org/");

    }




}
