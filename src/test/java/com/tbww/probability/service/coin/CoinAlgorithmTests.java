package com.tbww.probability.service.coin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tbww.probability.model.coin.CoinEnum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tbww.probability.model.coin.CoinEnum.*;

@SpringBootTest
class CoinAlgorithmTests {

    @Autowired
    CoinAlgorithm algorithm;

    @Test
    @DisplayName("When getAiResponse, return a CoinEnum")
    void getAiResponse() {
        var response = algorithm.getAIResponse();

        assertEquals(CoinEnum.class, response.getClass());
    }

    @Test
    @DisplayName("When cheating against HEAD, return TAIL")
    void getAiCheatHEAD() {
        var response = algorithm.getAICheat(HEAD);

        assertEquals(TAIL, response);
    }

    @Test
    @DisplayName("When cheating against TAIL, return HEAD")
    void getAiCheatTail() {
        var response = algorithm.getAICheat(TAIL);

        assertEquals(HEAD, response);
    }
    
}
