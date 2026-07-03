package com.enterprise.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Incident;
import com.enterprise.ai.provider.IncidentProvider;

@Service
public class IncidentService {

    private final IncidentProvider incidentProvider;

    public IncidentService(IncidentProvider incidentProvider) {
        this.incidentProvider = incidentProvider;
    }

    public Incident getIncident(String incidentNumber) {
        return incidentProvider.getIncident(incidentNumber);
    }

    public List<Incident> searchIncidents(String keyword) {
        return incidentProvider.searchIncidents(keyword);
    }

    public String createIncident(
            String shortDescription,
            String description,
            String category,
            String priority) {

        return incidentProvider.createIncident(
                shortDescription,
                description,
                category,
                priority);
    }

    public boolean updateIncidentStatus(
            String incidentNumber,
            String status) {

        return incidentProvider.updateIncidentStatus(
                incidentNumber,
                status);
    }

    public boolean assignIncident(
            String incidentNumber,
            String assignmentGroup,
            String assignedTo) {

        return incidentProvider.assignIncident(
                incidentNumber,
                assignmentGroup,
                assignedTo);
    }

    public boolean resolveIncident(
            String incidentNumber,
            String resolution) {

        return incidentProvider.resolveIncident(
                incidentNumber,
                resolution);
    }
}