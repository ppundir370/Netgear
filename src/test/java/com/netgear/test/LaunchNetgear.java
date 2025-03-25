package com.netgear.test;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
//import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchNetgear 
{
    WebDriver driver;
    WebDriverWait wait;
    // Method to generate a random email
    public static String generateRandomEmail() {
        // Define a string of possible characters for the username part of the email
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        // Random object for generating random values
        Random random = new Random();
        
        // Length of the username part of the email
        int usernameLength = 5;  // You can change this to any length
        
        // StringBuilder to hold the random username
        StringBuilder username = new StringBuilder();
        
        // Generate a random username
        for (int i = 0; i < usernameLength; i++) {
            int index = random.nextInt(characters.length());
            username.append(characters.charAt(index));
        }
        
        // Define the domain name
        String domain = "@yopmail.com";  // You can change this to any other domain
        
        // Combine the username and domain to form the complete email address
        return username.toString() + domain;
    }



    
   
   // public static void main(String[] args) throws InterruptedException
   //@Test
     public void testNetgear() throws InterruptedException
     {
        String randomEmail = generateRandomEmail();
        WebDriver driver = WebDriverManager.chromedriver().create();
        //driver = WebDriverManager.edgedriver().create();
        driver.get("https://www.netgear.com");
        driver.getTitle();
        driver.manage().window().maximize();
        System.out.println("The title of the page : " + driver.getTitle());
        System.out.println("The current URL is : " + driver.getCurrentUrl());
        Thread.sleep(2000);
        System.out.println("****************** Code executed before Javascript executor ***************");
        WebElement shadowhost = driver.findElement(By.id("usercentrics-root"));
         SearchContext sc = shadowhost.getShadowRoot();
         WebElement acceptAll = sc.findElement(By.cssSelector("[data-testid='uc-accept-all-button']"));
         acceptAll.click();
        System.out.println("****************** Code executed after Javascript executor ***************");
        Thread.sleep(2000);

        CreateAccountModule lg = new CreateAccountModule(driver, randomEmail, "Priyanka", "Pundir", "Test@123");
        lg.hoveraccounts(driver);
        lg.createAccount(driver);
        LoginModule login = new LoginModule();  
        login.Login(driver, lg);
        Thread.sleep(2000);
        HomeSolutions homepage = new HomeSolutions();
        homepage.homeSolutionsPageVerification(driver, lg);
        homepage.homeSolutionsMenuItem(driver);
        homepage.filtersVerification(driver,"BRAND");

        System.out.println("*********Back on main pag***********");
         driver.close();
         
   
    }

    public void products() throws InterruptedException
    {
        ExtractProduct ep = new ExtractProduct();
        ep.listOfProducts();
    }
}
