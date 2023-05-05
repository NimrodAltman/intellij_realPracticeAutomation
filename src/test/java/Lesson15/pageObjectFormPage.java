package Lesson15;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class pageObjectFormPage {

    @FindBy(id = "occupation")
    public WebElement txt_Occupation;

    @FindBy(id = "age")
    public WebElement txt_age;

    @FindBy(id = "location")
    public WebElement txt_location;

    @FindBy(how = How.CSS, using = "input[value='Click Me']")
    public WebElement btn_clickMe;
//
    public void formPageAction(String occupationValue){
        Assert.assertTrue(btn_clickMe.isDisplayed());
        txt_Occupation.sendKeys(occupationValue);
        txt_age.sendKeys("35");
        txt_location.sendKeys("bat yam");
        btn_clickMe.click();
    }
}
