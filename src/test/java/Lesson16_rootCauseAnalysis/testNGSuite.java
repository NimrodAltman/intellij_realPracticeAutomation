package Lesson16_rootCauseAnalysis;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class testNGSuite {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

//    @BeforeClass()
//    public void startSession(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("");
//        wait = new WebDriverWait(driver, 5);
//        js = (JavascriptExecutor) driver;
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    }

    @Test(groups = {"test1"})
    public void test01(){
        System.out.println("this is test01 from class mtTestNG2");
    }

    @Test(groups = {"test2"})
    public void test02(){
        System.out.println("this is test02 from class mtTestNG2");
    }

    @Test(groups = {"test3"})
    public void testo3(){
        System.out.println("this is test03 from class mtTestNG2");
    }

    @Test(groups = {"test4"})
    public void testo4(){
        System.out.println("this is test04 from class mtTestNG2");
    }

    @Test(groups = {"test3", "test2"})
    public void testo5(){
        System.out.println("this is test05 from class mtTestNG2");
    }


}
