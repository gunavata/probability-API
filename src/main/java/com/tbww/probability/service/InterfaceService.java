package com.tbww.probability.service;

import com.tbww.probability.model.ActivityResult;
import com.tbww.probability.model.Response;

public interface InterfaceService<T> {
    
    public Response getResult(String result, String message, ActivityResult state);

    public ActivityResult compareActivity(T player, T ai);

    public String generateResult(T player, T ai);

    public String generateMessage(ActivityResult state);
}
