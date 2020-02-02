package com.kolafied.bears.HealthCare.controller;


import com.kolafied.bears.HealthCare.dao.PatientHistoryDao;
import com.kolafied.bears.HealthCare.encrypt.Encryption;
import com.kolafied.bears.HealthCare.model.Patient;
import com.kolafied.bears.HealthCare.model.PatientHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/patienthistory"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientHistoryController {
	final String secretKey="database";
    @Autowired
    PatientHistoryDao patient;

    @GetMapping("/all")
    public List<PatientHistory> getAllNotes() {
    	List<PatientHistory> list=patient.findAll();
    	List<PatientHistory> newlist= new ArrayList<PatientHistory>();
    	for(PatientHistory hist: list) {
    		hist.setDiagnose_code(Encryption.decrypt(hist.getDiagnose_code(),secretKey));
    		hist.setInsurance_id(Encryption.decrypt(hist.getInsurance_id(),secretKey));
    		//System.out.println(hist.toString());
    		newlist.add(hist);
    	}
        return newlist;
    }

    @PostMapping("/add")
    public PatientHistory addPatient(@Valid @RequestBody PatientHistory patientAdd) {
    	System.out.println("*******************");
    	System.out.println(patientAdd.toString());
    	System.out.println("*******************");
    	String diag=patientAdd.getDiagnose_code();
    	String insuranceId=patientAdd.getInsurance_id();
    	patientAdd.setDiagnose_code(Encryption.encrypt(diag,secretKey));
    	patientAdd.setInsurance_id(Encryption.encrypt(insuranceId,secretKey));
        return patient.save(patientAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientHistory> getNoteById(@PathVariable(value = "id") Long id) {
        return patient.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientHistory> updatePatient(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody PatientHistory patientUpdate) {

        return patient.findById(id)
                .map(record -> {
                  // record.setPatient_id(patientUpdate.getPatient_id());
                    record.setDiagnose_code(patientUpdate.getDiagnose_code());
                    record.setInsurance_id(patientUpdate.getInsurance_id());
                    record.setDate_of_admission(patientUpdate.getDate_of_admission());
                   // record.setDoctor_id(patientUpdate.getDoctor_id());
                    PatientHistory updated = patient.save(record);
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
