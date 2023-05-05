package Lesson15;

import org.testng.annotations.DataProvider;

public class dataProviderSeperateClass {

    @DataProvider(name = "DataProviderArray")
    public Object[][] dataProviderArray(){
        return new Object[][]{
                {"Selenium","Selenium"},
                {"England","England"},
                {"Kuku","Kuku"}
        };
    }
}
