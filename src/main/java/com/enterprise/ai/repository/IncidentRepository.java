package com.enterprise.ai.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enterprise.ai.mapper.IncidentRowMapper;
import com.enterprise.ai.model.Incident;

@Repository
public class IncidentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final IncidentRowMapper rowMapper;

    public IncidentRepository(
            JdbcTemplate jdbcTemplate,
            IncidentRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // Get Incident by Number
    public Incident findByIncidentNumber(String incidentNumber) {

        String sql = """
                SELECT *
                FROM INCIDENT
                WHERE INCIDENT_NUMBER = ?
                """;

        List<Incident> incidents =
                jdbcTemplate.query(
                        sql,
                        rowMapper,
                        incidentNumber);

        return incidents.isEmpty()
                ? null
                : incidents.get(0);
    }

    // Get All Incidents
    public List<Incident> findAll() {

        String sql = """
                SELECT *
                FROM INCIDENT
                ORDER BY INCIDENT_NUMBER
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }

    // Create Incident
    public Incident save(Incident incident) {

        String sql = """
                INSERT INTO INCIDENT
                (
                    INCIDENT_NUMBER,
                    SHORT_DESCRIPTION,
                    DESCRIPTION,
                    PRIORITY,
                    ASSIGNMENT_GROUP,
                    STATUS
                )
                VALUES
                (
                    ?,?,?,?,?,?
                )
                """;

        jdbcTemplate.update(

                sql,

                incident.getIncidentNumber(),

                incident.getShortDescription(),

                incident.getDescription(),

                incident.getPriority(),

                incident.getAssignmentGroup(),

                incident.getStatus());

        return incident;
    }

    // Update Incident Status
    public boolean updateStatus(
            String incidentNumber,
            String status) {

        String sql = """
                UPDATE INCIDENT
                SET STATUS = ?
                WHERE INCIDENT_NUMBER = ?
                """;

        int rows = jdbcTemplate.update(
                sql,
                status,
                incidentNumber);

        return rows > 0;
    }

    // Search Incident
    public List<Incident> searchByKeyword(
            String keyword) {

        String search = "%" + keyword + "%";

        String sql = """
                SELECT *
                FROM INCIDENT
                WHERE
                LOWER(INCIDENT_NUMBER) LIKE LOWER(?)
                OR LOWER(SHORT_DESCRIPTION) LIKE LOWER(?)
                OR LOWER(DESCRIPTION) LIKE LOWER(?)
                OR LOWER(PRIORITY) LIKE LOWER(?)
                OR LOWER(ASSIGNMENT_GROUP) LIKE LOWER(?)
                OR LOWER(STATUS) LIKE LOWER(?)
                ORDER BY INCIDENT_NUMBER
                """;

        return jdbcTemplate.query(

                sql,

                rowMapper,

                search,

                search,

                search,

                search,

                search,

                search);
    }

}