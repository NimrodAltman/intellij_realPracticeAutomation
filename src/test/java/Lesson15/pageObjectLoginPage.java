package Lesson15;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageObjectLoginPage {

    @FindBy(how = How.CSS, using = "input[name='username2']")
    public WebElement txt_userName;

    @FindBy(how = How.CSS, using = "input[name='password2']")
    public WebElement txt_password;

    @FindBy(id= "submit")
    public WebElement btn_submit;
//
    public void loginPageAction(String userNameValue){
        txt_userName.sendKeys(userNameValue);
        txt_password.sendKeys("webdriver");
        btn_submit.click();
    }
}
