package com.xclr8.api.web.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;
import java.util.Set;

@Data
public class ExerciseSessionUpdateRequest {
    @JsonProperty("id")
    private String Id;

    private ExerciseSessionDetailRequest detail;
}
