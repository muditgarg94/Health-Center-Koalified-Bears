package com.kolafied.bears.HealthCare.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Table(name = "instruments")
@Entity
@EntityListeners(AuditingEntityListener.class)


public class Instruments {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instrument_id;

    private String instrument_name;

    private String uses;
    private Long i_count;

    private Long i_price;

    public Long getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(Long instrument_id) {
		this.instrument_id = instrument_id;
	}

	public String getInstrument_name() {
		return instrument_name;
	}

	public void setInstrument_name(String instrument_name) {
		this.instrument_name = instrument_name;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public Long getI_count() {
		return i_count;
	}

	public void setI_count(Long i_count) {
		this.i_count = i_count;
	}

	public Long getI_price() {
		return i_price;
	}

	public void setI_price(Long i_price) {
		this.i_price = i_price;
	}
}
