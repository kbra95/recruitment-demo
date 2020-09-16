package com.apps.recruitment.service.impl;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.exception.RecordNotFoundException;
import com.apps.recruitment.models.AnalysedData;
import com.apps.recruitment.models.AnalyzeRequest;
import com.apps.recruitment.models.KeyValue;
import com.apps.recruitment.repo.CandidateRepository;
import com.apps.recruitment.repo.InterviewRepository;
import com.apps.recruitment.service.ICandidateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements ICandidateService {
    private static final Logger LOGGER = LogManager.getLogger(CandidateServiceImpl.class);
    public static final String PASS = "pass";
    public static final String FAIL = "fail";
    public static final String PENDING = "pending";
    @Autowired
    CandidateRepository repository;
    @Autowired
    InterviewRepository interviewRepository;

    @Override
    public List<CandidateEntity> listAllCandidate() {
        List<CandidateEntity> candidateEntities = repository.findAll();
        if (!CollectionUtils.isEmpty(candidateEntities)) {
            return candidateEntities;
        }
        return new ArrayList<>();
    }

    @Override
    public CandidateEntity getCandidateById(Integer id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Candidate", "id", id));
    }

    @Override
    public CandidateEntity createOrUpdateCandidateEntity(CandidateEntity newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public boolean deleteCandidateById(Integer id) throws RuntimeException {
        Optional<CandidateEntity> candidateEntity = repository.findById(id);
        if (candidateEntity.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new RecordNotFoundException("Candidate", "id", id);
        }
    }

    @Override
    public AnalysedData getRatiosOfData(AnalyzeRequest request) throws Exception {
        validateRequest(request);
        List<KeyValue> departments = repository.countTotalAcceptOfDepartment(request.getStatusCode());
        AnalysedData analysedData = new AnalysedData();
        analysedData.setNumberOfFail(getTotalResultByResultCode(FAIL, request));
        analysedData.setNumberOfPass(getTotalResultByResultCode(PASS, request));
        LOGGER.info(getTotalResultByResultCode("pass", request) + " result of pass");
        if (!CollectionUtils.isEmpty(departments)) {
            double ratio = getRatio(request, departments);
            analysedData.setRatio(ratio);
        }
        return analysedData;
    }

    private void validateRequest(AnalyzeRequest request) throws Exception {
        if(!Status.contains(request.getStatusCode()))
            throw new Exception("Status value is not valid" + ( request.getStatusCode()) + "You should use one of them ACCEPTED,IN_PROCESS,CANCELLED");
    }

    private double getRatio(AnalyzeRequest request, List<KeyValue> departments) {
        double totalDept = departments.stream()
                .map(KeyValue::getValue)
                .reduce(0L, Long::sum);
        double resultOfDept = departments.stream()
                .filter(keyValue -> keyValue.getKey().equals(request.getDeptName()))
                .map(KeyValue::getValue)
                .findFirst().orElse(0L);
        return (resultOfDept / totalDept) * 100;
    }

    private Long getTotalResultByResultCode(String result, AnalyzeRequest request) {
        List<KeyValue> interviewResults = interviewRepository.countTotalInterviewResults(request.getDeptName());
        if (!CollectionUtils.isEmpty(interviewResults))
            return interviewResults.stream()
                    .filter(i -> i.getKey().equals(result))
                    .map(KeyValue::getValue).findFirst().orElse(null);
        return null;
    }

    public enum Status{
        ACCEPTED,
        CANCELLED,
        IN_PROCESS;
        public static boolean contains(String test) {
            for (Status c : Status.values()) {
                if (c.name().equals(test)) {
                    return true;
                }
            }
            return false;
        }
    }
}
