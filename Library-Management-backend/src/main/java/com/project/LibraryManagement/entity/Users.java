package com.project.LibraryManagement.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity

@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
public class Users {

	@Id
	@GeneratedValue
	private Long userId;
	
	@Column(nullable = false)
	private String email;
	
//	@Column(nullable = false)
//	private String userType;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	private Integer loginCount = 0;
	
	private String ssoType;
	
	private Date loginAt;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
		
	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}




	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getLogin_count() {
		return loginCount;
	}



	public void setLogin_count(Integer login_count) {
		this.loginCount = login_count;
	}



	public String getSsoType() {
		return ssoType;
	}



	public void setSsoType(String ssoType) {
		this.ssoType = ssoType;
	}



	public Date getLoginAt() {
		return loginAt;
	}



	public void setLoginAt(Date loginAt) {
		this.loginAt = loginAt;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	@PrePersist
	public void onSave() {
		
		Date currentDateTime = new Date();
		
		
			this.createdAt = currentDateTime;
		    this.updatedAt = currentDateTime;
		
	}
	
	@PostPersist
	public void onUpdate() {
		
		Date currentDateTime = new Date();
		this.updatedAt = currentDateTime;


	}

	
	
	
 
}
