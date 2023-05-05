package lesson13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(listeners.class)

public class errorHandling_ExceptionsListeners
{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass()
    public void startSession(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
        wait = new WebDriverWait(driver, 6);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }

    @Test()
    public void test01() throws InterruptedException {

            driver.findElement(By.id("btn")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("input[id='checkbox']"));

            System.out.println("element doesnt found: ");

    }

    @Test()
    public void Test02() throws InterruptedException {
        driver.findElement(By.id("btn")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[id='checkbox']"));
    }

}
