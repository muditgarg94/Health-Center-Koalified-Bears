package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Patient;
import com.kolafied.bears.HealthCare.model.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientHistoryDao extends JpaRepository<PatientHistory, Long> {
}
