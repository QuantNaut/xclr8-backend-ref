package com.xclr8.api.web.rest;

import com.xclr8.api.domain.ExerciseSession;
import com.xclr8.api.repository.ExerciseSessionRepository;
import com.xclr8.api.service.exercise.ExerciseService;
import com.xclr8.api.web.rest.request.ExerciseSessionRequest;
import com.xclr8.api.web.rest.request.ExerciseSessionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ExerciseSession> list() {
        return exerciseService.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExerciseSession list(@PathVariable("id") String id) {
        return exerciseService.get(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ExerciseSession save(@Valid @RequestBody ExerciseSessionRequest request,
                                final BindingResult bindingResult) {
        return exerciseService.save(request);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.PUT)
    public ExerciseSession saveDetail(@Valid @RequestBody ExerciseSessionUpdateRequest request,
                                      final BindingResult bindingResult) {
        return exerciseService.updateDetails(request);
    }

}
