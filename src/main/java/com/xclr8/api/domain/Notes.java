package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Notes.
 */

@Document(collection = "notes")
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("header")
    private String header;

    @Field("session_id")
    private String sessionId;

    @Field("current_condition")
    private String currentCondition;

    @Field("goals")
    private String goals;

    @Field("subjective")
    private String subjective;

    @Field("objective")
    private String objective;

    @Field("assessment")
    private String assessment;

    @Field("plan")
    private String plan;

    @Field("patient_id")
    private String patientId;

    @Field("routine_id")
    private String routineId;

    @Field("created_date")
    private ZonedDateTime createdDate;

    @Field("updated_date")
    private ZonedDateTime updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public Notes header(String header) {
        this.header = header;
        return this;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public Notes currentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
        return this;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getGoals() {
        return goals;
    }

    public Notes goals(String goals) {
        this.goals = goals;
        return this;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getSubjective() {
        return subjective;
    }

    public Notes subjective(String subjective) {
        this.subjective = subjective;
        return this;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getObjective() {
        return objective;
    }

    public Notes objective(String objective) {
        this.objective = objective;
        return this;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getAssessment() {
        return assessment;
    }

    public Notes assessment(String assessment) {
        this.assessment = assessment;
        return this;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getPlan() {
        return plan;
    }

    public Notes plan(String plan) {
        this.plan = plan;
        return this;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPatientId() {
        return patientId;
    }

    public Notes patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRoutineId() {
        return routineId;
    }

    public Notes routineId(String routineId) {
        this.routineId = routineId;
        return this;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notes notes = (Notes) o;
        if (notes.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, notes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Notes{" +
            "id=" + id +
            ", header='" + header + "'" +
            ", currentCondition='" + currentCondition + "'" +
            ", goals='" + goals + "'" +
            ", subjective='" + subjective + "'" +
            ", objective='" + objective + "'" +
            ", assessment='" + assessment + "'" +
            ", plan='" + plan + "'" +
            ", patientId='" + patientId + "'" +
            ", routineId='" + routineId + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", sessionId='" + sessionId + "'" +
            '}';
    }
}
