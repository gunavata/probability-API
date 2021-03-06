package com.tbww.probability.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Help;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.roshambo.RoshamboEnum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RoshamboControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

    String expectedName = "Rock, Paper, Scissor";
    String expectedUsage = "Specify choice with optional level";

    @Test
    @DisplayName("When GET /rps/, roshambo help is returned")
    void getDefaultEndpoint() {
        var response = restTemplate.getForObject("/rps/", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }

    @Test
    @DisplayName("When GET /rps/help, roshambo help is returned")
    void getHelpEndpoint() {
        var response = restTemplate.getForObject("/rps/help", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }

    @Test
    @DisplayName("When GET /rps/play with missing params, return bad request")
    void getWithMissingParams() {
        var response = restTemplate.exchange("/rps/play", HttpMethod.GET, entity, Response.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("When GET /rps/play with invalid params, return bad request")
    void getWithBadParams() {
        String validUrl = UriComponentsBuilder.fromPath("/rps/play")
                        .queryParam("choice", "DOG")
                        .queryParam("level", "69")
                        .toUriString();

        var response = restTemplate.exchange(validUrl, HttpMethod.GET, entity, Response.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("When GET /rps/play with required params, return valid result")
    void getWithRequiredParams() {
        String choice = "PAPER";
        String validUrl = UriComponentsBuilder.fromPath("/rps/play")
                        .queryParam("choice", choice)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedResult = String.format("You choose %s against the AI's", choice);

        assertTrue(response.getResult().contains(expectedResult));
    }

    @Test
    @DisplayName("When GET /rps/play with all params, return valid result")
    void getWithAllParams() {
        String choice = "SCISSOR";
        String validUrl = UriComponentsBuilder.fromPath("/rps/play")
                        .queryParam("choice", choice)
                        .queryParam("level", 1)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedResult = String.format("You choose %s against the AI's", choice);

        assertTrue(response.getResult().contains(expectedResult));
    }

    @Test
    @DisplayName("When GET /rps/play with cheat, return a loss result")
    void getWithCheatingParams() {
        String choice = "ROCK";
        String validUrl = UriComponentsBuilder.fromPath("/rps/play")
                        .queryParam("choice", choice)
                        .queryParam("level", 2)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedMessage = "Too bad! You Lost!";

        assertEquals(expectedMessage, response.getMessage());
        assertEquals(ActivityResult.LOSE, response.getState());
    }

    @Test
    @DisplayName("When GET /rps/playone, return a random choice") 
    void getPlayOne() {
        var response = restTemplate.getForObject("/rps/playone", RoshamboEnum.class);

        assertSame(response.getClass(), RoshamboEnum.class);
    }
    
}
