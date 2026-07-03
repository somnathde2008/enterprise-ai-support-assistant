package com.enterprise.ai.provider;

import java.util.List;

import com.enterprise.ai.model.Incident;

public interface IncidentProvider {

    /**
     * Get Incident by Incident Number
     */
    Incident getIncident(String incidentNumber);

    /**
     * Search incidents
     */
    List<Incident> searchIncidents(String keyword);

    /**
     * Create new incident
     *
     * Returns created incident number.
     */
    String createIncident(
            String shortDescription,
            String description,
            String category,
            String priority);

    /**
     * Update incident status
     */
    boolean updateIncidentStatus(
            String incidentNumber,
            String status);

    /**
     * Assign incident
     */
    boolean assignIncident(
            String incidentNumber,
            String assignmentGroup,
            String assignedTo);

    /**
     * Resolve incident
     */
    boolean resolveIncident(
            String incidentNumber,
            String resolution);

}