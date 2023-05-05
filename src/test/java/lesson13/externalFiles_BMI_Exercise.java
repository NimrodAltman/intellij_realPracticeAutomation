package lesson13;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class externalFiles_BMI_Exercise {
    WebDriver driver;
    WebDriverWait wait;
     //String expected = driver.get(getData("expectedBMI"));
    String expectedBMI = getData("expectedBMI");
    String expectedItMeans = getData("expectedItMeans");




    public String getData (String checkMBI)
    {
        DocumentBuilder dBuilder;
        org.w3c.dom.Document doc = null;
        File fxmlFile = new File("./lesson13ExternalFilesXML.xml");
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
        driver.get(getData("URL"));
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }




    @Test(priority = 0)
    public void calculateBMI(){
        BMIdata(getData("Weight"), getData("Height"));
       // String expected = driver.get(getData("expectedBMI"));
        BMIresult(getData("expectedBMI"));
        BMImeans(getData("expectedItMeans"));


    }

    @Step("enter BMI Data")
    public void BMIdata(String weight, String hight ){
        WebElement WeightField = driver.findElement(By.id("weight"));
        WeightField.sendKeys(weight);
        driver.findElement(By.id("hight")).sendKeys(hight);
        driver.findElement(By.id("calculate_data")).click();
    }

    @Step("verify BMI result")
    public void BMIresult(String expectedBMIresult){
        String actualMBI = driver.findElement(By.id("bmi_result")).getAttribute("value");
        Assert.assertEquals(actualMBI,expectedBMIresult, "BMI is not as expected");
    }

    @Step("verify BMI means")
    public void BMImeans(String expectedBMImeans){
        String actualItMeans = driver.findElement(By.id("bmi_means")).getAttribute("value");
        Assert.assertEquals(actualItMeans,expectedBMImeans,"BMI means is not as expected");
    }
}
