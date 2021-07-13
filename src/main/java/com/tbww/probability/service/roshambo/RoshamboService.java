package com.tbww.probability.service.roshambo;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.roshambo.RoshamboEnum;
import com.tbww.probability.service.InterfaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tbww.probability.model.ActivityResult.*;

@Service
public class RoshamboService implements InterfaceService<RoshamboEnum> {

    @Autowired
    private RoshamboAlgorithm aiService;

    @Override
    public Response getResponse(String result, String message, ActivityResult state) {
        return Response.builder().result(result).message(message).state(state).build();
    }

    @Override
    public String generateMessage(ActivityResult state) {
        String base = "%s! You %s!";
        return switch (state) {
            case LOSE -> String.format(base, "Too bad", state.getActivity());
            case TIE -> String.format(base, "Nice Try", state.getActivity());
            case WIN -> String.format(base, "Congratulations", state.getActivity());
            default -> throw new IllegalArgumentException("Unknown state! -> " +  state.toString());
        };
    }

    @Override
    public String generateResult(RoshamboEnum player, RoshamboEnum ai) {
        String base = "You choose %s against the AI's %s";
        return String.format(base, player, ai);
    }

    @Override
    public ActivityResult compareActivity(RoshamboEnum player, RoshamboEnum ai) {
        RoshamboEnum counter = aiService.getAICheat(player);
        if(ai == counter) {
            return LOSE;
        } else if (ai == player) {
            return TIE;
        } else {
            return WIN;
        }
    }

    public Response compute(RoshamboEnum choice, int level) {
        RoshamboEnum aiChoice;
        switch (level) {
            // Win 1/3, Tie 1/3 and Lose 1/3
            case 0 -> {
                aiChoice = aiService.getAIResponse();
            }
            // Win 2/3, Lose 1/3
            case 1 -> {
                aiChoice = aiService.getAIResponse();
                if (aiChoice == choice) {
                    aiChoice = aiService.getAICheat(choice);
                }
            }
            // Win everytime
            case 2 -> {
                aiChoice = aiService.getAICheat(choice);
            }
            default -> {
                throw new IllegalArgumentException("Not a valid level! -> " + level);
            }
        }
        ActivityResult state = compareActivity(choice, aiChoice);
        String message = generateMessage(state);
        String result = generateResult(choice, aiChoice);

        return getResponse(result, message, state);
    }

    

}
