package com.enterprise.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Incident;

@Service
public class IncidentSimilarityService {

    private final IncidentService incidentService;

    public IncidentSimilarityService(
            IncidentService incidentService) {

        this.incidentService = incidentService;
    }

    public List<Incident> findSimilarIncidents(
            String keyword) {

        return incidentService.searchIncidents(keyword);

    }

}