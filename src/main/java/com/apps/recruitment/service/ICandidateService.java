package com.apps.recruitment.service;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.models.AnalysedData;
import com.apps.recruitment.models.AnalyzeRequest;

import java.util.List;

public interface ICandidateService {

    List<CandidateEntity> listAllCandidate();

    CandidateEntity getCandidateById(Integer id) throws RuntimeException;

    CandidateEntity createOrUpdateCandidateEntity(CandidateEntity newEntity);

    boolean deleteCandidateById(Integer id) throws RuntimeException;

    AnalysedData getRatiosOfData(AnalyzeRequest request) throws Exception;
}
