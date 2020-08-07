package com.example.pld;

public class History {
    private String casenumber;
    private String previousdate;
    private String adjourndate;
    private String step;

    History( String casenumber, String previousdate, String adjourndate, String step)
    {
        this.casenumber=casenumber;
        this.previousdate=previousdate;
        this.adjourndate=adjourndate;
        this.step=step;
    }

    public String getCasenumber() {
        return casenumber;
    }

    public void setCasenumber(String casenumber) {
        this.casenumber = casenumber;
    }

    public String getPreviousdate() {
        return previousdate;
    }

    public void setPreviousdate(String previousdate) {
        this.previousdate = previousdate;
    }

    public String getAdjourndate() {
        return adjourndate;
    }

    public void setAdjourndate(String adjourndate) {
        this.adjourndate = adjourndate;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
