package com.example.sellxchange.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.sellxchange.Activity.ProductDetails;
import com.example.sellxchange.Domain.PopularDomain;
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<PopularDomain> popularDomains;


    public PopularAdapter(ArrayList<PopularDomain> popularDomains) {
        this.popularDomains= popularDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new  ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     holder.title.setText(popularDomains.get(position).getTitle());
     holder.price.setText(String.valueOf(popularDomains.get(position).getPrice()));


     //int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
     //Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
     Glide.with(holder.itemView.getContext()).load(popularDomains.get(position).getPic()).into(holder.pic);



     holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String num="1";
             Intent intent=new Intent(holder.itemView.getContext(), ProductDetails.class);
             intent.putExtra("var1",num);
             intent.putExtra("object",popularDomains.get(position));
             holder.itemView.getContext().startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        ImageView addbtn;
        TextView price;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title =itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            addbtn =itemView.findViewById(R.id.addbtn);
            price =itemView.findViewById(R.id.pricetxt);

        }
    }
}
