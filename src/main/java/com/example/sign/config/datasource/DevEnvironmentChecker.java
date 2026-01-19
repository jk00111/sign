package com.example.sign.config.datasource;

import org.springframework.stereotype.Component;

@Component
public class DevEnvironmentChecker implements EnvironmentChecker {

    @Override
    public boolean check() {
        String profile = System.getProperty("spring.profiles.active", "");
        return "dev".equals(profile) || "local".equals(profile);
    }
}
