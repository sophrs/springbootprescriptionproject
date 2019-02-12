package com.soph.PrescriptionInventory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="stockcheck")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"},allowGetters = true)
public class StockCheckModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicationid;
	
	private Long storeid;
	private int stockno;
	private int minimumstockno;

	
	public Long getMedicationid() {
		return medicationid;
	}
	public void setMedicationid(Long medicationid) {
		this.medicationid = medicationid;
	}
	public Long getStoreid() {
		return storeid;
	}
	public void setStoreid(Long storeid) {
		this.storeid = storeid;
	}
	public int getStockno() {
		return stockno;
	}
	public void setStockno(int stockno) {
		this.stockno = stockno;
	}
	public int getMinimumstockno() {
		return minimumstockno;
	}
	public void setMinimumstockno(int minimumstockno) {
		this.minimumstockno = minimumstockno;
	}
	

	
}
