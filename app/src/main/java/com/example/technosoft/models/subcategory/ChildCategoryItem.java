package com.example.technosoft.models.subcategory;

import com.google.gson.annotations.SerializedName;

public class ChildCategoryItem{

	@SerializedName("ChildId")
	private int childId;

	@SerializedName("name")
	private String name;

	@SerializedName("Category_id")
	private String categoryId;

	public int getChildId(){
		return childId;
	}

	public String getName(){
		return name;
	}

	public String getCategoryId(){
		return categoryId;
	}
}