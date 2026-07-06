package com.enterprise.ai.dto;

import jakarta.validation.constraints.NotBlank;

public class IncidentClassificationRequest {

    @NotBlank(message = "Short Description is required")
    private String shortDescription;

    @NotBlank(message = "Description is required")
    private String description;

    public IncidentClassificationRequest() {
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}