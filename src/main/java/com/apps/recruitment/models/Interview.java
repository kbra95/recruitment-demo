package com.apps.recruitment.models;

import com.apps.recruitment.dao.CandidateEntity;

import java.util.Date;

public class Interview {
    private Integer id;
    private Date interviewDate;
    private String interviewerName;
    private String result;
    private String cmnt;
    private Date onboardDate;
    private String type;
    private Candidate candidateEntity;

    public Candidate getCandidateEntity() {
        return candidateEntity;
    }

    public void setCandidateEntity(Candidate candidateEntity) {
        this.candidateEntity = candidateEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCmnt() {
        return cmnt;
    }

    public void setCmnt(String cmnt) {
        this.cmnt = cmnt;
    }

    public Date getOnboardDate() {
        return onboardDate;
    }

    public void setOnboardDate(Date onboardDate) {
        this.onboardDate = onboardDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "InterviewModel{" +
                "id=" + id +
                ", interviewDate=" + interviewDate +
                ", interviewerName='" + interviewerName + '\'' +
                ", result='" + result + '\'' +
                ", cmnt='" + cmnt + '\'' +
                ", onboardDate=" + onboardDate +
                ", type='" + type + '\'' +
                '}';
    }
}
