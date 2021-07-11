package com.tbww.probability.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Help {
    private String name;
    private String usage;
    private String params;
    private String sample;
}