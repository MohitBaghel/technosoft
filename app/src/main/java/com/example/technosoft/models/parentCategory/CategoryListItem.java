package com.example.technosoft.models.parentCategory;

import com.google.gson.annotations.SerializedName;

public class CategoryListItem{

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("Category_id")
	private int categoryId;

	public String getImage(){
		return image;
	}

	public String getName(){
		return name;
	}

	public int getCategoryId(){
		return categoryId;
	}
}