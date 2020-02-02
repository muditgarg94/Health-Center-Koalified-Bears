package com.kolafied.bears.HealthCare.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Table(name = "patient")
@Entity

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String email;
    private int age;
    private String sex;
    private String mobile;
    private String address;
    
    @OneToMany(mappedBy="patient",fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    @JsonManagedReference(value="patientId")
    private List<PatientHistory> patientHistory;
    
    @OneToOne(mappedBy="patientBill",fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    @JsonManagedReference(value="patientId")
    private Bill Bill;
    
//    @OneToOne(mappedBy="patientStaff", cascade =  CascadeType.ALL)
//    @JsonManagedReference(value="patientSt")
//    private List<Staff> staffList;

	public Bill getBill() {
		return Bill;
	}

	public void setBill(Bill bill) {
		Bill = bill;
	}

//	public List<Staff> getStaffList() {
//		return staffList;
//	}
//
//	public void setStaffList(List<Staff> staffList) {
//		this.staffList = staffList;
//	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public List<PatientHistory> getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(List<PatientHistory> patientHistory) {
		this.patientHistory = patientHistory;
	}


}
