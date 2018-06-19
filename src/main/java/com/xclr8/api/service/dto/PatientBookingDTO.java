package com.xclr8.api.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the PatientBooking entity.
 */
public class PatientBookingDTO implements Serializable {

    private String id;

    private String doctorId;

    private String patientId;

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

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public ZonedDateTime getBookingDateAndTime() {
        return bookingDateAndTime;
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

        PatientBookingDTO patientBookingDTO = (PatientBookingDTO) o;

        if ( ! Objects.equals(id, patientBookingDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PatientBookingDTO{" +
            "id=" + id +
            ", doctorId='" + doctorId + "'" +
            ", patientId='" + patientId + "'" +
            ", bookingDateAndTime='" + bookingDateAndTime + "'" +
            '}';
    }
}
