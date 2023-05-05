package lesson11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class assertionsAndVerifications_BMI_Exercise
{
    //.getTageName();
    // יש מצב שהתגית של אלמנט משתנה באתר, לכן קודם נזהה מה התגית ולפי התגית נדע איך פעולה לבצע, לדוגמא
    //  תגית INPUT היא תגית של לחצן לכן נדע לבצע לחיצה
    // תגית של SELECT היא תגית של דרופדאון
    // וככה נדע איזה פעולה לבצע לפי התגית שאנחנו מזהים

    //.getText();
    //.getAttribute("value");  -return input tag content
    //.getAttribute("key");  -return web elements Tag Attribute
    //.getSize().getWidth();
    //.getSize().getHeight();
    //getLocation().getX();
    //getLocation().getY();

    WebDriver driver;

    //147 216
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
    public void endSession(){
        driver.quit();
    }

    @Test
    public void assertAndVerify(){
        driver.findElement(By.id("weight")).sendKeys("147");
        driver.findElement(By.id("hight")).sendKeys("216");
        WebElement calcBMI = driver.findElement(By.id("calculate_data"));
        calcBMI.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String bmiResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        Assert.assertEquals(bmiResult, "32", "bmi is not as expected");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        int calcBmiWidth = driver.findElement(By.id("calculate_data")).getSize().getWidth();
        int calcBmiHight = driver.findElement(By.id("calculate_data")).getSize().getHeight();
        int calcBmiCoordinationX = driver.findElement(By.id("calculate_data")).getLocation().x;
        int calcBmiCoordinationY = driver.findElement(By.id("calculate_data")).getLocation().y;


        System.out.println("calc MBI button width is" + " " + calcBmiWidth);
        System.out.println("calc MBI button hight is" + " " + calcBmiHight);
        System.out.println("calc MBI button CoordinationX is" + " " + calcBmiCoordinationX);
        System.out.println("calc MBI button CoordinationX is" + " " + calcBmiCoordinationY);

        Assert.assertEquals(calcBmiWidth, "133");
        Assert.assertEquals(calcBmiHight, "35");
        Assert.assertEquals(calcBmiCoordinationX, "439");
        Assert.assertEquals(calcBmiCoordinationY, "265");

        Assert.assertTrue(calcBMI.isEnabled());
        Assert.assertTrue(calcBMI.isDisplayed());
        Assert.assertFalse(calcBMI.isSelected());

        calcBMI.getAttribute("value");
        calcBMI.getTagName();
        Assert.assertEquals(calcBMI.getAttribute("value"), "Calculate BMI");
        Assert.assertEquals(calcBMI.getTagName(), "input");


        WebElement hiddenElement = driver.findElement(By.id("new_input"));
        Assert.assertFalse(hiddenElement.isDisplayed());

//        try {
//
//        }
//        catch (Exception e)
//        {
//
//        }



    }



}
