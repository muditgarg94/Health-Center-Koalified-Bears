package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDao extends JpaRepository<Bill, Long> {
}
