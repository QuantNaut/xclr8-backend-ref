package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BodyPart.
 */

@Document(collection = "body_part")
public class BodyPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("body_part")
    private String bodyPart;

    @Field("body_section_id")
    private String bodySectionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public String getBodySectionId() {
        return bodySectionId;
    }

    public void setBodySectionId(String bodySectionId) {
        this.bodySectionId = bodySectionId;
    }

    public BodyPart bodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
        return this;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyPart bodyPart = (BodyPart) o;
        if (bodyPart.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, bodyPart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BodyPart{" +
            "id=" + id +
            ", bodyPart='" + bodyPart + "'" +
            ", bodySectionId='" + bodySectionId + "'" +
            '}';
    }
}
