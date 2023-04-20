package com.example.shamo.dto.transaction;

import java.util.List;

public class TransactionData {

	private Long id;
	private Long userId;
	private Float shippingPrice;
	private Float grandTotalPrice;
	private List<TransactionDetailData> trxDtlData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<TransactionDetailData> getTrxDtlData() {
		return trxDtlData;
	}

	public void setTrxDtlData(List<TransactionDetailData> trxDtlData) {
		this.trxDtlData = trxDtlData;
	}

}
