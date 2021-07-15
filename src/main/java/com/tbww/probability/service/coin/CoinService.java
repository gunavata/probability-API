package com.tbww.probability.service.coin;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.coin.CoinEnum;
import com.tbww.probability.service.InterfaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tbww.probability.model.ActivityResult.*;

@Service
public class CoinService implements InterfaceService<CoinEnum> {

    @Autowired
    CoinAlgorithm aiService;

    @Override
    public Response getResponse(String result, String message, ActivityResult state) {
        return Response.builder().result(result).message(message).state(state).build();
    }

    @Override
    public ActivityResult compareActivity(CoinEnum player, CoinEnum ai) {
        return player == ai ? WIN : LOSE;
    }

    @Override
    public String generateResult(CoinEnum player, CoinEnum ai) {
        String base = "You choose %s and the result was %s";
        return String.format(base, player, ai);
    }

    @Override
    public String generateMessage(ActivityResult state) {
        String base = "You got it %s!";
        return switch (state) {
            case LOSE -> String.format(base, "wrong");
            case WIN -> String.format(base, "right");
            default -> throw new IllegalArgumentException("TIE is not a valid option!");
        };
    }

    public Response flip(CoinEnum choice, int level) {
        CoinEnum aiChoice;
        switch (level) {
            case 0 -> aiChoice = aiService.getAIResponse();
            case 1 -> aiChoice = aiService.getAICheat(choice);
            default -> throw new IllegalArgumentException("Not a valid level! -> " + level);
        }

        ActivityResult state = compareActivity(choice, aiChoice);
        String message = generateMessage(state);
        String result = generateResult(choice, aiChoice);

        return getResponse(result, message, state);
    }

    public CoinEnum flipOne() {
        return aiService.getAIResponse();
    }
    
}
