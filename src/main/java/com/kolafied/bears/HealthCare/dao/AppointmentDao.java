package com.kolafied.bears.HealthCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kolafied.bears.HealthCare.model.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long> {
}
