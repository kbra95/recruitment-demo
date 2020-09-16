package com.apps.recruitment.models;

public class TotalInterviewResult {

    private int numOfPass;
    private int numOfPending;
    private int numOfFail;

    public TotalInterviewResult(int numOfPass, int numOfPending, int numOfFail) {
        this.numOfPass = numOfPass;
        this.numOfPending = numOfPending;
        this.numOfFail = numOfFail;
    }

    public TotalInterviewResult() {
    }

    public int getNumOfPass() {
        return numOfPass;
    }

    public void setNumOfPass(int numOfPass) {
        this.numOfPass = numOfPass;
    }

    public int getNumOfPending() {
        return numOfPending;
    }

    public void setNumOfPending(int numOfPending) {
        this.numOfPending = numOfPending;
    }

    public int getNumOfFail() {
        return numOfFail;
    }

    public void setNumOfFail(int numOfFail) {
        this.numOfFail = numOfFail;
    }
}
