package com.enterprise.ai.dto;

import java.util.List;

public class IncidentAnalysisResponse {

    private String category;
    private Integer confidence;
    private String rootCause;
    private List<String> resolution;
    private Boolean escalationRequired;
	/*
	 * private String incidentNumber;
	 * 
	 * public String getIncidentNumber() { return incidentNumber; }
	 * 
	 * public void setIncidentNumber(String incidentNumber) { this.incidentNumber =
	 * incidentNumber; }
	 */

    public IncidentAnalysisResponse() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public List<String> getResolution() {
        return resolution;
    }

    public void setResolution(List<String> resolution) {
        this.resolution = resolution;
    }

    public Boolean getEscalationRequired() {
        return escalationRequired;
    }

    public void setEscalationRequired(Boolean escalationRequired) {
        this.escalationRequired = escalationRequired;
    }

}