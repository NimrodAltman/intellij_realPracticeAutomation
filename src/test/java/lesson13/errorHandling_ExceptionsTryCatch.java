package lesson13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class errorHandling_ExceptionsTryCatch {

    // all types of selenium exceptions list:
    // https://www.selenium.dev/selenium/docs/api/py/common/selenium.common.exceptions.html

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

    @AfterMethod
    public void afterMethod() {
        driver.navigate().refresh();
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }

    @Test()
    public void test01() throws InterruptedException {
        try {
            driver.findElement(By.id("btn")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("input[id='checkbox']"));
        }
        catch(Exception e){
            System.out.println("element doesnt found: " + e);
        }
    }

        @Test()
        public void Test02() throws InterruptedException {
            driver.findElement(By.id("btn")).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='checkbox']")));

    }
}
