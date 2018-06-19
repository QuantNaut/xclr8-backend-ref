package com.xclr8.api.dto;

import com.xclr8.api.domain.Routine;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public class APIResponseRoutineDTO extends  APIResponseDTO{

    List<Routine> routines;

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    @Override
    public String toString() {
        return "APIResponseRoutineDTO{" +
            "routines=" + routines +
            '}';
    }
}
