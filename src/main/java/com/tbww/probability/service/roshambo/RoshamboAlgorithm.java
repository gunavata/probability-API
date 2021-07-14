package com.tbww.probability.service.roshambo;

import com.tbww.probability.model.roshambo.RoshamboEnum;
import com.tbww.probability.service.InterfaceAlgorithm;

import org.springframework.stereotype.Component;

import static com.tbww.probability.model.roshambo.RoshamboEnum.*;

import java.security.SecureRandom;

@Component
public class RoshamboAlgorithm implements InterfaceAlgorithm<RoshamboEnum> {

    @Override
    public RoshamboEnum getAIResponse() {
        var random = new SecureRandom();
        var response = random.nextDouble();
        if (response <= 0.333) {
            return PAPER;
        } else if (response <= 0.666) {
            return ROCK;
        } else {
            return SCISSOR;
        }
    }

    @Override
    public RoshamboEnum getAICheat(RoshamboEnum choice) {
        return switch (choice) {
            case PAPER -> SCISSOR;
            case ROCK -> PAPER;
            case SCISSOR -> ROCK;
            default -> throw new IllegalArgumentException("Choice is not valid! -> " + choice.toString());
        };
    }
    
}