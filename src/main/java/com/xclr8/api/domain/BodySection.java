package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BodySection.
 */

@Document(collection = "body_section")
public class BodySection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("body_section")
    private String bodySection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodySection() {
        return bodySection;
    }

    public BodySection bodySection(String bodySection) {
        this.bodySection = bodySection;
        return this;
    }

    public void setBodySection(String bodySection) {
        this.bodySection = bodySection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodySection bodySection = (BodySection) o;
        if (bodySection.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, bodySection.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BodySection{" +
            "id=" + id +
            ", bodySection='" + bodySection + "'" +
            '}';
    }
}
