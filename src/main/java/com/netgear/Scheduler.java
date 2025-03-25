package com.netgear;
import org.testng.TestNG;
import org.testng.annotations.Test;
//import com.netgear.BP;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

public class Scheduler {
    //public static void main(String[] args) {
      //  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

      //  Runnable task = () -> {
        //    System.out.println("Running Selenium TestNG tests...");
          //  TestNG testng = new TestNG();
            //testng.setTestClasses(new Class[]{com.netgear.BP.class});
            //testng.run();
        //};

        // Run TestNG tests every 2 minutes
        //scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.MINUTES);

        // Stop the scheduler after 30 minutes (for safety)
        //scheduler.schedule(() -> {
          //  System.out.println("Stopping Scheduler...");
            //scheduler.shutdown();
        //}, 30, TimeUnit.MINUTES);
    //}
}
