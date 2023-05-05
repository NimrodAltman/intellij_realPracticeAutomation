package lesson14;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class reportingSystem {

    //the command to export Allure Report from intellij-Terminal
    //
    //first command to position under the project
    //allure --version
    //
    //second command to export Allure Report
    //allure serve allure-results


    WebDriver driver;
    WebDriverWait wait;
    String test03BMIdataWeight = "90";
    String test03BMIdataHeight = "185";
    String test03BMIresult = "26";
    String test03BMImeans = "That you are not healthy.";


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
       // driver.get("https://atidcollege.co.il/Xamples/bmi/");
        driver.get(getData("URL"));
        wait = new WebDriverWait(driver, 7);
      //  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
    }

    @AfterMethod()
    public void returnToMainPage(){
        driver.get("https://atidcollege.co.il/Xamples/bmi/");
    }


    @AfterClass()
    public void endSession(){
        driver.quit();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test(priority = 0,description = "calculate BMI1")
    @Description("test description: BMI check1")
    public void test01(){
        BMIdata("75", "178");
        BMIresult("24");
        BMImeans("That you are healthy.");
    }

    @Test(priority = 1,description = "calculate BMI2")
    @Description("test description: BMI check2")
    public void test02(){
        BMIdata("75", "178");
        BMIresult("33");
        BMImeans("That you are not healthy.");
    }

    @Test(priority = 2,description = "calculate BMI3")
    @Description("test description: BMI check3")
    public void test03(){
        BMIdata(test03BMIdataWeight, test03BMIdataHeight);
        BMIresult(test03BMIresult);
        BMImeans(test03BMImeans);
    }

    @Test(priority = 3,description = "calculate BMI4")
    @Description("test description: BMI check4")
    public void test04(){
        BMIdata(getData("Weight"), getData("Height"));
        BMIresult(getData("expectedBMI"));
        BMImeans(getData("expectedItMeans"));
    }

    @Step("enter BMI Data")
    public void BMIdata(String weight, String hight){
        driver.findElement(By.id("weight")).sendKeys(weight);
        driver.findElement(By.id("hight")).sendKeys(hight);
        driver.findElement(By.id("calculate_data")).click();
    }

    @Step("verify MBI result")
    public void BMIresult(String expectedBMIresult){
        try {
            Assert.assertEquals(driver.findElement(By.id("bmi_result")).getAttribute("value"), expectedBMIresult);
        } catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert Failed. see details: " + e);
        }
    }

    @Step("verify BMI means")
    public void BMImeans(String expectedBMImeans){
        try {
            Assert.assertEquals(driver.findElement(By.id("bmi_means")).getAttribute("value"), expectedBMImeans);
        }catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert Failed. see details: " + e);
        }
    }


}
