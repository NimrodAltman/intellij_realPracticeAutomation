package lesson12;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class synchronization {

    WebDriver driver;
    String actual,expected;
    WebDriverWait wait;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public void endSession(){
        driver.quit();
    }

    @Test(priority = 0)
    public void test01_startRenderedBtn(){
        driver.findElement(By.id("rendered")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        actual = driver.findElement(By.id("finish2")).getText();
        expected = "My Rendered Element After Fact!";
        Assert.assertEquals(actual,expected,"start rendered btn msg not as expected");
    }

    @Test(priority = 1) //first way to Thread.sleep by throws
    public void test02_startHiddenBtnThreadSleep1() throws InterruptedException {
        driver.findElement(By.id("hidden")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("loading1")).isDisplayed();
    }

    @Test(priority = 2)//second way to Thread.sleep by Uninterruptibles
    public void test02_startHiddenBtnThreadSleep2(){
        driver.navigate().refresh();
        driver.findElement(By.id("hidden")).click();
        Uninterruptibles.sleepUninterruptibly(6,TimeUnit.SECONDS);
        driver.findElement(By.id("loading1")).isDisplayed();
    }

    @Test(priority = 3)
    public void test03_RemoveBtn(){
        driver.findElement(By.cssSelector("input[id='checkbox']")).click();
        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        actual = driver.findElement(By.id("message")).getText();
        expected = "It's gone!";
        Assert.assertEquals(actual,expected);
    }

    @Test(priority = 4)
    public void test04_addBtn(){
        driver.findElement(By.id("btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        actual = driver.findElement(By.id("message")).getText();
        expected = "It's back!";
        Assert.assertEquals(actual,expected);
    }

}
