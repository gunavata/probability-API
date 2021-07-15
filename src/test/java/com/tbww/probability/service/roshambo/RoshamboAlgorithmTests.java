package com.tbww.probability.service.roshambo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tbww.probability.model.roshambo.RoshamboEnum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tbww.probability.model.roshambo.RoshamboEnum.*;

@SpringBootTest
class RoshamboAlgorithmTests {

    @Autowired
    RoshamboAlgorithm algorithm;

    @Test
    @DisplayName("When getAiResponse, return a RoshamboEnum")
    void getAiResponse() {
        var response = algorithm.getAIResponse();

        assertEquals(RoshamboEnum.class, response.getClass());
    }

    @Test
    @DisplayName("When cheating against PAPER, return SCISSOR")
    void getAiCheatPaper() {
        var response = algorithm.getAICheat(PAPER);

        assertEquals(SCISSOR, response);
    }

    @Test
    @DisplayName("When cheating against ROCK, return PAPER")
    void getAiCheatRock() {
        var response = algorithm.getAICheat(ROCK);

        assertEquals(PAPER, response);
    }

    @Test
    @DisplayName("When cheating against SCISSOR, return ROCK")
    void getAiCheatScissor() {
        var response = algorithm.getAICheat(SCISSOR);

        assertEquals(ROCK, response);
    }

    // lol how do i write a test to cater for the default switch statement?
    // @Test
    // @DisplayName("When cheating with invalid value, throw exception")
    // void getAiCheatFuckedup() {
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         algorithm.getAICheat(null);
    //     });
    // }
    
}
