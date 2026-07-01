package com.enterprise.ai.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.enterprise.ai.model.Incident;

@Component
public class IncidentRowMapper implements RowMapper<Incident> {

    @Override
    public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {

        Incident incident = new Incident();

        incident.setIncidentNumber(rs.getString("INCIDENT_NUMBER"));
        incident.setShortDescription(rs.getString("SHORT_DESCRIPTION"));
        incident.setDescription(rs.getString("DESCRIPTION"));
        incident.setPriority(rs.getString("PRIORITY"));
        incident.setAssignmentGroup(rs.getString("ASSIGNMENT_GROUP"));
        incident.setStatus(rs.getString("STATUS"));

        return incident;
    }
}