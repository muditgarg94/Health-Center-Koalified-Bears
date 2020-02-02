package com.kolafied.bears.HealthCare.dao;

import com.kolafied.bears.HealthCare.model.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MedicinesDao extends JpaRepository<Medicines, Long> {

}
