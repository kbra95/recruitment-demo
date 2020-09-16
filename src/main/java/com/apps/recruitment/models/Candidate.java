package com.apps.recruitment.models;

import com.apps.recruitment.dao.InterviewEntity;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private Integer id;
    private List<InterviewEntity> interviewEntities = new ArrayList<>();
    private String name;
    private String type;
    private String department;
    private String role;
    private String status;
    private String universty;
    private String currentCompany;
    private Integer candidate_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InterviewEntity> getInterviewEntities() {
        return interviewEntities;
    }

    public void setInterviewEntities(List<InterviewEntity> interviewEntities) {
        this.interviewEntities = interviewEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUniversty() {
        return universty;
    }

    public void setUniversty(String universty) {
        this.universty = universty;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public Integer getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(Integer candidate_id) {
        this.candidate_id = candidate_id;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", interviewEntities=" + interviewEntities +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", universty='" + universty + '\'' +
                ", currentCompany='" + currentCompany + '\'' +
                '}';
    }
}
