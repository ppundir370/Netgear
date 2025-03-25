package com.netgear;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BP {

    WebDriver driver;
    WebDriverWait wait;

     @Test(priority = 1, dataProvider = "urlProvider")
    public void bp(String url) throws InterruptedException
    {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        BlankPage bp1 = new BlankPage();
        bp1.checkBlankPage(driver , url, wait);
        driver.quit();
        
    }
    @DataProvider(name = "urlProvider")
    public Object[][] Urls() 
    {
        return new Object[][] {
            {"https://www.netgear.com/"},
            {"http://prodapp2.netgear.com/"},
            {"http://prodapp3.netgear.com/"},
            {"http://prodapp4.netgear.com/"}

        };   
     }
    
    
}
