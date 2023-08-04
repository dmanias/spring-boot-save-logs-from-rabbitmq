package com.jtampakakis.bluemaster.savelogs.security.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jtampakakis.bluemaster.savelogs.utils.Validator;


@Entity 
@Table(name = "customers", schema = "blog")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;
	
	public Customer() {}
	public Customer(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void ValidateID() {
		Validator.IdValidator("Customer ID", id+"", false);
	}
}
