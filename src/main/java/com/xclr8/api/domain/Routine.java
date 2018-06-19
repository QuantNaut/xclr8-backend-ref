package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * A Routine.
 */

@Document(collection = "routine")
public class Routine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("routine_name")
    private String routineName;

    @Field("routine_body_parts")
    private Set<String> routineBodyParts;

    @Field("routine_body_section")
    private String routineBodySection;

    @Field("routine_data_url")
    private String routineDataUrl;

    @NotNull
    @Field("created_by")
    private String createdBy;

    @Field("creation_date")
    private ZonedDateTime creationDate;

    @Field("updated_date")
    private ZonedDateTime updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public Routine routineName(String routineName) {
        this.routineName = routineName;
        return this;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Routine createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Routine creationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<String> getRoutineBodyParts() {
        return routineBodyParts;
    }

    public void setRoutineBodyParts(Set<String> routineBodyParts) {
        this.routineBodyParts = routineBodyParts;
    }

    public String getRoutineBodySection() {
        return routineBodySection;
    }

    public void setRoutineBodySection(String routineBodySection) {
        this.routineBodySection = routineBodySection;
    }

    public String getRoutineDataUrl() {
        return routineDataUrl;
    }

    public void setRoutineDataUrl(String routineDataUrl) {
        this.routineDataUrl = routineDataUrl;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Routine routine = (Routine) o;
        if (routine.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, routine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Routine{" +
            "id=" + id +
            ", routineName='" + routineName + "'" +
            ", createdBy='" + createdBy + "'" +
            ", creationDate='" + creationDate + "'" +
            ", routineBodyParts='" + routineBodyParts + "'" +
            ", routineBodySection='" + routineBodySection + "'" +
            ", routineDataUrl='" + routineDataUrl + "'" +
            ", updatedDate='" + updatedDate + "'" +
            '}';
    }
}
