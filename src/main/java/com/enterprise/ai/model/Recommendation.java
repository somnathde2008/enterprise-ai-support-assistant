package com.enterprise.ai.model;

public class Recommendation {

    private String category;

    private String recommendedTeam;

    private String estimatedResolutionTime;

    private String preventiveAction;

    private String recommendedKB;

    private boolean escalationRequired;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRecommendedTeam() {
        return recommendedTeam;
    }

    public void setRecommendedTeam(String recommendedTeam) {
        this.recommendedTeam = recommendedTeam;
    }

    public String getEstimatedResolutionTime() {
        return estimatedResolutionTime;
    }

    public void setEstimatedResolutionTime(String estimatedResolutionTime) {
        this.estimatedResolutionTime = estimatedResolutionTime;
    }

    public String getPreventiveAction() {
        return preventiveAction;
    }

    public void setPreventiveAction(String preventiveAction) {
        this.preventiveAction = preventiveAction;
    }

    public String getRecommendedKB() {
        return recommendedKB;
    }

    public void setRecommendedKB(String recommendedKB) {
        this.recommendedKB = recommendedKB;
    }

    public boolean isEscalationRequired() {
        return escalationRequired;
    }

    public void setEscalationRequired(boolean escalationRequired) {
        this.escalationRequired = escalationRequired;
    }

}