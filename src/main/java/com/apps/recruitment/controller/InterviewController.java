package com.apps.recruitment.controller;

import com.apps.recruitment.dao.InterviewEntity;
import com.apps.recruitment.service.impl.InterviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewServiceImpl interviewServiceImpl;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<InterviewEntity>> getAllInterviews() {
        return new ResponseEntity<>(interviewServiceImpl.listAllInterview(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewEntity> getInterviewById(@PathVariable("id") Integer id)
            throws RuntimeException {
        InterviewEntity entity = interviewServiceImpl.getInterviewById(id);
        return (entity != null) ? new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK)
                : new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bycandidate/{cand_no}")
    public ResponseEntity<List<InterviewEntity>> getInterviewByCandNo(@PathVariable("cand_no") Integer candNo) {
        return new ResponseEntity<>(interviewServiceImpl.getInterviewByCandNo(candNo), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InterviewEntity> createOrUpdateInterview(@RequestBody InterviewEntity interviewEntity)
            throws RuntimeException {
        InterviewEntity updated = interviewServiceImpl.createOrUpdateInterviewEntity(interviewEntity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteInterviewById(@PathVariable("id") Integer id)
            throws RuntimeException {
        interviewServiceImpl.deleteInterviewById(id);
        return HttpStatus.OK;
    }

}
