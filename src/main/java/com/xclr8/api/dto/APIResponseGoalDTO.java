package com.xclr8.api.dto;

import com.xclr8.api.domain.Goal;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public class APIResponseGoalDTO extends APIResponseDTO {

    private List<Goal> goals;

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "APIResponseGoalDTO{" +
            "goals=" + goals +
            '}';
    }
}
