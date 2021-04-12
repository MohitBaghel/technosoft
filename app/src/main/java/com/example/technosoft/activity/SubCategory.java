package com.example.technosoft.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.technosoft.Interface.MvpView;
import com.example.technosoft.R;
import com.example.technosoft.adapter.SubAdapter;
import com.example.technosoft.models.subcategory.ChildCategoryItem;
import com.example.technosoft.models.subcategory.PrimaryCategoryItem;
import com.example.technosoft.models.subcategory.ResponsegetSubCategory;
import com.example.technosoft.retrofit.ApiClient;
import com.example.technosoft.retrofit.ApiInterface;
import com.example.technosoft.utils.BasicUtils;
import com.example.technosoft.utils.Global;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategory extends AppCompatActivity implements MvpView {
    TextView men_tv,women_tv,kids_tv;
    RecyclerView men_rv,women_rv,kids_rv;
    SubAdapter adapter;
    GridLayoutManager grid;
    List<ChildCategoryItem> menlist=new ArrayList<>();
    List<ChildCategoryItem> womenlist=new ArrayList<>();
    List<ChildCategoryItem> childlist=new ArrayList<>();
    String id="";
    ProgressBar progressBar;
    String token= Global.Token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        getSupportActionBar().hide();
        try{
            men_tv=findViewById(R.id.men_tv);
            women_tv=findViewById(R.id.women_tv);
            kids_tv=findViewById(R.id.kids_tv);
            men_rv=findViewById(R.id.men_rv);
            women_rv=findViewById(R.id.women_rv);
            kids_rv=findViewById(R.id.kids_rv);
            progressBar=findViewById(R.id.progressBar);
            adapter=new SubAdapter(this,menlist,SubCategory.this);
            grid=new GridLayoutManager(this,1);
            men_rv.setAdapter(adapter);
            men_rv.setLayoutManager(grid);
//            id= getIntent().getIntExtra("id",0);
            id= String.valueOf(getIntent().getIntExtra("id",0));
            if(BasicUtils.checkNetworkConnection(this)) {
                getSubCategory(id);
            }else{
                Toast.makeText(this, getResources().getString(R.string.alertInternet), Toast.LENGTH_SHORT).show();
            }
            men_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    men_rv.setVisibility(View.VISIBLE);
                }
            });
            women_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    women_rv.setVisibility(View.VISIBLE);
                }
            });
            women_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kids_rv.setVisibility(View.VISIBLE);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getSubCategory(String id) {

        try{
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
            Call<ResponsegetSubCategory> call=api.getSubCategory("Bearer"+token,id);
            call.enqueue(new Callback<ResponsegetSubCategory>() {
                @Override
                public void onResponse(Call<ResponsegetSubCategory> call, Response<ResponsegetSubCategory> response) {
                    progressBar.setVisibility(View.GONE);
                    if(response.body().getData().getPrimaryCategory().get(0).getChildCategory().size()>0 && response.body().getStatus() ==200){
                        try{
                            Log.d("response",String.valueOf(response.body().getData().getPrimaryCategory().size()));
                            menlist=response.body().getData().getPrimaryCategory().get(0).getChildCategory();
                            adapter.updateListItem(menlist);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(SubCategory.this, "No Data Found!!", Toast.LENGTH_SHORT).show();
                    }
//                    if(response.body().getData().getPrimaryCategory().get(1).getChildCategory().size()>0 && response.body().getStatus() ==200){
//                        try{
//                            Log.d("response",String.valueOf(response.body().getData().getPrimaryCategory().size()));
//                            menlist=response.body().getData().getPrimaryCategory().get(0).getChildCategory();
//                            adapter.updateListItem(menlist);
//
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }else { Toast.makeText(SubCategory.this, "No Data Found!!", Toast.LENGTH_SHORT).show();
//                    }
//                    if(response.body().getData().getPrimaryCategory().get(2).getChildCategory().size()>0 && response.body().getStatus() ==200){
//                        try{
//                            Log.d("response",String.valueOf(response.body().getData().getPrimaryCategory().size()));
//                            menlist=response.body().getData().getPrimaryCategory().get(0).getChildCategory();
//                            adapter.updateListItem(menlist);
//
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }else { Toast.makeText(SubCategory.this, "No Data Found!!", Toast.LENGTH_SHORT).show();
//                    }


                    }
                @Override
                public void onFailure(Call<ResponsegetSubCategory> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    t.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void getClickPostion(int position, String name) {

    }
}




//  progressBar!!.visibility = View.VISIBLE
//          val api = ApiClient.getClient().create(ApiInterface::class.java)
//        val call = api.getSubCategory("Bearer$token", id)
//        call.enqueue(object : Callback<ResponsegetSubCategory> {
//        override fun onResponse(call: Call<ResponsegetSubCategory>, response: Response<ResponsegetSubCategory>) {
//        progressBar!!.visibility = View.GONE
//        if (response.body()!!.data.primaryCategory[0].childCategory.size > 0 && response.body()!!.status == 200) {
//        try {
//        Log.d("response", response.body()!!.data.primaryCategory.size.toString())
//        menlist = response.body()!!.data.primaryCategory[0].childCategory
//        adapter!!.updateListItem(menlist)
//        } catch (e: Exception) {
//        e.printStackTrace()
//        }
//        } else {
//        Toast.makeText(this@SubCategory, "No Data Found!!", Toast.LENGTH_SHORT).show()
//        }