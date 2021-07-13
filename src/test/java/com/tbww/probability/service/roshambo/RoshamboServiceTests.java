package com.tbww.probability.service.roshambo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.tbww.probability.model.roshambo.RoshamboEnum.*;
import static com.tbww.probability.model.ActivityResult.*;

@SpringBootTest
public class RoshamboServiceTests {
    
    @Autowired
    RoshamboService service;

    @MockBean
    private RoshamboAlgorithm algorithm;

    @BeforeEach
    public void setup() {
        when(algorithm.getAIResponse()).thenReturn(ROCK);
        when(algorithm.getAICheat(ROCK)).thenReturn(PAPER);
        when(algorithm.getAICheat(SCISSOR)).thenReturn(ROCK);
        when(algorithm.getAICheat(PAPER)).thenReturn(SCISSOR);
    }

    @Test
    @DisplayName("When computing, return valid Response")
    void computeValid() {
        

        var response = service.compute(ROCK, 0);

        var expectedResponse = Response.builder()
                                        .result("You choose ROCK against the AI's ROCK")
                                        .message("Nice Try! You Tied!")
                                        .state(TIE)
                                        .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When computing a cheat, return valid losing Response")
    void computeValidCheat() {
        var response = service.compute(ROCK, 2);

        var expectedResponse = Response.builder()
                                        .result("You choose ROCK against the AI's PAPER")
                                        .message("Too bad! You Lost!")
                                        .state(LOSE)
                                        .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When computing with invalid level, throw exception")
    void computeInvalidLevel() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.compute(ROCK, 69);
        });
    }

    @Test
    @DisplayName("When comparing PAPER to ROCK, return WIN")
    void comparePaperRock() {
        var response = service.compareActivity(PAPER, ROCK);

        assertEquals(WIN, response);
    }

    @Test
    @DisplayName("When comparing ROCK to ROCK, return TIE")
    void compareRockRock() {
        var response = service.compareActivity(ROCK, ROCK);

        assertEquals(TIE, response);
    }

    @Test
    @DisplayName("When comparing SCISSOR to ROCK, return LOSE")
    void compareScissorRock() {
        var response = service.compareActivity(SCISSOR, ROCK);

        assertEquals(LOSE, response);
    }

    @Test
    @DisplayName("When generating result, expect a formatted string")
    void generateResult() {
        var response = service.generateResult(ROCK, SCISSOR);

        String expectedResponse = "You choose ROCK against the AI's SCISSOR";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a WIN, expect a WIN message")
    void generateWinMessage() {
        var response = service.generateMessage(WIN);

        String expectedResponse = "Congratulations! You Won!";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a LOSE, expect a LOSE message")
    void generateLoseMessage() {
        var response = service.generateMessage(LOSE);

        String expectedResponse = "Too bad! You Lost!";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When generating a TIE, expect a TIE message")
    void generateTieMessage() {
        var response = service.generateMessage(TIE);

        String expectedResponse = "Nice Try! You Tied!";

        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("When getting response, expect a valid response")
    void getResponse() {
        String result = "Result is here";
        String message = "Message is here";
        ActivityResult state = TIE;
        var response = service.getResponse(result, message, state);

        var expectedResponse = Response.builder()
                                        .result(result)
                                        .message(message)
                                        .state(TIE)
                                        .build();

        assertEquals(expectedResponse, response);
    }

}
