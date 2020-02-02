package com.kolafied.bears.HealthCare.controller;

import com.kolafied.bears.HealthCare.dao.BillDao;
import com.kolafied.bears.HealthCare.model.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/bill"})
@CrossOrigin(origins = "*", allowedHeaders = "*")



public class BillController {
	@Autowired
	   BillDao bill;

	    @GetMapping("/all")
	    public List<Bill> getAll() {
	        return bill.findAll();
	    }

	    @PostMapping("/add")
	    public Bill addBill(@Valid @RequestBody Bill billAdd) {
	        return bill.save(billAdd);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Bill> getById(@PathVariable(value = "id") Long id) {
	        return bill.findById(id)
	                .map(record -> ResponseEntity.ok().body(record))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Bill> updateBill(@PathVariable(value = "id") Long id,
	                                               @Valid @RequestBody Bill billUpdate) {

	        return bill.findById(id)
	                .map(record -> {
	                    record.setbDate(billUpdate.getbDate());
	                    //record.setPatientId(billUpdate.getPatientId());
	                    record.setEmail(billUpdate.getEmail());
	                    record.setEmail(billUpdate.getEmail());
	                    record.setbAmt(billUpdate.getbAmt());
	                    
	                    Bill updated = bill.save(record);
	                    return ResponseEntity.ok().body(updated);
	                }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
	        return bill.findById(id)
	                .map(record -> {
	                	bill.deleteById(id);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }

}
