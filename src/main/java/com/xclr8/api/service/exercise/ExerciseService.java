package com.xclr8.api.service.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xclr8.api.domain.ExerciseSession;
import com.xclr8.api.domain.ExerciseSessionDetail;
import com.xclr8.api.repository.ExerciseSessionRepository;
import com.xclr8.api.service.SessionService;
import com.xclr8.api.web.rest.request.ExerciseSessionRequest;
import com.xclr8.api.web.rest.request.ExerciseSessionUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExerciseService {
    private final Logger log = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    ExerciseSessionRepository exerciseSessionRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public List<ExerciseSession> list() {
        log.debug("Request to list Exercise Session");
        return exerciseSessionRepository.findAll();
    }

    public ExerciseSession get(String id) {
        log.debug("Request to list Exercise Session");
        return exerciseSessionRepository.findOne(id);
    }

    public ExerciseSession save(ExerciseSessionRequest request) {
        log.debug("Request to save Exercise Session Request: {}", request);
        ExerciseSession exerciseSession = mapper.convertValue(request, ExerciseSession.class);
        log.debug("Request to save Exercise Session: {}", exerciseSession);
        return exerciseSessionRepository.save(exerciseSession);
    }

    public ExerciseSession updateDetails(ExerciseSessionUpdateRequest request) {
        log.debug("Request to update Exercise Session Detail Request: {}", request);
        ExerciseSession exerciseSession = exerciseSessionRepository.findOne(request.getId());
        log.debug("Exercise Session: {}", exerciseSession);
        if (exerciseSession == null) {
            return null;
        }
        Set<ExerciseSessionDetail> details = exerciseSession.getDetails();
        ExerciseSessionDetail detail = mapper.convertValue(request.getDetail(), ExerciseSessionDetail.class);
        details.add(detail);
        return exerciseSessionRepository.save(exerciseSession);
    }
}
