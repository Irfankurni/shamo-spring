package com.example.shamo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(name = "shipping_price")
	private float shippingPrice;

	@Column(name = "grand_total_price")
	private float grandTotalPrice;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public float getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(float shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public float getGrandTotalPrice() {
		return grandTotalPrice;
	}

	public void setGrandTotalPrice(float grandTotalPrice) {
		this.grandTotalPrice = grandTotalPrice;
	}

}
