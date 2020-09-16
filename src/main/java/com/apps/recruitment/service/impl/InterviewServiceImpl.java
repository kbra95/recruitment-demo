package com.apps.recruitment.service.impl;

import com.apps.recruitment.dao.InterviewEntity;
import com.apps.recruitment.exception.RecordNotFoundException;
import com.apps.recruitment.repo.CandidateRepository;
import com.apps.recruitment.repo.InterviewRepository;
import com.apps.recruitment.service.IInterviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InterviewServiceImpl implements IInterviewService {
    private static final Logger LOGGER = LogManager.getLogger(InterviewServiceImpl.class);

    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<InterviewEntity> listAllInterview() {
        return interviewRepository.findAll();
    }

    @Override
    public List<InterviewEntity> getInterviewByCandNo(Integer candNo) {
        List<InterviewEntity> interviewEntities = interviewRepository.findByCandidateEntity(candNo);
        return interviewEntities;
    }

    @Override
    public InterviewEntity getInterviewById(Integer id) throws RuntimeException {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Interview", "id", id));
    }

    @Override
    public InterviewEntity createOrUpdateInterviewEntity(InterviewEntity newEntity) {
        return interviewRepository.save(newEntity);
    }

    @Override
    public void deleteInterviewById(Integer id) throws RuntimeException {
        Optional<InterviewEntity> interview = interviewRepository.findById(id);
        if (interview.isPresent()) {
            interviewRepository.deleteById(id);
        } else {
            throw new RuntimeException("No interview record exist for given id");
        }
    }

}
