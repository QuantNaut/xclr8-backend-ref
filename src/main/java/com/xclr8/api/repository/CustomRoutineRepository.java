package com.xclr8.api.repository;

import com.xclr8.api.domain.Routine;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public interface CustomRoutineRepository {

    List<Routine> findAllRoutinesBySearchCriteria(String routineBodySection, String routineBodyParts);

}
