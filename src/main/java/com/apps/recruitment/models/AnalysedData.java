package com.apps.recruitment.models;

public class AnalysedData {
    private Long numberOfFail;
    private Long numberOfPass;
    private Double ratio;

    public Long getNumberOfFail() {
        return numberOfFail;
    }

    public void setNumberOfFail(Long numberOfFail) {
        this.numberOfFail = numberOfFail;
    }

    public Long getNumberOfPass() {
        return numberOfPass;
    }

    public void setNumberOfPass(Long numberOfPass) {
        this.numberOfPass = numberOfPass;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratioOfAcceptedByDepartment) {
        this.ratio = ratioOfAcceptedByDepartment;
    }


    @Override
    public String toString() {
        return "AnalysedData{" +
                "numberOfFail=" + numberOfFail +
                ", numberOfPass=" + numberOfPass +
                '}';
    }
}
