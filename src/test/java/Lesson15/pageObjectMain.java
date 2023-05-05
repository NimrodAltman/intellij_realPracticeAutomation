package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class pageObjectMain {

    WebDriver driver;
    WebDriverWait wait;
    pageObjectLoginPage login;
    pageObjectFormPage form;
    pageObjectClickPage clickPage;
    JavascriptExecutor js;

    @BeforeClass()
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        login = PageFactory.initElements(driver, pageObjectLoginPage.class);
        form = PageFactory.initElements(driver, pageObjectFormPage.class);
        clickPage = PageFactory.initElements(driver, pageObjectClickPage.class);
        js = (JavascriptExecutor) driver;

    }

    @Test(priority = 0)
    public void test01(){
        login.loginPageAction("selenium");
        form.formPageAction("israel");
        clickPage.clickPageAction();
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }


}
