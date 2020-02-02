package com.kolafied.bears.HealthCare.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Table(name = "patient_history")
@Entity
@EntityListeners(AuditingEntityListener.class)

public class PatientHistory {
/*
    public PatientHistory(Long case_id, String diagnose_code, String insurance_id, @NotNull Date date_of_admission,
			Patient patient, Doctor doctor, Date createdAt, Date updatedAt) {
    	System.out.println("********************************");
    	System.out.println("Inside constructor of pateintHistory");
		this.case_id = case_id;
		this.diagnose_code = diagnose_code;
		this.insurance_id = insurance_id;
		this.date_of_admission = date_of_admission;
		this.patient = patient;
		List<PatientHistory> list=null;
		if(this.patient!=null || this.doctor!=null) {
			list=this.patient.getPatientHistory();
		if(list==null){
			list=new ArrayList<PatientHistory>();

		}
		}
		if(list!=null)
		list.add(this);
		this.patient.setPatientHistory(list);
		this.doctor = doctor;
		this.doctor.setPatientHistory(list);
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
*/

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long case_id;

    
    public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		List<PatientHistory> list=this.patient.getPatientHistory();
		if(list==null)
			list=new ArrayList<PatientHistory>();
		if(!list.contains(this))
			list.add(this);
		this.patient.setPatientHistory(list);
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		System.out.println("Inside doctor setter");
		this.doctor = doctor;
		List<PatientHistory> list=this.doctor.getPatientHistory();
		if(list==null)
			list=new ArrayList<PatientHistory>();
		if(!list.contains(this))
			list.add(this);
		for(PatientHistory hist:list) {
			System.out.println(hist.toString());
		}
		this.doctor.setPatientHistory(list);
	}


	private String diagnose_code;
    private String insurance_id;
    @NotNull
    private Date date_of_admission;
 
    @ManyToOne
    @JoinColumn(name="patientId",referencedColumnName="patientId")
    @JsonBackReference(value="patientId")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name="doctor_id",referencedColumnName="doctor_id")
    @JsonBackReference(value="doctor_id")
    private Doctor doctor;

 
	public Long getCase_id() {
		return case_id;
	}

	public void setCase_id(Long case_id) {
		this.case_id = case_id;
	}

	

	public String getDiagnose_code() {
		return diagnose_code;
	}

	public void setDiagnose_code(String diagnose_code) {
		this.diagnose_code = diagnose_code;
	}

	public String getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(String insurance_id) {
		this.insurance_id = insurance_id;
	}

	public Date getDate_of_admission() {
		return date_of_admission;
	}

	public void setDate_of_admission(Date date_of_admission) {
		this.date_of_admission = date_of_admission;
	}


	@Override
	public String toString() {
		String str="";
		if(patient!=null) {
			str="pateintId  ="+patient.getPatientId();
		}
		if(doctor!=null) {
			str+="doctor_id="+doctor.getDoctor_id();
		}
		str+="case_id="+case_id;
		return str;
	}



}
