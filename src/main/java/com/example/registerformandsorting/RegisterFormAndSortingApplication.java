package com.example.registerformandsorting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterFormAndSortingApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false"); //JOptionPane çalışabilmesi için.
        SpringApplication.run(RegisterFormAndSortingApplication.class, args);
    }

}
