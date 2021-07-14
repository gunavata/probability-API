package com.tbww.probability.service.coin;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.coin.CoinEnum;
import com.tbww.probability.service.InterfaceService;

import org.springframework.stereotype.Service;

@Service
public class CoinService implements InterfaceService<CoinEnum> {

    @Override
    public Response getResponse(String result, String message, ActivityResult state) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActivityResult compareActivity(CoinEnum player, CoinEnum ai) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String generateResult(CoinEnum player, CoinEnum ai) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String generateMessage(ActivityResult state) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object flip(CoinEnum head, int i) {
        return null;
    }
    
}
