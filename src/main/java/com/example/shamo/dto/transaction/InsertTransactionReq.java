package com.example.shamo.dto.transaction;

import java.util.List;

public class InsertTransactionReq {

	private Long userId;
	private Float shippingPrice;
	private Float grandTotalPrice;
	private List<InsertTransactionDetailReq> trxDtl;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Float getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Float shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Float getGrandTotalPrice() {
		return grandTotalPrice;
	}

	public void setGrandTotalPrice(Float grandTotalPrice) {
		this.grandTotalPrice = grandTotalPrice;
	}

	public List<InsertTransactionDetailReq> getTrxDtl() {
		return trxDtl;
	}

	public void setTrxDtl(List<InsertTransactionDetailReq> trxDtl) {
		this.trxDtl = trxDtl;
	}

}
