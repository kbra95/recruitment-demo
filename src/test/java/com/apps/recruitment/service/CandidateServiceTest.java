package com.apps.recruitment.service;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.dao.InterviewEntity;
import com.apps.recruitment.models.AnalysedData;
import com.apps.recruitment.models.AnalyzeRequest;
import com.apps.recruitment.models.Candidate;
import com.apps.recruitment.models.KeyValue;
import com.apps.recruitment.repo.CandidateRepository;
import com.apps.recruitment.repo.InterviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    private ICandidateService candidateService;
    @MockBean
    private CandidateRepository candidateRepository;
    @MockBean
    private InterviewRepository interviewRepository;

    @Test
    void testGetInterviewById() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(1);
        candidateEntity.setName("xxx yy");
        candidateEntity.setType("junior");

        doReturn(Optional.of(candidateEntity)).when(candidateRepository).findById(1);
        CandidateEntity returnedEntity = candidateService.getCandidateById(1);

        Assertions.assertTrue(returnedEntity != null, "Record was not found");
        Assertions.assertSame(returnedEntity, candidateEntity, " REcords should be the same");
    }

    @Test
    void testFindAll() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(1);
        candidateEntity.setName("xxx yy");
        candidateEntity.setType("junior");
        CandidateEntity candidateEntity2 = new CandidateEntity();
        candidateEntity2.setId(2);
        candidateEntity2.setName("xxx yy");
        candidateEntity2.setType("junior");

        doReturn(Arrays.asList(candidateEntity, candidateEntity2)).when(candidateRepository).findAll();
        List<CandidateEntity> returnedValues = candidateService.listAllCandidate();
        Assertions.assertEquals(returnedValues.size(), 2, "The sizes should be the same");
    }

    @Test
    void testCreateOrUpdateCandidate() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(1);
        candidateEntity.setName("xxx yy");
        candidateEntity.setType("junior");

        doReturn(candidateEntity).when(candidateRepository).save(any());
        CandidateEntity returned = candidateService.createOrUpdateCandidateEntity(candidateEntity);
        Assertions.assertNotNull(returned, " It can not be null");
        Assertions.assertEquals(1, returned.getId(), "The ids should be the same");
    }

    @Test
    void testGetRatiosOfData_WithValidRequest() throws Exception {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setStatusCode("ACCEPTED");
        request.setDeptName("ISD");

        KeyValue keyValue1 = new KeyValue("ISD", 3L);
        KeyValue keyValue2 = new KeyValue("AIE", 1L);
        doReturn(Arrays.asList(keyValue1, keyValue2))
                .when(candidateRepository).countTotalAcceptOfDepartment(request.getStatusCode());

        KeyValue interviewResultMock = new KeyValue("fail", 2L);
        KeyValue interviewResultMock2 = new KeyValue("pass", 9L);
        doReturn(Arrays.asList(interviewResultMock, interviewResultMock2))
                .when(interviewRepository).countTotalInterviewResults(request.getDeptName());

        AnalysedData returnedData = candidateService.getRatiosOfData(request);

        Assertions.assertEquals(2L,returnedData.getNumberOfFail() );
        Assertions.assertEquals(9L,returnedData.getNumberOfPass());
        Assertions.assertEquals(75.0,returnedData.getRatio());
    }

}
