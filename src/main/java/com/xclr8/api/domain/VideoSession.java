package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A VideoSession.
 */

@Document(collection = "video_session")
public class VideoSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("doctor_id")
    private String doctorId;

    @Field("patient_id")
    private String patientId;

    @Field("video_session_token")
    private String videoSessionToken;

    @Field("video_session_id")
    private String videoSessionId;

    @Field("created_date")
    private ZonedDateTime createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public VideoSession doctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public VideoSession patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVideoSessionToken() {
        return videoSessionToken;
    }

    public VideoSession videoSessionToken(String videoSessionToken) {
        this.videoSessionToken = videoSessionToken;
        return this;
    }

    public void setVideoSessionToken(String videoSessionToken) {
        this.videoSessionToken = videoSessionToken;
    }

    public String getVideoSessionId() {
        return videoSessionId;
    }

    public VideoSession videoSessionId(String videoSessionId) {
        this.videoSessionId = videoSessionId;
        return this;
    }

    public void setVideoSessionId(String videoSessionId) {
        this.videoSessionId = videoSessionId;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public VideoSession createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoSession videoSession = (VideoSession) o;
        if (videoSession.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, videoSession.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "VideoSession{" +
            "id=" + id +
            ", doctorId='" + doctorId + "'" +
            ", patientId='" + patientId + "'" +
            ", videoSessionToken='" + videoSessionToken + "'" +
            ", videoSessionId='" + videoSessionId + "'" +
            ", createdDate='" + createdDate + "'" +
            '}';
    }
}
