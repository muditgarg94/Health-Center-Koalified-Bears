package com.kolafied.bears.HealthCare.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Table(name = "medicines")
@Entity
@EntityListeners(AuditingEntityListener.class)


public class Medicines {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicine_id;
    private String medicine_name;
    private Long m_count;
    private Long m_price;



	public Long getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(Long medicine_id) {
		this.medicine_id = medicine_id;
	}

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}

	public Long getM_count() {
		return m_count;
	}

	public void setM_count(Long m_count) {
		this.m_count = m_count;
	}

	public Long getM_price() {
		return m_price;
	}

	public void setM_price(Long m_price) {
		this.m_price = m_price;
	}


    
}