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
	private String medicationName;
	
	private String NHS_number;
	
	
	private Integer manufacturerid;
	
	public MedicationModel() {
		
		
	}
	public MedicationModel(String medicationName, String Nhsnum, Integer manufacturerid) {
		this.medicationName = medicationName;
		this.NHS_number = Nhsnum;
		this.manufacturerid = manufacturerid;
	}
	


	public Integer getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(Integer manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

	public Long getmedId() {
		return medicationid;
	}

	public void setmedId(Long id) {
		this.medicationid = id;
	}

	public String getMedicationName() {
		return medicationName;
	}


	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getNHSNumber() {
		return NHS_number;
	}

	public void setNHSNumber(String nhs_number) {
		this.NHS_number = nhs_number;
	}
	

}
