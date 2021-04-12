package com.example.technosoft.retrofit;

import com.example.technosoft.models.parentCategory.ResponsegetParentCategory;
import com.example.technosoft.models.subcategory.ResponsegetSubCategory;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("ParentCategory")
    Call<ResponsegetParentCategory> getParentCategory(@Header("Authorization") String token);

    @GET("PrimaryCategory")
    Call<ResponsegetSubCategory> getSubCategory(@Header("Authorization")String token,
                                                @Query("parent_id") String id);

}
