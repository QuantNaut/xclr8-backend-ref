package com.xclr8.api.domain;

import java.io.Serializable;

/**
 * Created by shenju on 2/6/17.
 */
public class RoutineIdAndRoutineName implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String routineName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    @Override
    public String toString() {
        return "RoutineIdAndRoutineName{" +
            "id='" + id + '\'' +
            ", routineName='" + routineName + '\'' +
            '}';
    }
}
