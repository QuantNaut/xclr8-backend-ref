package com.xclr8.api.repository;

import com.xclr8.api.domain.Routine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
@Repository
public class CustomRoutineRepositoryImpl implements CustomRoutineRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    private final Logger log = LoggerFactory.getLogger(CustomRoutineRepository.class);

    public List<Routine> findAllRoutinesBySearchCriteria(String routineBodySection, String routineBodyParts){
        Query query = new Query();
        List<Criteria> criterion = new ArrayList<Criteria>();
        criterion.add(Criteria.where("routine_body_section").is(routineBodySection));
        if(routineBodyParts != null) {
            String[] bodyParts = routineBodyParts.split(":");
            for (String bodyPart : bodyParts) {
                if (bodyPart != null)
                    criterion.add(Criteria.where("routine_body_parts").in(bodyPart));
            }
        }
       /* for(String bodyPart : routineSearchCriteriaDTO.getRoutineBodyParts()){
            criterion.add(Criteria.where("routine_body_parts").in(bodyPart));
        }*/
        query.addCriteria(new Criteria().andOperator(criterion.toArray(new Criteria[criterion.size()])));
        log.debug("{}", query.toString());
        List<Routine> routines = mongoTemplate.find(query, Routine.class);
        return routines;
    }
}
