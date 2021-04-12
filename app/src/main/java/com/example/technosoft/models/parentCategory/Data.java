package com.example.technosoft.models.parentCategory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("categoryList")
	private List<CategoryListItem> categoryList;

	public List<CategoryListItem> getCategoryList(){
		return categoryList;
	}
}