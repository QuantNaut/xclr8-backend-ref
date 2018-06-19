package com.xclr8.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Document(collection = "exercise_session")
public class ExerciseSession {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("user_id")
    @JsonProperty("user_id")
    private String UserId;

    @Field("user_name")
    @JsonProperty("user_name")
    private String UserName;

    @Field("exercise_id")
    @JsonProperty("exercise_id")
    private String exerciseId;

    @Field("exercise_name")
    @JsonProperty("exercise_name")
    private String exerciseName;

    @Field("date")
    private long date;

    @Field("configuration")
    @JsonProperty("exercise_config")
    private Map<String, Object> exerciseConfig;

    @Field
    private Set<ExerciseSessionDetail> details = new HashSet<>();


}
