package lesson11;

import java.util.ArrayList;
import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class switchAndNavigationYoni
{
    WebDriver driver;
    String output;
    String mainTitle = "Switch and Navigate";

    @BeforeClass
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://atidcollege.co.il/Xamples/ex_switch_navigation.html");
    }

    @Test
    public void Test1_HandlingAlert()
    {
        // -------------  Handling Alert  ----------------------------------
        driver.findElement(By.id("btnAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());
        alert.accept();
        output = "Alert is gone.";
        assertEquals(driver.findElement(By.id("output")).getText(),output);
    }

    @Test
    public void Test2_HandlingPrompt()
    {
        // -------------  Handling Prompt  ---------------------------------
        driver.findElement(By.id("btnPrompt")).click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("Selenium");
        System.out.println("Prompt text is: " + prompt.getText());
        prompt.accept();
        output = "Selenium";
        assertEquals(driver.findElement(By.id("output")).getText(),output);
    }

    @Test
    public void Test3_HandlingConfirmBox()
    {
        // -------------  Handling Confirm Box  ----------------------------
        driver.findElement(By.id("btnConfirm")).click();
        Alert confirmbox = driver.switchTo().alert();
        System.out.println("Confirm Boxt text is: " + confirmbox.getText());
        confirmbox.accept();
        output = "Confirmed.";
        assertEquals(driver.findElement(By.id("output")).getText(),output);

        driver.findElement(By.id("btnConfirm")).click();
        confirmbox.dismiss();
        output = "Rejected!";
        assertEquals(driver.findElement(By.id("output")).getText(),output);
    }

    @Test
    public void Test4_HandlingiFrame()
    {
        // ------------ Go to iFrame and Return to Main Window----------------------
        WebElement iFrameElement = driver.findElement(By.cssSelector("iframe[src='ex_switch_newFrame.html']"));

        driver.switchTo().frame(iFrameElement);
        System.out.println("IFrame text is: " + driver.findElement(By.id("iframe_container")).getText());

        driver.switchTo().defaultContent();
        assertEquals(driver.findElement(By.id("title")).getText(),mainTitle);
    }

    @Test
    public void Test5_HandlingTabs()
    {
        // -------- Move to new Tab and Return to Old Tab  -------
        String oldTab = driver.getWindowHandle();  // considering that there is only one tab opened in that point.
        driver.findElement(By.id("btnNewTab")).click();

        // Option 1 - Cast to ArrayList
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));  // change focus to new tab
        System.out.println("Tab text is: " + driver.findElement(By.id("new_tab_container")).getText());
        output = "This is a new tab";
        assertEquals(driver.findElement(By.id("new_tab_container")).getText(),output);

        driver.close();
        driver.switchTo().window(oldTab);
        assertEquals(driver.findElement(By.id("title")).getText(),mainTitle);
    }


    @Test
    public void Test6_HandlingWindows()
    {
        // -------- Move to new Window and Return to Old Window  -------
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.cssSelector("a[href='ex_switch_newWindow.html']")).click();

        // Option 2 - Use for each loop
        for(String winHandle : driver.getWindowHandles())  //Switch to new window opened
        {
            driver.switchTo().window(winHandle);
        }

        System.out.println("Tab text is: " + driver.findElement(By.id("new_window_container")).getText());
        output = "This is a new window";
        assertEquals(driver.findElement(By.id("new_window_container")).getText(),output);

        driver.close();
        driver.switchTo().window(winHandleBefore);
        assertEquals(driver.findElement(By.id("title")).getText(),mainTitle);
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}

