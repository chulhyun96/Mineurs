package com.newlecmineursprj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

import java.util.Properties;

@SpringBootApplication
public class NewlecMineursPrjApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(NewlecMineursPrjApplication.class, args);
    }
}

