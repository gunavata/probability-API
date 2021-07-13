package com.tbww.probability.service.roshambo;

import com.tbww.probability.model.roshambo.RoshamboEnum;
import com.tbww.probability.service.InterfaceAlgorithm;

import org.springframework.stereotype.Component;

import static com.tbww.probability.model.roshambo.RoshamboEnum.*;

@Component
public class RoshamboAlgorithm implements InterfaceAlgorithm<RoshamboEnum> {

    @Override
    public RoshamboEnum getAIResponse() {
        double response = Math.random();
        if (response <= 0.334) {
            return PAPER;
        } else if (response <= 0.667) {
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
