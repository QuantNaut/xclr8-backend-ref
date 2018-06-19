package com.xclr8.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExerciseSessionDetail {

    @JsonProperty("repetition_no")
    private int repetitionNo;

    @JsonProperty("min_angle")
    private float minAngle;

    @JsonProperty("max_angle")
    private float maxAngle;

    private float speed;

    private float duration;

}
