package com.cas.casdemo.casservis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cas.casdemo")
public class CasServisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasServisApplication.class, args);
    }
}

