package com.enterprise.ai.orchestrator;

import java.util.List;

import com.enterprise.ai.dto.IncidentAnalysisResponse;
import com.enterprise.ai.model.KnowledgeArticle;
import com.enterprise.ai.model.Recommendation;

public class HybridResponse {

    private String incidentNumber;

    //private String analysis;
    
    private IncidentAnalysisResponse analysis;

    private List<KnowledgeArticle> knowledgeArticles;

    private Recommendation recommendation;

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public IncidentAnalysisResponse getAnalysis() {
		return analysis;
	}

	public void setAnalysis(IncidentAnalysisResponse analysis) {
		this.analysis = analysis;
	}

	/*
	 * public String getAnalysis() { return analysis; }
	 * 
	 * public void setAnalysis(String analysis) { this.analysis = analysis; }
	 */
    public List<KnowledgeArticle> getKnowledgeArticles() {
        return knowledgeArticles;
    }

    public void setKnowledgeArticles(List<KnowledgeArticle> knowledgeArticles) {
        this.knowledgeArticles = knowledgeArticles;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

}