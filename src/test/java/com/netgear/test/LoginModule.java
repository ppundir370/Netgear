package com.netgear.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginModule {

    WebDriver driver;
    
    public void SignIn(WebDriver driver) throws InterruptedException
    {
        Actions ac = new Actions(driver);
        WebElement accounts = driver.findElement(By.xpath("//a[@id='account']/parent::div/parent::div[@id='myAccountLogOut']"));
        ac.moveToElement(accounts).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement logiWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sign In']")));
        logiWebElement.click();
        System.out.println("*************Clicked sign in button successfully**************");
        Thread.sleep(3000);
        

    }
    
    
    public void Login(WebDriver driver, CreateAccountModule cAccountModule)
    {
        String email = cAccountModule.getemail();
        String password = cAccountModule.getpassword();
        System.out.println("Enter login module");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions move = new Actions(driver);
        WebElement emaiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mat-input-7']")));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mat-input-8']")));
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='Login-btn']")));

        if(emaiElement.toString().contains("@") && passwordElement.toString().contains("@"))
        {
        move.moveToElement(submitButton).build().perform();
        submitButton.click();
        }
        if(emaiElement.toString().contains("@") )
        {
        move.moveToElement(passwordElement).build().perform();
        passwordElement.click();
        passwordElement.sendKeys(password);
        move.moveToElement(submitButton).build().perform();
        submitButton.click();
        System.out.println("********user logged in successfully");
        }
        if(emaiElement.toString().isEmpty())
        {
         move.moveToElement(emaiElement).build().perform();
         emaiElement.sendKeys(email);
         move.moveToElement(passwordElement).build().perform();
         passwordElement.sendKeys(password);

         
        }

        

    }
    
}
