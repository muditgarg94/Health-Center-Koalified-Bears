package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.InstrumentsDao;
import com.kolafied.bears.HealthCare.dao.MedicinesDao;
import com.kolafied.bears.HealthCare.model.Instruments;
import com.kolafied.bears.HealthCare.model.Medicines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/instruments"})
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class InstrumentsController {
	
	   @Autowired
	   InstrumentsDao instruments;

	    @GetMapping("/all")
	    public List<Instruments> getAll() {
	        return instruments.findAll();
	    }

	    @PostMapping("/add")
	    public Instruments addInstruments(@Valid @RequestBody Instruments instrumentsAdd) {
	        return instruments.save(instrumentsAdd);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Instruments> getById(@PathVariable(value = "id") Long id) {
	        return instruments.findById(id)
	                .map(record -> ResponseEntity.ok().body(record))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Instruments> updateInstruments(@PathVariable(value = "id") Long id,
	                                               @Valid @RequestBody Instruments instrumentsUpdate) {

	        return instruments.findById(id)
	                .map(record -> {
	                    record.setInstrument_name(instrumentsUpdate.getInstrument_name());
	                    record.setUses(record.getUses());
	                    record.setI_count(instrumentsUpdate.getI_count());
	                    record.setI_price(record.getI_price());
	                    Instruments updated = instruments.save(record);
	                    return ResponseEntity.ok().body(updated);
	                }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
	        return instruments.findById(id)
	                .map(record -> {
	                	instruments.deleteById(id);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }

}
