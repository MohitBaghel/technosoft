package com.example.technosoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.technosoft.R;
import com.example.technosoft.models.parentCategory.CategoryListItem;
import com.example.technosoft.Interface.MvpView;

import java.util.List;
import java.util.zip.Inflater;

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> {
   Context context;
    List<CategoryListItem>list;
    MvpView mvpView;

    public TechAdapter(Context context, List<CategoryListItem> list, MvpView mvpView) {
        this.context = context;
        this.list = list;
        this.mvpView = mvpView;
    }

    public void updateListItem(List<CategoryListItem> list) {
        try {
            this.list = list;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    @Override
    public TechAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fashion_category_adapter,parent,false);
        return new TechAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        Glide.with(context)
                .load(list.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.fashion_item);
            icon=itemView.findViewById(R.id.icon_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mvpView.getClickPostion(list.get(getAdapterPosition()).getCategoryId(),"id");
                }
            });
            }
    }
}
