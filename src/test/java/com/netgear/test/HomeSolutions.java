package com.netgear.test;
import java.time.Duration;
//import java.time.temporal.TemporalAmount;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;

public class HomeSolutions {


    WebDriver driver;
    WebDriverWait wait;
    
    
    public void homeSolutionsPageVerification(WebDriver driver, CreateAccountModule ca) throws InterruptedException
    {
        driver.getCurrentUrl();
        System.out.println("*******Current URL After login ********* " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        Actions action = new Actions(driver);
        WebElement accounts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='account']//parent::div[@class='dropdown profile']//parent::div[@id='myAccountLogin']")));
        action.moveToElement(accounts).build().perform();
        WebElement loggedinText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Welcome back Priyanka!' and @class='welcome s-15']")));
        System.out.println("*******Logged in text " + loggedinText.getText() + "************");
        System.out.println("******* logged in text " + loggedinText.toString() + "************");

        Thread.sleep(2000);
        if(loggedinText.isDisplayed() && loggedinText.toString().contains(ca.fName))
        {  
           System.out.println(" ******************User is logged in successfully***************** ");
        }
        else
        {
            System.out.println("User is not logged in ");
        }

    }
    
    
    public void homeSolutionsMenuItem(WebDriver driver)
    {
        Actions action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement homeSolutionsMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='nav-link nav-menu']//parent::li[@id='HOME SOLUTIONS']")));
        action.moveToElement(homeSolutionsMenu).build().perform();
        System.out.println("**********Hover over home solutions*********");
        WebElement homeWifi = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-gtm-menu = 'HOME SOLUTIONS > Home WiFi > Whole Home Mesh WiFi']//parent::li")));
        action.moveToElement(homeWifi).build().perform();
        homeWifi.click();
        WebElement breadcrumbElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".breadcrumb-wrapper .breadcrumb-item")));
        WebElement homeWifiWebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".breadcrumb-wrapper a, .breadcrumb-wrapper a:hover")));
        String homeWifiColor = homeWifiWebElement.getCssValue("color");
        String fontColor = breadcrumbElement.getCssValue("font-size");
        String fontFamily = breadcrumbElement.getCssValue("font-family");
        String lineHeight = breadcrumbElement.getCssValue("line-height");
        String color = breadcrumbElement.getCssValue("color");
        System.out.println("Breadcrumb color is " + fontColor);
        System.out.println("Breadcrumb color is " + fontFamily);
        System.out.println("Breadcrumb color is " + lineHeight);
        System.out.println("Breadcrumb color is " + color);
        System.out.println("Color of Home/Wifi " + homeWifiColor);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol[@class='breadcrumb']")));
        if(fontColor.equals("16px"))
        {
            System.out.println("Font size is expected");
        }
        else
        {
            System.out.println("Font size is different"); 

        }

        if(fontFamily.equals("NunitoSans-Light"))
        {
            System.out.println("Font family is expected");
        }
        else
        {
            System.out.println("Font family is not expected");
        }
        if(lineHeight.equals("21px"))
        {
            System.out.println("Line height is expected");
        }
        else
        {
            System.out.println("Line height is not expected");
        }
        if(homeWifiColor.contains("rgba(0,0,0)"))
        {
            System.out.println("Home Wifi color is expected");
        }
        else
        {
            System.out.println("Home Wifi color is not expected");
        }


        
        }
        
        public void filtersVerification(WebDriver driver, String filterLabel)
        {
            int i = 1;
            List<WebElement> filterElements = driver.findElements(By.xpath("//span[@class='filter-drop-text text-uppercase focus-box']"));
            for(WebElement filterValue : filterElements )
            {
                System.out.println("******Filter value is " + filterValue.getText() + " and" + "Filter label is*********" + filterLabel);

                if(filterValue.getText().equalsIgnoreCase(filterLabel))
                {
                    
                    wait.until(ExpectedConditions.elementToBeClickable(filterValue));
                    filterValue.click();

                    List<WebElement> checkboxElements = filterValue.findElements((By.xpath(".//following-sibling::div//ul//li//div//label")));
                    for(WebElement checkBoxValue : checkboxElements)
                    {
                       // (//span[@class='listfilter-label'])//parent::span//following-sibling::div//ul//li//div//label
                        
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        Boolean textPresent = wait.until(ExpectedConditions.textToBePresentInElement(checkBoxValue, checkBoxValue.getText()));
                        System.out.println("Text present inside the element " + textPresent);
                        if(textPresent)
                        {
                            System.out.println("Checkbox values under " + filterValue.getText() +" are : " + i + checkBoxValue.getText());
                            i++;
                        }
                        else
                        {
                            System.out.println("Not present");
                        }
                       
                    }
                }
                
                


            }











           // List<WebElement> filterElements = driver.findElements(By.xpath("//span[@class='listfilter-label']"));
           // for(int i = 0; i < filterElements.size(); i++)
            //{
             
                //System.out.println("Filter elements are : " + filterElements.get(i).getText());
                //if(filterElements.get(i).getText().equalsIgnoreCase(filterLabel))
                //{
                   // for(WebElement we : filterElements)
                   // {
                 // WebElement checkBoxElement = we.findElement(By.xpath("(//span[@class='listfilter-label'])//parent::span//following-sibling::div//ul//li//div//label[@class='checkbox-text' and @for]/text()"));
                    
                   // String checkBoxValue = checkBoxElement.getText();
                  
                        
                        //System.out.println("Check Box text names of all the Labels " + checkBoxValue);
                
                  //  }
                //}
               // else      
                //{
                   // System.out.println("Label is not expected");

               // }
            
              
          //  }
            
        }
    
}
