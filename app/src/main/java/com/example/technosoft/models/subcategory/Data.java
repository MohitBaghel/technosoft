package com.example.technosoft.models.subcategory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("PrimaryCategory")
	private List<PrimaryCategoryItem> primaryCategory;

	public List<PrimaryCategoryItem> getPrimaryCategory(){
		return primaryCategory;
	}
}