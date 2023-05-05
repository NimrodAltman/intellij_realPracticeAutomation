package lesson12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class midTermExam {
    WebDriver driver;
    String firstName = "nimrod";
    String lastName = "altman";
    String actual,expectd;
    WebDriverWait wait;

    @BeforeClass
            public void startSession()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/pizza/");
        wait = new WebDriverWait(driver, 7);
    }

    @AfterClass
    public void endSession(){
        driver.close();
        driver.quit();
    }

    @Test(priority=0)
    public void test01_verifyInitialPrice(){
        String actualInitialOrderPrice = driver.findElement(By.className("ginput_total_5")).getText();
        String expectedInitialOrderPrice = "$7.50";
        Assert.assertEquals(actualInitialOrderPrice, expectedInitialOrderPrice, "Initial price is not ass expected(7.50)");
    }

    @Test(priority=1)
    public void test02_verifyUpdatePrice(){
        WebElement firstNameField = driver.findElement(By.cssSelector("input[id='input_5_22_3']"));
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driver.findElement(By.cssSelector("input[id='input_5_22_6']"));
        lastNameField.sendKeys(lastName);
        WebElement phoneNumber = driver.findElement(By.cssSelector("input[id='input_5_23']"));
        phoneNumber.sendKeys("0501122333");

        Select delivery = new Select(driver.findElement(By.cssSelector("select[id='input_5_21']")));
        delivery.selectByValue("Delivery|3");

        actual = driver.findElement(By.className("ginput_total_5")).getText();
        expectd = "$10.50";
        Assert.assertEquals(actual,expectd,"Update price is not ass expected(10.50)");

    }

    @Test(priority=2)
    public void test03_verifyAlertData(){
        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='coupon.html']"));
        driver.switchTo().frame(iframe);
        String iframeCouponNum = driver.findElement(By.id("coupon_Number")).getText();
        driver.switchTo().defaultContent();
        WebElement commentArea = driver.findElement(By.cssSelector("textarea[class='textarea medium']"));
        commentArea.sendKeys(iframeCouponNum);
        WebElement submitBtn = driver.findElement(By.id("gform_submit_button_5"));
        submitBtn.click();
       Alert alertPopup =  driver.switchTo().alert();
       actual = alertPopup.getText();
       alertPopup.accept();
       expectd = firstName + " " + lastName + " " + iframeCouponNum;
       Assert.assertEquals(actual,expectd, "Alert msg is not ass expected(nimrod altman 088-234)");

    }


}
