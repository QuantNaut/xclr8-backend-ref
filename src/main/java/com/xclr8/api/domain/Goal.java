package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * A Goals.
 */

@Document(collection = "goal")
public class Goal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("session_id")
    private String sessionId;

    @Field("doctor_id")
    private String doctorId;

    @Field("body_parts")
    private Set<String> bodyParts;

    @Field("patient_id")
    private String patientId;

    @Field("routine_id")
    private String routineId;

    @Field("frequency_of_exercise")
    private String frequencyOfExercise;

    @Field("repetition_of_exercise")
    private String repetitionOfExercise;

    @Field("degree_of_motion")
    private String degreeOfMotion;

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

    public String getSessionId() {
        return sessionId;
    }

    public Goal sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Goal doctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public Goal patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRoutineId() {
        return routineId;
    }

    public Goal routineId(String routineId) {
        this.routineId = routineId;
        return this;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public String getFrequencyOfExercise() {
        return frequencyOfExercise;
    }

    public Goal frequencyOfExercise(String frequencyOfExercise) {
        this.frequencyOfExercise = frequencyOfExercise;
        return this;
    }

    public void setFrequencyOfExercise(String frequencyOfExercise) {
        this.frequencyOfExercise = frequencyOfExercise;
    }

    public String getRepetitionOfExercise() {
        return repetitionOfExercise;
    }

    public Goal repetitionOfExercise(String repetitionOfExercise) {
        this.repetitionOfExercise = repetitionOfExercise;
        return this;
    }

    public void setRepetitionOfExercise(String repetitionOfExercise) {
        this.repetitionOfExercise = repetitionOfExercise;
    }

    public String getDegreeOfMotion() {
        return degreeOfMotion;
    }

    public Goal degreeOfMotion(String degreeOfMotion) {
        this.degreeOfMotion = degreeOfMotion;
        return this;
    }

    public void setDegreeOfMotion(String degreeOfMotion) {
        this.degreeOfMotion = degreeOfMotion;
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

    public Set<String> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(Set<String> bodyParts) {
        this.bodyParts = bodyParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Goal goal = (Goal) o;
        if (goal.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, goal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Goals{" +
            "id=" + id +
            ", sessionId='" + sessionId + "'" +
            ", doctorId='" + doctorId + "'" +
            ", patientId='" + patientId + "'" +
            ", routineId='" + routineId + "'" +
            ", frequencyOfExercise='" + frequencyOfExercise + "'" +
            ", repetitionOfExercise='" + repetitionOfExercise + "'" +
            ", degreeOfMotion='" + degreeOfMotion + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", bodyParts='" + bodyParts + "'" +
            '}';
    }
}
