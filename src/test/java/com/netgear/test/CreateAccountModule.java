package com.netgear.test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateAccountModule {
    WebDriver driver;
    public String email;
    public String fName;
    public String lName;
    public String pWord;
    
    
    public CreateAccountModule(WebDriver driver, String email, String fn, String ln, String pW)
    {
        this.email = email;
        this.fName = fn;
        this.lName = ln;
        this.pWord = pW;
        System.out.println("Account created with " +email+ "and Password " +pW);

    }

    public String getemail() {
        return email;
    }
    public String getpassword()
    {
        return pWord;
    }

    public String getFirstName()
    {
        return fName;
    }

   // WebDriver driver;

    
   public void hoveraccounts(WebDriver driver) throws InterruptedException
   {
    Actions ac = new Actions(driver);
    WebElement accounts = driver.findElement(By.xpath("//div[@id='myAccountLogOut']"));
    ac.moveToElement(accounts).perform();
    WebElement createAccount = driver.findElement(By.xpath("//a[@id='register-redirect']"));
    createAccount.click();
    Thread.sleep(3000);
   } 
   
   public void createAccount(WebDriver driver) throws InterruptedException
   {
    WebElement emailAddress = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
    emailAddress.click();
    emailAddress.clear();
    emailAddress.sendKeys(this.email);
    Thread.sleep(2000);

   // WebElement confirmEmailAddress = driver.findElement(By.xpath("//input[@id='cnfEmail']"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement confirmEmailAddress = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='cnfEmail']")));
    //Actions ac = new Actions(driver);
    //ac.moveToElement(confirmEmailAddress);
    //confirmEmailAddress.click();
    //confirmEmailAddress.clear();
    confirmEmailAddress.sendKeys(this.email);
    Thread.sleep(2000);

   // WebElement fName = driver.findElement(By.xpath("//mat-label[@id='lbl_firstName']"));
    WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ip_firstName']")));
    
    System.out.println("**********I am here ***********");
    if (firstName.isEnabled()) {
        System.out.println("**********Inside if ***********");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstName);
    Thread.sleep(2000);
        firstName.click();
       // ((JavascriptExecutor) driver).executeScript("arguments[0].value='fname';", firstName);
        firstName.sendKeys(this.fName);
    } else {
        System.out.println("Element is disabled.");
    }
    
    
    
    

    //WebElement lName = driver.findElement(By.xpath("//mat-label[@id='lbl_lastName']"));
    WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ip_lastName']")));

    //lName.click();
    //lName.clear();
    lastName.sendKeys(this.lName);
    Thread.sleep(2000);

    WebElement passWord = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ip_pwdSignup']")));
    passWord.click();
    passWord.sendKeys(this.pWord);

    WebElement confirmPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ip_cnfPwd']")));
    confirmPassword.click();
    confirmPassword.sendKeys(this.pWord);

    //WebElement checkBox1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='mat-mdc-checkbox-2-input']")));
    WebElement checkBox2 = driver.findElement(By.xpath("//input[@id='mat-mdc-checkbox-1-input']"));
    //if (!checkBox1.isSelected()) 
    //{
        // If it's checked, click to check it
      //  checkBox1.click();
   // }
    //else
    //{
       // System.out.println("Checkbox is already checked");
   // }
    if (!checkBox2.isSelected()) 
    {
        // If it's not checked, click to check it
        checkBox2.click();
    }
    else
    {
        System.out.println("Checkbox is already checked");
    }


    WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@form='signupForm']")));
    submitButton.click();

    System.out.println("******************Form submitted successfully**************");
   }
   
    
    

    

    
}
