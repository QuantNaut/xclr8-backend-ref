package com.xclr8.api.repository;

import com.xclr8.api.domain.PatientBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenju on 2/6/17.
 */
@Repository
public class CustomPatientBookingRepositoryImpl implements CustomPatientBookingRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<PatientBooking> findAllByDoctorIdAndBookingDateAndTime(String doctorId, ZonedDateTime bookingDateAndTime){
        Query query = new Query();
        List<Criteria> criterion = new ArrayList<Criteria>();
        criterion.add(Criteria.where("doctor_id").is(doctorId));
        ZonedDateTime from = bookingDateAndTime.minusHours(1);
        ZonedDateTime to = bookingDateAndTime.plusHours(1);
        criterion.add(Criteria.where("booking_date_and_time").gte(from).lte(to));
        query.addCriteria(new Criteria().andOperator(criterion.toArray(new Criteria[criterion.size()])));
        List<PatientBooking> patientBookings = mongoTemplate.find(query, PatientBooking.class);
        return  patientBookings;
    }

}
