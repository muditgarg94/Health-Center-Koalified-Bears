package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Instruments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InstrumentsDao extends JpaRepository<Instruments, Long>{

}
