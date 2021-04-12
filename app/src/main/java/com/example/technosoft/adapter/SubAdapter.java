package com.example.technosoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.technosoft.Interface.MvpView;
import com.example.technosoft.R;
import com.example.technosoft.activity.SubCategory;
import com.example.technosoft.models.parentCategory.CategoryListItem;
import com.example.technosoft.models.subcategory.ChildCategoryItem;
import com.example.technosoft.models.subcategory.PrimaryCategoryItem;

import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {
    Context context;
    List<ChildCategoryItem> list;
    MvpView mvpView;

    public SubAdapter(Context context, List<ChildCategoryItem> list, MvpView mvpView) {
        this.context = context;
        this.list = list;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public SubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_category_adapter,parent,false);
        return new SubAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapter.ViewHolder holder, int position) {

        holder.category.setText(list.get(position).getName());
    }

    public void updateListItem(List<ChildCategoryItem> list) {
        try {
            this.list = list;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.men_tv);
        }
    }
}
