package lesson11;

import com.sun.javafx.binding.StringFormatter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class switchAndNavigation {
    WebDriver driver;

    @BeforeClass()
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().refresh();
        driver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");
    }

    @Test(priority=0)
    public void alertBTN() {
        //alertBTN
        driver.findElement(By.id("btnAlert")).click();
        Alert alertPopup = driver.switchTo().alert();
        System.out.println("alert massage is:" + " " + alertPopup);
        alertPopup.accept();
        String expectedAlertBtnMsg = "Alert is gone.";

      //  one way to do assert(initiate element into string and then do the assert)
        String actualAlertBtnMsg = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(actualAlertBtnMsg,expectedAlertBtnMsg);

      // second way to do assert(find element directly with the assert)
      //Assert.assertEquals(driver.findElement(By.id("output")).getText(),expectedAlertBtnMsg);

      //  (    WebElement alertBtnMsg = driver.findElement(By.id("output"));
      //  alertBtnMsg.getText();      ) - it not work with web element because to get text/attribute/etc must do it with string/int

    }

    @Test(priority=1)
    public void promotBTN(){
        //promotBTNCheck
        driver.findElement(By.id("btnPrompt")).click();
        Alert promotPopup = driver.switchTo().alert();
        String promotPopupText = promotPopup.getText();
        System.out.println("promot alert massage is:" + " " + promotPopupText);
        promotPopup.sendKeys("nimrod");
        promotPopup.accept();
        String expectedPromotBtnMsg = "nimrod";
        String promotBtnMsg = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(promotBtnMsg, expectedPromotBtnMsg, "promotBtnMsg is not as expected");
    }

    @Test(priority=2)
    public void confirmBox(){
        //confirmBoxCheck
        driver.findElement(By.id("btnConfirm")).click();
        Alert confirmBox = driver.switchTo().alert();
        String confirmBoxText = confirmBox.getText();
        System.out.println("confirmBox text is :" + " " + confirmBoxText);
        confirmBox.accept();
        String ExpectedConfirmOutput = "Confirmed.";
        String ActualConfirmOutput = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(ActualConfirmOutput, ExpectedConfirmOutput);

        driver.findElement(By.id("btnConfirm")).click();
        confirmBox.dismiss();
        String rejectedOutput = "Rejected!";
        Assert.assertEquals(driver.findElement(By.id("output")).getText(), rejectedOutput);

    }


    @Test(priority=3)
    public void iframeCheck(){
        //iframeCheck

        WebElement iframe = driver.findElement(By.cssSelector("iframe[src='ex_switch_newFrame.html']"));
        driver.switchTo().frame(iframe);
        String iframeText = driver.findElement(By.id("iframe_container")).getText();
        System.out.println("iFrame test is:" + " " + iframeText);
        String iFrameExpectedText = "This is an IFrame !";
         Assert.assertEquals(iframeText, iFrameExpectedText, "iframeText is not as expected");

         driver.switchTo().defaultContent();
        String getHeaderText = driver.findElement(By.id("title")).getText();
        String expectedHeaderText = "Switch and Navigate";
        Assert.assertEquals(getHeaderText, expectedHeaderText);
    }

    @Test(priority=4)
    public void newTabCheck(){
        String oldTab = driver.getWindowHandle();
        driver.findElement(By.id("btnNewTab")).click();
        //Set<String> winHandles = driver.getWindowHandles();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);

        }

        String expectedNewTabText = "This is a new tab";
        String actualNewTabText = driver.findElement(By.id("new_tab_container")).getText();
        Assert.assertEquals(expectedNewTabText,actualNewTabText);
        System.out.println("New Tab Text is:" + " " + actualNewTabText);
        driver.close(); //close the current tab/window
        driver.switchTo().window(oldTab);
        String oldTabTitle = driver.getTitle();
        System.out.println("old Tab Title is:" + " " + oldTabTitle);
    }

    @Test(priority=5)
    public void newWindowCheck(){
        String oldWindow = driver.getWindowHandle();
        WebElement moveToNewWindow = driver.findElement(By.cssSelector("a[href='ex_switch_newWindow.html']"));
        moveToNewWindow.click();

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        String expectedNewWindowText = "This is a new window";
        String actualNewWindowText = driver.findElement(By.id("new_window_container")).getText();
        Assert.assertEquals(actualNewWindowText,expectedNewWindowText);
        System.out.println("actual New Window Text is:" + " " + actualNewWindowText);
        driver.close();
        driver.switchTo().window(oldWindow);
        String oldWindowTitle = driver.getTitle();
        System.out.println("old Tab window is:" + " " + oldWindowTitle);
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }
}
