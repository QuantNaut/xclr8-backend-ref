package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A Session.
 */

@Document(collection = "session")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("session_name")
    private String sessionName;

    @Field("session_invitation_message")
    private String sessionInvitationMessage;

    @Field("patient_id")
    private String patientId;

    @Field("session_channel")
    private String sessionChannel;

    @Field("video_session_token")
    private String videoSessionToken;

    @Field("video_session_id")
    private String videoSessionId;

    @NotNull
    @Field("doctor_id")
    private String doctorId;

    @Field("start_date")
    private ZonedDateTime startDate;

    @Field("is_active")
    private Boolean isActive;

    @Field("end_date")
    private ZonedDateTime endDate;

    @Field("routines")
    private List<RoutineIdAndRoutineName> routines;

    @Field("patient_routine")
    private String patientRoutine;
  /*  @Field("goals")
    private Set<String> goals;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public Session sessionName(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Session startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Session endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getSessionChannel() {
        return sessionChannel;
    }

    public void setSessionChannel(String sessionChannel) {
        this.sessionChannel = sessionChannel;
    }

    public List<RoutineIdAndRoutineName> getRoutines() {
        return routines;
    }

    public void setRoutines(List<RoutineIdAndRoutineName> routines) {
        this.routines = routines;
    }

    public String getSessionInvitationMessage() {
        return sessionInvitationMessage;
    }

    public void setSessionInvitationMessage(String sessionInvitationMessage) {
        this.sessionInvitationMessage = sessionInvitationMessage;
    }

    public String getVideoSessionToken() {
        return videoSessionToken;
    }

    public void setVideoSessionToken(String videoSessionToken) {
        this.videoSessionToken = videoSessionToken;
    }

    public String getVideoSessionId() {
        return videoSessionId;
    }

    public void setVideoSessionId(String videoSessionId) {
        this.videoSessionId = videoSessionId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPatientRoutine() {
        return patientRoutine;
    }

    public void setPatientRoutine(String patientRoutine) {
        this.patientRoutine = patientRoutine;
    }

    /*public Set<String> getGoals() {
        return goals;
    }

    public Session goals(Set<String> goals) {
        this.goals = goals;
        return this;
    }

    public void setGoals(Set<String> goals) {
        this.goals = goals;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        if (session.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Session{" +
            "id=" + id +
            ", sessionName='" + sessionName + "'" +
            ", patientId='" + patientId + "'" +
            ", doctorId='" + doctorId + "'" +
            ", startDate='" + startDate + "'" +
            ", endDate='" + endDate + "'" +
            ", routines='" + routines + "'" +
            ", sessionChannel='" + sessionChannel + "'" +
            ", sessionInvitationMessage='" + sessionInvitationMessage + "'" +
            ", videoSessionId='" + videoSessionId + "'" +
            ", videoSessionToken='" + videoSessionToken + "'" +
            ", patientRoutine='" + patientRoutine + "'" +
          //  ", goals='" + goals + "'" +
            '}';
    }
}
