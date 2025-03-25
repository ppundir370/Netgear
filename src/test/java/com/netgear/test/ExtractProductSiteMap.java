package com.netgear.test;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtractProductSiteMap extends ExtentReport {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions action;
    static HashMap<Integer, String> map = new HashMap<>();

     @BeforeMethod
    public void verifytest(Method method) {
        setupTest(method);
    }

    @Test
    public void linksExtraction() throws InterruptedException
    {
        setupDriver();
        navigateToSupportPage();
        extractSiteMapLinks();
        verifyFirmwareAndDownloads();
        quitDriver();
    }
    
    
        // ✅ Setup WebDriver
        public static void setupDriver() 
        {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        action = new Actions(driver);
       }

    // ✅ Navigate to Netgear Support sitemap page
    public static void navigateToSupportPage() {
        driver.get("http://stagesupport.netgear.com/support/supportsitemap_hreflang.xml");
    }


    
    public void extractSiteMapLinks() throws InterruptedException
    {
        Thread.sleep(2000);
        List<WebElement> links = driver.findElements(By.xpath("//div[@class='ms-4']/div[2]/div/span[@class='xmlTag-color']"));
        // Storing data in HashMap
        System.out.println("Size of the link is: "+ links.size());
        for (int i = 0; i < links.size(); i++) {
            if(links.get(i).getText().contains("/product"))
            {
                map.put(i + 1, links.get(i).getText());  // Index starts from 1

            }
            
        }

        // Print the HashMap
        System.out.println(map);

    }

    public static void verifyFirmwareAndDownloads() throws InterruptedException {
        // List<String[]> extractedLinks = new ArrayList<>(); // String array list
        // List<String> CurrentVersionText = new ArrayList<>();
        for (String link : map.values()) {
            Thread.sleep(2000);
            test.get().log(Status.PASS, "Link launched in browser");
            if (!isLinkAlive(link)) {
                test.get().log(Status.FAIL, "Link is dead");
                continue;
            }

            driver.get(link);

            Thread.sleep(3000);
            // Check if the expected element exists before waiting for its visibility
            List<WebElement> elements = driver.findElements(
                    By.xpath("//a[@class='btn hide-until-pick-a-topic-load my-1 btn-primary btn-sm mx-2']"));

            if (!elements.isEmpty()) {
                WebElement firmwareAndDoElement = elements.get(0);

                // Now wait for visibility before interacting
                wait.until(ExpectedConditions.visibilityOf(firmwareAndDoElement));
           
           // WebElement firmwareAndDoElement = driver.findElement(
              //      By.xpath("//a[@class='btn hide-until-pick-a-topic-load my-1 btn-primary btn-sm mx-2']"));
           // wait.until(ExpectedConditions.visibilityOf(firmwareAndDoElement));
            if (firmwareAndDoElement.isDisplayed()) 
            {
                try {
                    test.get().log(Status.PASS, "Download button visible on page");

                    firmwareAndDoElement.click();
                    //
                    // wait.until(ExpectedConditions.visibilityOf(currentVersionTexElement));
                    WebElement currentVersionTexElement = driver.findElement(
                            By.xpath("//h5[@class='border-bottom pb-2 mb-2' and text() = 'Current Versions']"));

                    action.scrollToElement(currentVersionTexElement).build().perform();
                    currentVersionTexElement.getText();
                    test.get().log(Status.PASS,
                            "Text is :  " + currentVersionTexElement.getText());

                    if (currentVersionTexElement.isDisplayed()) {
                        try {
                            // test.get().log(Status.PASS, "Current Version text verified on page");
                            // Below represents firmware text
                            List<WebElement> insideElements = driver.findElements(By.xpath(
                                    "//div[@id='downloadDocsAccordian']/div/h2/a[@class='accordion-button collapsed bg-light']"));
                            insideElements.size();
                            System.out.println("The link is*********** :" + link);
                            System.out.println("The elements text size under current version of above link********** : "
                                    + insideElements.size());
                            // Download Links inside firmware text
                            // List<WebElement> downloadElements = driver.findElements(
                            // By.xpath("//div[@id='downloadDocsAccordian']/div/h2/following-sibling::div/div/a"));
                            // System.out.println("The links inside each Element" +
                            // downloadElements.size());

                            // Iterate through each inside element
                            // Iterate through each inside element
                            // for (int i = 0; i < insideElements.size(); i++) {
                            // Get and print the text of the current element
                            // String text = insideElements.get(i).getText();
                            // System.out.println("The text is: " + text);

                            // Click on the element to update the page content
                            // insideElements.get(i).click();

                            // Wait for the new elements to load (Add explicit wait if necessary)
                            Thread.sleep(2000); // Replace with WebDriverWait if possible

                            // Fetch the updated list of download elements AFTER the click
                            List<WebElement> downloadElements = driver.findElements(
                                    By.xpath("//div[@id='downloadDocsAccordian']/div/h2/following-sibling::div/div/a"));
                            System.out.println("Total links are " + downloadElements.size());
                            test.get().log(Status.PASS, "Total links are:********** " + downloadElements.size());
                            // Print each link immediately after the text
                            for (WebElement link1 : downloadElements) {
                                String href = link1.getAttribute("href");
                                System.out.println("Link is:*********** " + href);
                                test.get().log(Status.PASS, "Link is:********** " + href);
                            }
                            // }

                            // for (WebElement firmwaretexElement : insideElements)
                            // {
                            // String text = firmwaretexElement.getText();
                            // System.out.println(text);
                            // // test.get().log(Status.PASS, " " + text);

                            // action.scrollToElement(firmwaretexElement).build().perform();
                            // firmwaretexElement.click();
                            // try{
                            // for(WebElement firwareDownloadLinkElement : downloadElements)
                            // {
                            // action.scrollToElement(firwareDownloadLinkElement).build().perform();
                            // String href = firwareDownloadLinkElement.getAttribute("href");
                            // System.out.println("links inside are " + href);
                            // // test.get().log(Status.PASS, "URL " +
                            // "https://stagesupport.netgear.com/support/product/be17000/");
                            // // test.get().log(Status.PASS, "Download and Release links verified on the
                            // page "+firwareDownloadLinkElement.getText() +" ::"+ href);

                            // // System.out.println("The download links and release links are : "
                            // +singleDownloadElement.getText() +" ::"+ href) ;
                            // }
                            // }
                            // catch(Exception e)
                            // {
                            // test.get().log(Status.FAIL, "Unable to traverse Firmware download links on
                            // click" + "https://stagesupport.netgear.com/support/product/be17000/");
                            // }

                            // // System.out.println("The element are as follows : " + text);

                            // // String href = insideElements.ge;
                            // // System.out.println("Link extracted from current version section :");

                            // //extractedLinks.add(new String[]{text, text}); //Store in list
                            // }
                        } catch (Exception e) {
                            test.get().log(Status.FAIL, "Current Version if condition failed");

                        }
                    }
                } catch (Exception e) {
                    test.get().log(Status.INFO, "Download button not visible  " + link );
                }

            } else {
                test.get().log(Status.PASS, "No Firmware and download link present on the page :  " + link);
                // System.out.println("No Firmware and download link present on the page :");
            }
        }
        else
        {
            test.get().log(Status.INFO, "Expected element not found on page, skipping. "+"Link is " + link);
        }

        }

    }

    public static boolean isLinkAlive(String urlString) {
        try {
            URL url = URI.create(urlString).toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            return responseCode >= 200 && responseCode < 400; // Only return true for 2xx and 3xx responses
        } catch (Exception e) {
            return false; // Link is dead
        }
    }

    



    public static void quitDriver()
    {
        driver.quit();
    }
    
    
}