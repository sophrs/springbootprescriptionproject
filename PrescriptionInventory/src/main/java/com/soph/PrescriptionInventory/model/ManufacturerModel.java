package com.soph.PrescriptionInventory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "manufacturer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "creationDate", "lastModified" }, allowGetters = true)
public class ManufacturerModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long manufacturerid;

	private String manufacturer_name;
	private String phone_number;
	private String address;	
	
	
	public Long getmanId() {
		return manufacturerid;
	}

	public void setId(Long id) {
		this.manufacturerid = id;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
