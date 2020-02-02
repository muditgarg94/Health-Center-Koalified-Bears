package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.StaffDao;
import com.kolafied.bears.HealthCare.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/staff"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StaffController {


    @Autowired
    StaffDao staff;

    @GetMapping("/all")
    public List<Staff> getAllNotes() {
        return staff.findAll();
    }

    @PostMapping("/add")
    public Staff addStaff(@Valid @RequestBody Staff staffAdd) {
        return staff.save(staffAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getNoteById(@PathVariable(value = "id") Long id) {
        return staff.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Staff staffUpdate) {

        return staff.findById(id)
                .map(record -> {
                    record.setName(staffUpdate.getName());
                    record.setType(staffUpdate.getType());
                    record.setDob(staffUpdate.getDob());
                    record.setAddress(staffUpdate.getAddress());
                    Staff updated = staff.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        return staff.findById(id)
                .map(record -> {
                	staff.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

