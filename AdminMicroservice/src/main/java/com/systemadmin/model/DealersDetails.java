package com.systemadmin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DealersDetails")
public class DealersDetails {
	
	@Id
	private int id;
	private String userName;
	private String emailId;
	private String mobileNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public DealersDetails(int id, String userName, String emailId, String mobileNumber) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
	}

	
	
}