package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<Staff, Long>{
}
