package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.DepartmentDao;

import com.kolafied.bears.HealthCare.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/department"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {

    @Autowired
    DepartmentDao department;

    @GetMapping("/all")
    public List<Department> getAll() {
        return department.findAll();
    }

    @PostMapping("/add")
    public Department addDepartment(@Valid @RequestBody Department departmentAdd) {
        return department.save(departmentAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable(value = "id") String id) {
        return department.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") String id,
                                               @Valid @RequestBody Department departmentUpdate) {

        return department.findById(id)
                .map(record -> {
                    record.setDept_name(departmentUpdate.getDept_name());
                    record.setDept_desc(departmentUpdate.getDept_desc());
                    Department updated = department.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") String id) {
        return department.findById(id)
                .map(record -> {
                    department.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
