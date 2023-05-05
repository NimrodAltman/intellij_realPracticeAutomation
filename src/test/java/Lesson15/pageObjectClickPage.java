package Lesson15;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class pageObjectClickPage {

    @FindBy(how = How.CSS, using = "button[type='button']")
    public WebElement btn_clickMe;

    public void clickPageAction(){
        Assert.assertTrue(btn_clickMe.isDisplayed());
        btn_clickMe.click();
    }
}
