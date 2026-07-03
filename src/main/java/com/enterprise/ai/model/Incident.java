package com.enterprise.ai.model;

public class Incident {

	private Long id;
	private String incidentNumber;
	private String shortDescription;
	private String description;
	private String priority;
	private String assignmentGroup;
	private String status;
	private String category;
	private String sourceSystem;

    public Incident() {
    }

    

    public Incident(Long id, String incidentNumber, String shortDescription, String description,
			String priority, String assignmentGroup, String status, String category, String sourceSystem) {
		
		this.id = id;
		this.incidentNumber = incidentNumber;
		this.shortDescription = shortDescription;
		this.description = description;
		this.priority = priority;
		this.assignmentGroup = assignmentGroup;
		this.status = status;
		this.category = category;
		this.sourceSystem = sourceSystem;
	}



	public Long getid() {
		return id;
	}



	public void setid(Long id) {
		this.id = id;
	}



	public String getIncidentNumber() {
		return incidentNumber;
	}



	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
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



	public String getPriority() {
		return priority;
	}



	public void setPriority(String priority) {
		this.priority = priority;
	}



	public String getAssignmentGroup() {
		return assignmentGroup;
	}



	public void setAssignmentGroup(String assignmentGroup) {
		this.assignmentGroup = assignmentGroup;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getSourceSystem() {
		return sourceSystem;
	}



	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}



}