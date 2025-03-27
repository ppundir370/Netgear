package com.netgear.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.dockerjava.api.model.Link;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtractProduct extends ExtentReport {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions action;
    static JSONArray productArray = new JSONArray();
    static List<String> allLinks = new ArrayList<>();

    @BeforeMethod
    public void verifytest(Method method) {
        setupTest(method);
    }

    @Test
    public void listOfProducts() throws InterruptedException {
        // public static void main(String[] args) throws InterruptedException {

        setupDriver();

        try {
            navigateToSupportPage();
            wifiRoutersCategory();
            verifyFirmwareAndDownloads();
            // currentVersion();
            // extractProductDetails();
            // saveProductsToJson();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }
    }

    // ✅ Setup WebDriver
    public static void setupDriver() {
        // chrome options added on 27-03-2025 to mimic jenkins headless behvaiour//
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
        options.addArguments("--window-size=1920,1080"); // Set window size
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource issues
        options.addArguments("--no-sandbox"); // Bypass OS security restrictions

        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        action = new Actions(driver);
        //// chrome options added on 27-03-2025 to mimic jenkins headless behvaiour//
    }

    // ✅ Navigate to Netgear Support page
    public static void navigateToSupportPage() {
        driver.get("https://stagesupport.netgear.com/support/");
    }

    public static void wifiRoutersCategory() throws InterruptedException {

        List<WebElement> allCategory = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[@class='category-grid']")));

        for (int i = 0; i < allCategory.size(); i++) {
            action.scrollToElement(allCategory.get(i)).build().perform();
            System.out.println("All category lists are : " + allCategory.get(i).getText());
        }
        WebElement wifiRoutersElement = driver
                .findElement(By.xpath("//a[@data-tab-value = '#product-category-0100-WiFiRouters']"));
        wifiRoutersElement.click();
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[@data-category-name = '0100-WiFi Routers']")));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ Re-fetch the sub-category list
        List<WebElement> subCategory = driver.findElements(By.xpath("//a[@data-category-name = '0100-WiFi Routers']"));

        // Store text in session storage
        for (WebElement currentSubCategory : subCategory) {

            // Scroll to the element
            Thread.sleep(3000);
            action.scrollToElement(currentSubCategory).build().perform();

            // Store text in session storage
            js.executeScript("sessionStorage.setItem(arguments[0], arguments[1]);", "subCategory" +
                    currentSubCategory.getText());
                    System.out.println("Current sub category text value : " + currentSubCategory.getText());

            String domElement = currentSubCategory.getAttribute("data-ref-id");
            takeScreenshot("Before Print");
            try {
                // added on 27-03-2025
                System.out.println("The dom Element is : " + domElement);
                // added on 27-03-2025
                takeScreenshot("After Print"); // Capture screenshot after clicking
            } catch (Exception e) {
                takeScreenshot("error"); // Capture screenshot on error
                e.printStackTrace();
            }

            takeScreenshot("SubCategory before click");
            // Wait for sub-category to be clickable & click it
            wait.until(ExpectedConditions.elementToBeClickable(currentSubCategory)).click();
            List<WebElement> insideLinks = driver.findElements(By.xpath(
                    "(//div[@class='col-xl-6'])[1]/div[contains(@id, '" + domElement + "')]//a"));
            takeScreenshot("SubCategory after click");

            Thread.sleep(2000);

            for (WebElement link : insideLinks) {
                takeScreenshot("Links of Each sub category");
                // System.out.println(link.getText());
                String href = link.getAttribute("href");
                if (href != null && !href.isEmpty()) {
                    allLinks.add(href);
                    // test.get().log(Status.PASS, "Size of the links captured " + allLinks.size());
                    // test.get().log(Status.PASS, "Traversed all the links" + href);
                    // System.out.println("All the inside links of the Products : " + href);
                }
            }
            // Thread.sleep(3000);
            // Take Screenshot before an action
            takeScreenshot("before_scroll");
            try {
                
                WebElement BreadCrumbWifiRouter = driver.findElement(By.xpath("//span[@id='first_column_title']"));
                wait.until(ExpectedConditions.visibilityOf(BreadCrumbWifiRouter));
                BreadCrumbWifiRouter.click();
                WebElement wifiRoutersElement1 = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(@data-tab-value , '#product-category-0100-WiFiRouters')]")));

                // scrollToElement(wifiRoutersElement);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                        wifiRoutersElement1);
                        takeScreenshot("After_Scroll");
                Thread.sleep(1000);
                takeScreenshot("Before_click");
                System.out.println("Text of wifi Router Element " + wifiRoutersElement1.getText());
                //wait.until(ExpectedConditions.visibilityOf(wifiRoutersElement1)).click();
                wifiRoutersElement1.click();
                takeScreenshot("After_click"); // Capture screenshot after clicking

                
            } catch (Exception e) {
                takeScreenshot("error"); // Capture screenshot on error
                e.printStackTrace();
            }

        }

    }

    public static void verifyFirmwareAndDownloads() throws InterruptedException {
        // List<String[]> extractedLinks = new ArrayList<>(); // String array list
        // List<String> CurrentVersionText = new ArrayList<>();
        for (String link : allLinks) {
            Thread.sleep(2000);
            test.get().log(Status.PASS, "Link launched in browser");
            if (!isLinkAlive(link)) {
                test.get().log(Status.FAIL, "Link is dead");
                continue;
            }

            driver.get(link);

            Thread.sleep(5000);
            // Check if the expected element exists before waiting for its visibility
            List<WebElement> elements = driver.findElements(
                    By.xpath("//a[@class='btn hide-until-pick-a-topic-load my-1 btn-primary btn-sm mx-2']"));

            if (!elements.isEmpty()) {
                WebElement firmwareAndDoElement = elements.get(0);

                // Now wait for visibility before interacting
                wait.until(ExpectedConditions.visibilityOf(firmwareAndDoElement));

                // WebElement firmwareAndDoElement = driver.findElement(
                // By.xpath("//a[@class='btn hide-until-pick-a-topic-load my-1 btn-primary
                // btn-sm mx-2']"));
                // wait.until(ExpectedConditions.visibilityOf(firmwareAndDoElement));
                if (firmwareAndDoElement.isDisplayed()) {
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
                                System.out.println(
                                        "The elements text size under current version of above link********** : "
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
                                        By.xpath(
                                                "//div[@id='downloadDocsAccordian']/div/h2/following-sibling::div/div/a"));
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
                        test.get().log(Status.INFO, "Download button not visible  " + link);
                    }

                } else {
                    test.get().log(Status.PASS, "No Firmware and download link present on the page :  " + link);
                    // System.out.println("No Firmware and download link present on the page :");
                }
            } else {
                test.get().log(Status.INFO, "Expected element not found on page, skipping. " + "Link is " + link);
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

    public static void takeScreenshot(String fileName) {
        try {
            // 1️⃣ Capture screenshot as a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 2️⃣ Define the destination path for saving the screenshot
            File destination = new File("target/screenshots/" + fileName + ".png");

            // 3️⃣ Copy the captured screenshot to the destination folder
            FileUtils.copyFile(screenshot, destination);

            System.out.println("📸 Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wifiRoutersSubCategories() {

    }

}
