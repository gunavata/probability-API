package com.tbww.probability.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Response.ResponseBuilder.class)
@Builder(builderClassName = "ResponseBuilder", toBuilder = true)
public class Response {
    String result;
    String message;
    ActivityResult state;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResponseBuilder {

    }
}
