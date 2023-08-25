package com.example.canteenm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.security.AccessControlContext;
import java.util.List;

public class VegAdapter extends RecyclerView.Adapter<VegAdapter.myViewHolder> {

    final Context context;
    final List<VegModel> list;

    public VegAdapter(Context context, List<VegModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VegAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.foodimg);
        holder.foodname.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.type.setText(list.get(position).getFoodtype());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView foodimg;
        TextView foodname;
        TextView price;
        TextView type;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimg=itemView.findViewById(R.id.img1);
            foodname=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            type=itemView.findViewById(R.id.foodtype);

        }
    }

//    ArrayList<VegModel>vegModel;
//    Context context;
//
//    public VegAdapter(Context context, ArrayList<VegModel> vegModel) {
//        this.vegModel = vegModel;
//        this.context =context;
//    }
//    public static class myViewHolder extends RecyclerView.ViewHolder{
//        CircleImageView image;
//        TextView name,price,foodtype;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//            image = (CircleImageView)itemView.findViewById(R.id.img1);
//            name = (TextView)itemView.findViewById(R.id.name);
//            price = (TextView)itemView.findViewById(R.id.price);
//            foodtype = (TextView)itemView.findViewById(R.id.foodtype);
//        }
//    }
//
//    @NonNull
//    @Override
//    public VegAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
//        return new myViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
//
//        holder.name.setText(vegModel.get(position).getName());
//        holder.price.setText(vegModel.get(position).getPrice());
//        holder.foodtype.setText(vegModel.get(position).getFoodtype());
//
//        Glide.with(holder.image.getContext())
//                .load(vegModel.get(position).getImage())
//                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
//                .circleCrop()
//                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
//                .into(holder.image);
//    }
//
//    @Override
//    public int getItemCount() {
//        return vegModel.size();
//    }
//
//
//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull VegModel model) {
//        holder.name.setText(model.getName());
//        holder.price.setText(model.getPrice());
//        holder.foodtype.setText(model.getFoodtype());
//
//        Glide.with(holder.image.getContext())
//                .load(model.getImage())
//                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
//                .circleCrop()
//                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
//                .into(holder.image);
//
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
//        return new myViewHolder(view);
//    }
//
//    class myViewHolder extends RecyclerView.ViewHolder{
//        CircleImageView image;
//        TextView name,price,foodtype;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//            image = (CircleImageView)itemView.findViewById(R.id.img1);
//            name = (TextView)itemView.findViewById(R.id.name);
//            price = (TextView)itemView.findViewById(R.id.price);
//            foodtype = (TextView)itemView.findViewById(R.id.foodtype);
//        }
//    }

}
