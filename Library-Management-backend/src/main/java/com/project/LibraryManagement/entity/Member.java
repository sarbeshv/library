package com.project.LibraryManagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Member {
	
	@GeneratedValue
	@Id
	private long id;
	
	@Nonnull
	private String userName;

	private String gender;
	
	@Nonnull
	private  String email;
	
	@Nonnull
	private String phoneNumber;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public Member(long id, String userName, String gender, String email, String phoneNumber) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", userName=" + userName + ", gender=" + gender + ", email=" + email
				+ ", phoneNumber=" + phoneNumber +"]";
	}



	
}