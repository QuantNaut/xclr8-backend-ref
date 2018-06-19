package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PatientRoutineMapping.
 */

@Document(collection = "patient_routine_mapping")
public class PatientRoutineMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("routine_id")
    private String routineId;

    @Field("routine_name")
    private String routineName;

    @Field("patient_id")
    private String patientId;

    @Field("doctor_id")
    private String doctorId;

    @Field("created_date")
    private ZonedDateTime createdDate;

    @Field("modified_date")
    private ZonedDateTime modifiedDate;

    @Field("is_active")
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutineId() {
        return routineId;
    }

    public PatientRoutineMapping routineId(String routineId) {
        this.routineId = routineId;
        return this;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public String getPatientId() {
        return patientId;
    }

    public PatientRoutineMapping patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public PatientRoutineMapping createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public PatientRoutineMapping modifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public PatientRoutineMapping isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientRoutineMapping patientRoutineMapping = (PatientRoutineMapping) o;
        if (patientRoutineMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, patientRoutineMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PatientRoutineMapping{" +
            "id=" + id +
            ", routineId='" + routineId + "'" +
            ", routineName='" + routineName + "'" +
            ", patientId='" + patientId + "'" +
            ", createdDate='" + createdDate + "'" +
            ", modifiedDate='" + modifiedDate + "'" +
            ", doctorId='" + doctorId + "'" +
            ", isActive='" + isActive + "'" +
            '}';
    }
}
