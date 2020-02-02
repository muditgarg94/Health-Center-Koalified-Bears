package com.kolafied.bears.HealthCare.controller;


import com.kolafied.bears.HealthCare.dao.DoctorDao;
import com.kolafied.bears.HealthCare.dao.PatientDao;
import com.kolafied.bears.HealthCare.model.Doctor;
import com.kolafied.bears.HealthCare.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/doctors"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {

    @Autowired
    DoctorDao doctor;

    @GetMapping("/all")
    public List<Doctor> getAllNotes() {
        return doctor.findAll();
    }

    @PostMapping("/add")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctorAdd) {
        return doctor.save(doctorAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getNoteById(@PathVariable(value = "id") Long id) {
        return doctor.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Doctor doctorUpdate) {

        return doctor.findById(id)
                .map(record -> {
                    record.setName(doctorUpdate.getName());
                    record.setSpecialization(doctorUpdate.getSpecialization());
                    record.setMobile(doctorUpdate.getMobile());
                    record.setEmail(doctorUpdate.getEmail());
                    record.setAddress(doctorUpdate.getAddress());
                    Doctor updated = doctor.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        return doctor.findById(id)
                .map(record -> {
                    doctor.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
