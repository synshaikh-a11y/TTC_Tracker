package com.transit.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransitTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransitTrackerApplication.class, args);

        System.out.println("🚌 TTC Transit Tracker is running!");
        System.out.println("📍 Open: http://localhost:8080");
      
    }
}