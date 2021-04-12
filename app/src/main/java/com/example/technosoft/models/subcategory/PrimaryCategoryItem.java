package com.example.technosoft.models.subcategory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PrimaryCategoryItem{

	@SerializedName("parent_id")
	private int parentId;

	@SerializedName("name")
	private String name;

	@SerializedName("Category_id")
	private String categoryId;

	@SerializedName("child_category")
	private List<ChildCategoryItem> childCategory;

	public int getParentId(){
		return parentId;
	}

	public String getName(){
		return name;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public List<ChildCategoryItem> getChildCategory(){
		return childCategory;
	}
}