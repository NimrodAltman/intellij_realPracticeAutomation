package lesson14;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class actionsWithJSexecuter {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    JavascriptExecutor js;
    String droppableExpectedText = "Dropped!";

    @BeforeClass()
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        wait = new WebDriverWait(driver,7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action = new Actions(driver);
        js = (JavascriptExecutor)driver;
    }

    @AfterClass()
    public void endSession(){
        driver.quit();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test(priority = 0, description = "Action Test")
    @Description("test description: Action Testing")
    public void test01actions() throws InterruptedException {
        dragAndDropTest(droppableExpectedText);
        clickAndHoldTest();
        doubleClickTest();
        mouseOverTest();
        scrollingTest();
        rightClickTest();
    }

    @Step("Drag And Drop Test")
    public void dragAndDropTest(String dropExpectedText){
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        action.dragAndDrop(draggable,droppable).build().perform();
        String dropActualText = driver.findElement(By.id("droppable")).getText();
        try {
            Assert.assertEquals(dropActualText, dropExpectedText);
        }catch (AssertionError e) {
                saveScreenshot(driver);
                fail("Assert Failed. see details: " + e);
        }
    }


    @Step("Select Multiple Elements Test")
    public void clickAndHoldTest(){
        try {
            List<WebElement> itemList = driver.findElements(By.xpath("//*[@id='select_items']/li"));
            List<WebElement> itemList1 = driver.findElements(By.cssSelector("ol[id='select_items'][class='selectable ui-selectable']"));
            action.clickAndHold(itemList.get(1)).clickAndHold(itemList.get(2)).build().perform();
        //click().
            }catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert Failed. see details: " + e);
        }
    }

    @Step("double Click On Element Test")
    public void doubleClickTest(){
        WebElement dClickElement = driver.findElement(By.id("dbl_click"));
        action.doubleClick(dClickElement).build().perform();
        try {
            WebElement hiddenElement = driver.findElement(By.id("demo"));
            Assert.assertTrue(hiddenElement.isDisplayed());
        }catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert Failed. see details: " + e);
        }
    }

    @Step("mouse Over On Element Test")
    public void mouseOverTest(){
        WebElement mouseOverElement = driver.findElement(By.id("mouse_hover"));
        String bckgclr = driver.findElement(By.id("mouse_hover")).getCssValue("background-color");
        action.moveToElement(mouseOverElement).build().perform();
      //  Assert.assertTrue(driver.findElement(By.cssSelector("span[style='background-color: rgb(255, 255, 0);']")).isDisplayed());
        String bckgclrChange = driver.findElement(By.id("mouse_hover")).getCssValue("background-color");
        Assert.assertNotEquals(bckgclr,bckgclrChange);
    }

    @Step("scroll to element Test")
    public void scrollingTest(){
        WebElement scrollElement = driver.findElement(By.id("scrolled_element"));
        js.executeScript("arguments[0].scrollIntoView(true);",scrollElement);
        try {
            Assert.assertTrue(scrollElement.isDisplayed());
        }catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert Failed. see details: " + e);
        }
    }

    @Step("perform right click")
    public void rightClickTest() throws InterruptedException {
        WebElement performRightClick = driver.findElement(By.id("contact_area"));
        action.contextClick(performRightClick).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(5000);

    }




}
