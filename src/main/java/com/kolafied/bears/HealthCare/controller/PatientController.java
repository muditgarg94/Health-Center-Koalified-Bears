package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.PatientDao;
import com.kolafied.bears.HealthCare.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/patients"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    PatientDao patient;

    @GetMapping("/all")
    public List<Patient> getAllNotes() {
        return  patient.findAll();
    }

    @PostMapping("/add")
    public Patient addPatient(@Valid @RequestBody Patient patientAdd) {
        return patient.save(patientAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getNoteById(@PathVariable(value = "id") Long id) {
        return patient.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long id,
                           @Valid @RequestBody Patient patientUpdate) {

        return patient.findById(id)
                .map(record -> {
                    record.setFirstName(patientUpdate.getFirstName());
                    record.setLastName(patientUpdate.getLastName());
                    record.setEmail(patientUpdate.getEmail());
                    record.setAge(patientUpdate.getAge());
                    record.setSex(patientUpdate.getSex());
                    record.setMobile(patientUpdate.getMobile());
                    record.setAddress(patientUpdate.getAddress());
                    Patient updated = patient.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        return patient.findById(id)
                .map(record -> {
                    patient.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
