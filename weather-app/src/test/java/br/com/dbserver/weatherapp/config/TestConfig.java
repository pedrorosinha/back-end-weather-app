package br.com.dbserver.weatherapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Value("${test.openweathermap.api.key}")
    private String testApiKey;

    @Value("${test.openweathermap.api.url}")
    private String testApiUrl;

    public String getTestApiKey() {
        return testApiKey;
    }

    public String getTestApiUrl() {
        return testApiUrl;
    }
}
