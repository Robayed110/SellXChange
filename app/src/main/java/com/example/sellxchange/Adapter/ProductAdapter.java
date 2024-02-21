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
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductDomain> list;

    public ProductAdapter(ArrayList<ProductDomain> list,Context context) {

        this.list = list;
        this.context = context;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.viewholder_product,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ProductDomain productDomain=list.get(position);
        holder.title.setText(productDomain.getTitle());
        holder.price.setText(String.valueOf(productDomain.getPrice()));
        holder.description.setText(productDomain.getDescription());


        String imageUri=null;
        imageUri=productDomain.getPic();
        //Picasso.get().load(imageUri).into(holder.pic);
        Glide.with(holder.itemView.getContext()).load(productDomain.getPic()).into(holder.pic);

        //holder.pic.setImageURI(productDomain.getPhoto());
        String picUrl="plus";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.addBtn);
        /*int drawResourceId = holder.itemView.getContext().getResources().getIdentifier(String.valueOf(list.get(position)),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawResourceId).into(holder.pic);*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num="2";
                Intent intent=new Intent(holder.itemView.getContext(),ProductDetails.class);
                intent.putExtra("var1",num);
                intent.putExtra("object2",list.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView addBtn,pic;
        TextView title,price,description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.imageView);
            description=itemView.findViewById(R.id.descripTxt);
            addBtn=itemView.findViewById(R.id.addImage);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.pricetxt);
        }
    }
}
