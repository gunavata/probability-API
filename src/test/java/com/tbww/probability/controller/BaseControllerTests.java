package com.tbww.probability.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import com.tbww.probability.model.Help;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    String expectedName = "Probability Games";
    String expectedUsage = "Append param choice onto API and request /help for more info";

    @Test
    @DisplayName("When GET /, a help infographic is returned")
    void getDefaultEndpoint() {
        var response = restTemplate.getForObject("/", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }

    @Test
    @DisplayName("When GET /help, a help infographic is returned")
    void getHelpEndpoint() {
        var response = restTemplate.getForObject("/help", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }
    
}
