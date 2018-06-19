package com.xclr8.api.dto;

import com.xclr8.api.domain.PatientBooking;
import com.xclr8.api.service.dto.PatientBookingDTO;

import java.util.List;

/**
 * Created by shenju on 2/6/17.
 */
public class APIResponsePatientBookingDTO extends APIResponseDTO{
    List<PatientBookingDTO> data;

    public List<PatientBookingDTO> getData() {
        return data;
    }

    public void setData(List<PatientBookingDTO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponsePatientBookingDTO{" +
            "data=" + data +
            '}';
    }
}
