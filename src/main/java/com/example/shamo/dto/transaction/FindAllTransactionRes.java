package com.example.shamo.dto.transaction;

import java.util.List;

public class FindAllTransactionRes {

	private List<TransactionData> data;

	public List<TransactionData> getData() {
		return data;
	}

	public void setData(List<TransactionData> data) {
		this.data = data;
	}

}
