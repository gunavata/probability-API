package com.tbww.probability.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Help.HelpBuilder.class)
@Builder(builderClassName = "HelpBuilder", toBuilder = true)
public class Help {
    private String name;
    private String usage;
    private String params;
    private String sample;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HelpBuilder {

    }

}
