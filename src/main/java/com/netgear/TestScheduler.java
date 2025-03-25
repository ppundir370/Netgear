package com.netgear;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduler {

    public static void main(String args[])
    {
        //Define the thread pool with Schedules exector, Only 1 thread is sufficient to run the program.
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        Runnable run = () -> System.out.println("The program runs every 3 seconds" + System.currentTimeMillis());
        s.schedule(run, 3, TimeUnit.SECONDS);

        

        
    }
    
}
