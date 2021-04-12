package com.example.technosoft.models.subcategory;

import com.google.gson.annotations.SerializedName;

public class ResponsegetSubCategory{

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private int status;

	public Data getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}