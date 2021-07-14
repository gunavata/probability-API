package com.tbww.probability.service.coin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.tbww.probability.model.ActivityResult.*;
import static com.tbww.probability.model.coin.CoinEnum.*;



@SpringBootTest
public class CoinServiceTests {

    @Autowired
    CoinService service;

    @MockBean
    CoinAlgorithm algorithm;

    @BeforeEach
    public void setup() {
        when(algorithm.getAIResponse()).thenReturn(HEAD);
        when(algorithm.getAICheat(HEAD)).thenReturn(TAIL);
        when(algorithm.getAICheat(TAIL)).thenReturn(HEAD);
    }

    @Test
    @DisplayName("When flipping, return valid Response")
    void computeValid() {
        var response = service.flip(HEAD, 0);

        var expectedResponse = Response.builder()
                                        .result("You choose HEAD and the result was HEAD")
                                        .message("You got it right!")
                                        .state(WIN)
                                        .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When flipping a cheat, return valid losing Response")
    void computeValidCheat() {
        var response = service.flip(HEAD, 1);

        var expectedResponse = Response.builder()
                                        .result("You choose HEAD and the result was TAIL")
                                        .message("You got it wrong!")
                                        .state(LOSE)
                                        .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When computing with invalid level, throw exception")
    void computeInvalidLevel() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.flip(TAIL, 69);
        });
    }

    @Test
    @DisplayName("When comparing HEAD to HEAD, return WIN")
    void compareHeadHead() {
        var response = service.compareActivity(HEAD, HEAD);

        assertEquals(WIN, response);
    }

    @Test
    @DisplayName("When comparing HEAD to TAIL, return LOSE")
    void compareHeadTail() {
        var response = service.compareActivity(HEAD, TAIL);

        assertEquals(LOSE, response);
    }

    @Test
    @DisplayName("When generating result, expect a formatted string")
    void generateResult() {
        var response = service.generateResult(TAIL, HEAD);

        String expectedResponse = "You choose TAIL and the result was HEAD";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a WIN, expect a WIN message")
    void generateWinMessage() {
        var response = service.generateMessage(WIN);

        String expectedResponse = "You got it right!";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a LOSE, expect a LOSE message")
    void generateLoseMessage() {
        var response = service.generateMessage(LOSE);

        String expectedResponse = "You got it wrong!";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a TIE, throw an exception")
    void generateTieMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.generateMessage(TIE);
        }, "TIE is not a valid option!");
    }

    @Test
    @DisplayName("When getting response, expect a valid response")
    void getResponse() {
        String result = "Result is here";
        String message = "Message is here";
        ActivityResult state = WIN;
        var response = service.getResponse(result, message, state);

        var expectedResponse = Response.builder()
                                        .result(result)
                                        .message(message)
                                        .state(TIE)
                                        .build();

        assertEquals(expectedResponse, response);
    }
    
}
