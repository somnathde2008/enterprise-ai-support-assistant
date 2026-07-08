package com.enterprise.ai.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enterprise.ai.model.Incident;

@Service
public class IncidentSimilarityServiceImpl
        implements IncidentSimilarityService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    IncidentSimilarityServiceImpl.class);

    private final IncidentService incidentService;

    public IncidentSimilarityServiceImpl(
            IncidentService incidentService) {

        this.incidentService = incidentService;
    }

    @Override
    public List<Incident> findSimilarIncidents(
            String incidentNumber,
            int limit) {

        LOGGER.info(
                "Finding similar incidents for {}",
                incidentNumber);

        Incident incident =
                incidentService.getIncident(
                        incidentNumber);

        if (incident == null) {

            LOGGER.warn(
                    "Incident {} not found",
                    incidentNumber);

            return Collections.emptyList();
        }

        String keyword =
                incident.getShortDescription();

        List<Incident> results =
                incidentService.searchIncidents(
                        keyword);
        
		/*
		 * float[] embedding = embeddingService.generateEmbedding(
		 * incident.getShortDescription());
		 * 
		 * List<Incident> results = vectorStore.search( embedding, limit);
		 */

        LOGGER.info(
                "Found {} candidate incidents",
                results.size());

        return results.stream()
                .filter(i ->
                        !incidentNumber.equals(
                                i.getIncidentNumber()))
                .limit(limit)
                .toList();
    }

}