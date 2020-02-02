package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.MedicinesDao;
import com.kolafied.bears.HealthCare.model.Medicines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/medicines"})
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class MedicinesController {
	
	   @Autowired
	    MedicinesDao medicines;

	    @GetMapping("/all")
	    public List<Medicines> getAll() {
	        return medicines.findAll();
	    }

	    @PostMapping("/add")
	    public Medicines addMedicines(@Valid @RequestBody Medicines medicinesAdd) {
	        return medicines.save(medicinesAdd);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Medicines> getById(@PathVariable(value = "id") Long id) {
	        return medicines.findById(id)
	                .map(record -> ResponseEntity.ok().body(record))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Medicines> updateMedicines(@PathVariable(value = "id") Long id,
	                                               @Valid @RequestBody Medicines medicinesUpdate) {

	        return medicines.findById(id)
	                .map(record -> {
	                    record.setMedicine_name(medicinesUpdate.getMedicine_name());
	                    record.setM_count(medicinesUpdate.getM_count());
	                   record.setM_price(record.getM_price());
	                    Medicines updated = medicines.save(record);
	                    return ResponseEntity.ok().body(updated);
	                }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
	        return medicines.findById(id)
	                .map(record -> {
	                    medicines.deleteById(id);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }

}
