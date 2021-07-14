package com.tbww.probability.service.coin;

import com.tbww.probability.model.coin.CoinEnum;
import com.tbww.probability.service.InterfaceAlgorithm;

import org.springframework.stereotype.Component;

import static com.tbww.probability.model.coin.CoinEnum.*;

import java.security.SecureRandom;

@Component
public class CoinAlgorithm implements InterfaceAlgorithm<CoinEnum> {

    @Override
    public CoinEnum getAIResponse() {
        var random = new SecureRandom();
        return random.nextDouble() >= 50 ? HEAD : TAIL;
    }

    @Override
    public CoinEnum getAICheat(CoinEnum choice) {
        return choice == HEAD ? TAIL : HEAD;
    }
    
}
