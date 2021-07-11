package com.tbww.probability.service.roshambo;

import com.tbww.probability.model.roshambo.RoshamboEnum;
import com.tbww.probability.service.InterfaceAlgorithm;

import org.springframework.stereotype.Component;

@Component
public class RoshamboAlgorithm implements InterfaceAlgorithm<RoshamboEnum> {

    @Override
    public RoshamboEnum getAIResponse() {
        double response = Math.random();
        if (response <= 0.334) {
            return RoshamboEnum.PAPER;
        } else if (response <= 0.667) {
            return RoshamboEnum.ROCK;
        } else {
            return RoshamboEnum.SCISSOR;
        }
    }

    @Override
    public RoshamboEnum getAICheat(RoshamboEnum choice) {
        return switch (choice) {
            case PAPER -> RoshamboEnum.SCISSOR;
            case ROCK -> RoshamboEnum.PAPER;
            case SCISSOR -> RoshamboEnum.ROCK;
            default -> throw new IllegalArgumentException("Choice is not valid! -> " + choice.toString());
        };
    }
    
}
