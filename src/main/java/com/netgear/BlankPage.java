
package com.netgear;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BlankPage {
    WebDriver driver;
    WebDriverWait wait ;

public void checkBlankPage(WebDriver driver, String url, WebDriverWait wait)
{   
    this.driver = driver;
    this.wait = wait;
    
    //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     driver.get(url);
    System.out.println(driver.getCurrentUrl());
   WebElement bodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
   if(bodyElement.isDisplayed())
   {
    System.out.println("No blank page issue");
   }
   else
   {
    System.out.println("Blank page issue");
   }
   Assert.assertFalse(driver.getTitle().trim().isEmpty(), "Blank page issue: " + url);

}


}
