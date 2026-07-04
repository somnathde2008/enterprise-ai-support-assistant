package com.enterprise.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.ai.model.Incident;
import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.service.IncidentService;
import com.enterprise.ai.service.KnowledgeService;

@RestController
@RequestMapping("/glpi")
public class GlpiTestController {

   
    private IncidentService incidentService;
    private final KnowledgeService knowledgeService;
    
    public GlpiTestController(
            IncidentService incidentService,
            KnowledgeService knowledgeService) {

        this.incidentService = incidentService;
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/test")
    public String test() {
        return "GLPI Controller Working";
    }

    @PostMapping("/ticket/test")
    public String createTestTicket() {

        return incidentService.createIncident(
                "Database connection failed",
                "Unable to connect SQL Server from TPMS.",
                "Database",
                "P2");
    }
    
    
    @GetMapping("/ticket/{incidentNumber}")
    public Incident getIncident(
            @PathVariable String incidentNumber) {

        return incidentService.getIncident(incidentNumber);

    }
    
    
    @GetMapping("/tickets/search")
    public List<Incident> searchIncidents(
            @RequestParam String keyword) {

        return incidentService.searchIncidents(keyword);
    }
    
    
    @GetMapping("/knowledge/search")
    public List<KnowledgeArticle> searchKnowledge(
            @RequestParam String keyword) {

        return knowledgeService.searchKnowledge(keyword);

    }
}