package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
}
