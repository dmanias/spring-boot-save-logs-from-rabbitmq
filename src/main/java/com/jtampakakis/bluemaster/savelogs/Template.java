package com.jtampakakis.bluemaster.savelogs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jtampakakis.bluemaster.savelogs.repos.TemplateRepo;
import com.jtampakakis.bluemaster.savelogs.utils.Validator;

@Entity 
@Table(name = "templates", schema = "blog")
@JsonInclude(Include.NON_EMPTY)
public class Template implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String hardware;

	public Template() {}
	public Template(int id) {
		this.id=id;
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
	
	public Template Create(TemplateRepo repo) {
		repo.save(this);
		return this;
	}
	
	public Template Update(TemplateRepo repo) {
		repo.save(this);
		return this;
	}
	
	public Template Delete(TemplateRepo repo) {
		repo.delete(this);
		return this;
	}
	
	public void ValidateCreation(TemplateRepo repo) {
		// TODO Auto-generated method stub
		
	}
	public void ValidateUpdate(TemplateRepo repo) {
		// TODO Auto-generated method stub
		
	}
	public void ValidateId() {
		Validator.IdValidator("Template ID", id+"", false);
		
	}
}
