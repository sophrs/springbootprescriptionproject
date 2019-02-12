package com.soph.PrescriptionInventory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="medication")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"},allowGetters = true)
public class MedicationModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicationid;
	
	@NotBlank
	private String medication_name;
	
	private String NHS_number;
	
	private Long manufacturerid;
//	
//	public MedicationModel(String medication_name, String Nhsnum, Long manufacturerid) {
//		this.medication_name = medication_name;
//		this.NHS_number = Nhsnum;
//		this.manufacturerid = manufacturerid;
//	}


	public Long getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(Long manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

	public Long getmedId() {
		return medicationid;
	}

	public void setmedId(Long id) {
		this.medicationid = id;
	}

	public String getMedicationName() {
		return medication_name;
	}


	public void setMedicationName(String medication_name) {
		this.medication_name = medication_name;
	}

	public String getNHSNumber() {
		return NHS_number;
	}

	public void setNHSNumber(String nhs_number) {
		this.NHS_number = nhs_number;
	}
	

}
