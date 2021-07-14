package com.tbww.probability.service.coin;

import com.tbww.probability.model.coin.CoinEnum;
import com.tbww.probability.service.InterfaceAlgorithm;

import org.springframework.stereotype.Component;

import static com.tbww.probability.model.coin.CoinEnum.*;

@Component
public class CoinAlgorithm implements InterfaceAlgorithm<CoinEnum> {

    @Override
    public CoinEnum getAIResponse() {
        double response = Math.random();
        return response >= 50 ? HEAD : TAIL;
    }

    @Override
    public CoinEnum getAICheat(CoinEnum choice) {
        return choice == HEAD ? TAIL : HEAD;
    }
    
}
