package com.jtampakakis.bluemaster.savelogs.security.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jtampakakis.bluemaster.savelogs.utils.Validator;

@Entity 
@Table(name = "hardwares", schema = "blog")
public class Hardware implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String serialNumber;
	
	@ManyToOne
	private Customer customer;
	
	public Hardware() {}
	public Hardware(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void ValidateID() {
		Validator.IdValidator("Hardware ID", id+"", false);
	}
}
