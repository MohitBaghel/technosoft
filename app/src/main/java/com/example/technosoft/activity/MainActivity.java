package com.example.technosoft.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.technosoft.R;
import com.example.technosoft.adapter.TechAdapter;
import com.example.technosoft.models.parentCategory.CategoryListItem;
import com.example.technosoft.Interface.MvpView;
import com.example.technosoft.models.parentCategory.ResponsegetParentCategory;
import com.example.technosoft.retrofit.ApiClient;
import com.example.technosoft.retrofit.ApiInterface;
import com.example.technosoft.utils.BasicUtils;
import com.example.technosoft.utils.Global;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MvpView {
    RecyclerView recyclerView;
    TechAdapter adapter;
    List<CategoryListItem> list=new ArrayList<>();
    GridLayoutManager grid;
    ProgressBar progressBar;
    String Token= Global.Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        try{
            progressBar=findViewById(R.id.progressBar);
            recyclerView=findViewById(R.id.fashion_rv);
            adapter=new TechAdapter(this,list,MainActivity.this);
            grid=new GridLayoutManager(this,1);
            recyclerView.setLayoutManager(grid);
            recyclerView.setAdapter(adapter);
            if(BasicUtils.checkNetworkConnection(this)){
                ParentCategory();
            }else{
                Toast.makeText(this, "Please try again Later", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ParentCategory() {
        try {
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
            Call<ResponsegetParentCategory> call=api.getParentCategory("Bearer "+Token);
            call.enqueue(new Callback<ResponsegetParentCategory>() {
                @Override
                public void onResponse(Call<ResponsegetParentCategory> call, Response<ResponsegetParentCategory> response) {
                    progressBar.setVisibility(View.GONE);
                    try {
//                        if (response.body().getStatus()==200 && response.body().getData().getCategoryList().size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            list=response.body().getData().getCategoryList();
                            adapter.updateListItem(list);
//                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponsegetParentCategory> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getClickPostion(int position, String name) {
        try {
            Intent i=new Intent(this, SubCategory.class);
            i.putExtra("id",list.get(position).getCategoryId());
            startActivity(i);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}