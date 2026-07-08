package com.enterprise.ai.service;

import java.util.List;

import com.enterprise.ai.model.Incident;

public interface IncidentSimilarityService {

    /**
     * Find similar incidents for the given incident number.
     *
     * @param incidentNumber Incident Number
     * @param limit Maximum results
     * @return Similar incidents
     */
    List<Incident> findSimilarIncidents(
            String incidentNumber,
            int limit);

}