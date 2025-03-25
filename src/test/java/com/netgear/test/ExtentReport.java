package com.netgear.test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ExtentReport {
    public static ExtentReports extent;
    //public static ExtentTest test;
    public static ExtentSparkReporter sparkReporter;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeClass
    public void setupReport() {
        sparkReporter = new ExtentSparkReporter("Reports/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Priyanka");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }
    
    @BeforeMethod
    public void setupTest(Method method) {
        ExtentTest newTest = extent.createTest(method.getName()); // Create a test dynamically
        test.set(newTest); // Store test in ThreadLocal
    }

    @AfterClass
    public void tearDownReport() {
        extent.flush(); // Write everything to the report
    }
}

    

