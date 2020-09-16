package com.apps.recruitment.controller;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.models.AnalysedData;
import com.apps.recruitment.models.AnalyzeRequest;
import com.apps.recruitment.service.impl.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateServiceImpl candidateServiceImpl;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CandidateEntity>> getAllCandidates() {
        return new ResponseEntity<>(candidateServiceImpl.listAllCandidate(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateEntity> getCandidateById(@PathVariable("id") Integer id)
            throws RuntimeException {
        CandidateEntity entity = candidateServiceImpl.getCandidateById(id);
        return (entity != null) ? new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK)
                : new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateEntity> createOrUpdateCandidate(@RequestBody CandidateEntity candidateEntity)
            throws RuntimeException {
        CandidateEntity updated = candidateServiceImpl.createOrUpdateCandidateEntity(candidateEntity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCandidateById(@PathVariable("id") Integer id)
            throws RuntimeException {
        candidateServiceImpl.deleteCandidateById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/analysis")
    public ResponseEntity<AnalysedData> getAnalyzeResult(@RequestBody AnalyzeRequest request) throws Exception {
        return new ResponseEntity<>(candidateServiceImpl.getRatiosOfData(request), new HttpHeaders(), HttpStatus.OK);
    }
}
