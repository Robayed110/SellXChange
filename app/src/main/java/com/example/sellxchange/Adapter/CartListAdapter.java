package com.example.sellxchange.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sellxchange.Activity.CartListActivity;
import com.example.sellxchange.Domain.PopularDomain;
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.Helper.ManagementCart;
import com.example.sellxchange.Interface.ChangeNumberItemsListener;
import com.example.sellxchange.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<PopularDomain> popularDomains;
    private ArrayList<ProductDomain> productDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<ProductDomain> productDomains, ManagementCart managementCart, ChangeNumberItemsListener changeNumberItemsListener) {
        this.productDomains = productDomains;
        this.managementCart = managementCart;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    public CartListAdapter(ArrayList<PopularDomain> popularDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.popularDomains = popularDomains;
        this.managementCart =new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(popularDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(popularDomains.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf(Math.round(popularDomains.get(position).getNumberInCart()*popularDomains.get(position).getPrice()*100)/100));
        holder.num.setText(String.valueOf(popularDomains.get(position).getNumberInCart()));
        //int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        //Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
        Glide.with(holder.itemView.getContext()).load(popularDomains.get(position).getPic()).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 managementCart.plusNumberCar(popularDomains, position, new ChangeNumberItemsListener() {
                     @Override
                     public void changed() {
                         notifyDataSetChanged();
                         changeNumberItemsListener.changed();
                     }
                 });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberCar(popularDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.orderNumberCart);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
