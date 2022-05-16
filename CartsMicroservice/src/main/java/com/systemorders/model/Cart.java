package com.systemorders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CartList")
public class Cart {
	@Id
	private String id;
	private String dealerId;
	private String cropName;
	private String cropType;
	private double quantity;
	private double price;
	private double totalQuantity;
	private double totalPrice;
	private boolean PaymentStatus;
	
	public Cart() {
		super();
	}

	public Cart(String id, String dealerId, String cropName, String cropType, double quantity, double price,
			double totalQuantity, double totalPrice) {
		super();
		this.id = id;
		this.dealerId = dealerId;
		this.cropName = cropName;
		this.cropType = cropType;
		this.quantity = quantity;
		this.price = price;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.PaymentStatus=false;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
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

	public double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

public void setPaymentStatus(Boolean status)
{
	this.PaymentStatus=status;
}
    
}
