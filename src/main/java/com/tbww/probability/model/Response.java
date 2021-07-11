package com.tbww.probability.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    String result;
    String message;
    ActivityResult state;
}
