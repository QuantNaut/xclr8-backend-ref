package com.xclr8.api.web.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class ExerciseSessionRequest {
    private String id;

    @JsonProperty("user_id")
    private String UserId;

    @NotBlank
    @JsonProperty("user_name")
    private String UserName;

    @JsonProperty("exercise_id")
    private String exerciseId;

    @NotBlank
    @JsonProperty("exercise_name")
    private String exerciseName;

    private long date;

    @JsonProperty("exercise_config")
    private Map<String, Object> exerciseConfig;

}
