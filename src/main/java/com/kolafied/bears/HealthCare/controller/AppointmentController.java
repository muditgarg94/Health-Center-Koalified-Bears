package com.kolafied.bears.HealthCare.controller;


import com.kolafied.bears.HealthCare.dao.AppointmentDao;
import com.kolafied.bears.HealthCare.dao.DoctorDao;
import com.kolafied.bears.HealthCare.model.Appointment;
import com.kolafied.bears.HealthCare.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/appointment"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppointmentController {


    @Autowired
    AppointmentDao appointment;

    @GetMapping("/all")
    public List<Appointment> getAll() {
        return appointment.findAll();
    }


    @PostMapping("/add")
    public Appointment addAppointment(@Valid @RequestBody Appointment appointmentAdd) {
        return appointment.save(appointmentAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable(value = "id") Long id) {
        return appointment.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody Appointment appointmentUpdate) {

        return appointment.findById(id)
                .map(record -> {
                    record.setName(appointmentUpdate.getName());
                    record.setDeptId(appointmentUpdate.getDeptId());
                    record.setEmail(appointmentUpdate.getEmail());
                    record.setMobile(appointmentUpdate.getMobile());
                    record.setTime(appointmentUpdate.getTime());
                    record.setDate(appointmentUpdate.getDate());
                    Appointment updated = appointment.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        return appointment.findById(id)
                .map(record -> {
                    appointment.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
