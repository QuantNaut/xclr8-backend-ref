package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PatientBooking.
 */

@Document(collection = "patient_booking")
public class PatientBooking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("doctor_id")
    private String doctorId;

    @Field("patient_id")
    private String patientId;

    @Field("booking_date_and_time")
    private ZonedDateTime bookingDateAndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public PatientBooking doctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public PatientBooking patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public ZonedDateTime getBookingDateAndTime() {
        return bookingDateAndTime;
    }

    public PatientBooking bookingDateAndTime(ZonedDateTime bookingDateAndTime) {
        this.bookingDateAndTime = bookingDateAndTime;
        return this;
    }

    public void setBookingDateAndTime(ZonedDateTime bookingDateAndTime) {
        this.bookingDateAndTime = bookingDateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientBooking patientBooking = (PatientBooking) o;
        if (patientBooking.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, patientBooking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PatientBooking{" +
            "id=" + id +
            ", doctorId='" + doctorId + "'" +
            ", patientId='" + patientId + "'" +
            ", bookingDateAndTime='" + bookingDateAndTime + "'" +
            '}';
    }
}
