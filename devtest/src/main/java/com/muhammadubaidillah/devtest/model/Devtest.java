package com.muhammadubaidillah.devtest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "devtest")

public class Devtest {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", length = 36, nullable = false)
	public String id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "phone")
	public String phone;
	
	@Column(name = "email")
	public String email;
	
	@Column(name = "user_id")
	public String userId;
	
	@Column(name = "created_date")
	public Date createdDate;
	
	@Column(name = "updated_date")
	public Date updatedDate;
	
	@Column(name = "created_by")
	public String createdBy;
	
	@Column(name = "updated_by")
	public String updatedBy;
}
