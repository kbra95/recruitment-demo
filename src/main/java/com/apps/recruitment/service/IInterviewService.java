package com.apps.recruitment.service;

import com.apps.recruitment.dao.InterviewEntity;

import java.util.List;

public interface IInterviewService {

    List<InterviewEntity> listAllInterview();

    InterviewEntity getInterviewById(Integer id) throws RuntimeException;

    InterviewEntity createOrUpdateInterviewEntity(InterviewEntity newEntity);

    void deleteInterviewById(Integer id) throws RuntimeException;

    List<InterviewEntity> getInterviewByCandNo(Integer candNo);
}
