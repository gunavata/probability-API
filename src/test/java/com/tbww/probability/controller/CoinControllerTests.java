package com.tbww.probability.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Help;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.coin.CoinEnum;

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
class CoinControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

    String expectedName = "Coin Flip";
    String expectedUsage = "Specify choice with optional level";

    @Test
    @DisplayName("When GET /coin/, coin flip help is returned")
    void getDefaultEndpoint() {
        var response = restTemplate.getForObject("/coin/", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }

    @Test
    @DisplayName("When GET /coin/help, coin flip help is returned")
    void getHelpEndpoint() {
        var response = restTemplate.getForObject("/coin/help", Help.class);

        assertEquals(expectedName, response.getName());
        assertEquals(expectedUsage, response.getUsage());
    }

    @Test
    @DisplayName("When GET /coin/play with missing params, return bad request")
    void getWithMissingParams() {
        var response = restTemplate.exchange("/coin/play", HttpMethod.GET, entity, Response.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("When GET /coin/play with invalid params, return bad request")
    void getWithBadParams() {
        String validUrl = UriComponentsBuilder.fromPath("/coin/play")
                        .queryParam("choice", "CAT")
                        .queryParam("level", "420")
                        .toUriString();

        var response = restTemplate.exchange(validUrl, HttpMethod.GET, entity, Response.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("When GET /coin/play with required params, return valid result")
    void getWithRequiredParams() {
        String choice = "HEAD";
        String validUrl = UriComponentsBuilder.fromPath("/coin/play")
                        .queryParam("choice", choice)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedResult = String.format("You choose %s and the result was", choice);

        assertTrue(response.getResult().contains(expectedResult));
    }

    @Test
    @DisplayName("When GET /coin/play with all params, return valid result")
    void getWithAllParams() {
        String choice = "TAIL";
        String validUrl = UriComponentsBuilder.fromPath("/coin/play")
                        .queryParam("choice", choice)
                        .queryParam("level", 0)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedResult = String.format("You choose %s and the result was", choice);

        assertTrue(response.getResult().contains(expectedResult));
    }

    @Test
    @DisplayName("When GET /coin/play with cheat, return a loss result")
    void getWithCheatingParams() {
        String choice = "HEAD";
        String validUrl = UriComponentsBuilder.fromPath("/coin/play")
                        .queryParam("choice", choice)
                        .queryParam("level", 1)
                        .toUriString();

        var response = restTemplate.getForObject(validUrl, Response.class);

        String expectedMessage = "You got it wrong!";

        assertEquals(expectedMessage, response.getMessage());
        assertEquals(ActivityResult.LOSE, response.getState());
    }

    @Test
    @DisplayName("When GET /coin/playone, return a random choice")
    void getPlayOne() {
        var response = restTemplate.getForObject("/coin/playone", CoinEnum.class);

        assertSame(CoinEnum.class, response.getClass());
    }
    
}
