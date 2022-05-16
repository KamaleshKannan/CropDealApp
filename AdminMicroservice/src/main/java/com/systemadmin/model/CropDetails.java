package com.systemadmin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Cropslist")
public class CropDetails {
	
	@Id
	private String id;
	private String farmerId;
	private String cropName;
	private String cropType;
	private double quantity;
	private double price;
	private String address;
	
	public CropDetails() {
		super();
	}

	public CropDetails(String id, String farmerId, String cropName, String cropType, double quantity, double price,
			String address) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.cropName = cropName;
		this.cropType = cropType;
		this.quantity = quantity;
		this.price = price;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

	
	
}