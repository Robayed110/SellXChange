package com.example.sellxchange.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sellxchange.Activity.Category;
import com.example.sellxchange.Domain.CategoriesDomain;
import com.example.sellxchange.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoriesDomain> categoriesDomains;

    public CategoryAdapter(ArrayList<CategoriesDomain> categoriesDomains) {
        this.categoriesDomains = categoriesDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_catagory,parent,false);
        return new  ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.setCategoryName(categoriesDomains.get(position).getTitle());
     String picUrl=" ";
     switch (position){
             case 0:{
             picUrl="cat_1";
             break;
         }
         case 1:{
             picUrl="cat_2";
             break;
         }
         case 2:{
             picUrl="cat_3";
             break;
         }
         case 3:{
             picUrl="cat_4";
             break;
         }
         case 4:{
             picUrl="cat_5";
             break;
         }
     }
     int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
     Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categoryPic);
//     holder.categoryPic.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//             Intent intent=new Intent(holder.itemView.getContext(), Ferrari.class);
//             intent.putExtra("object",  categoriesDomains.get(position));
//             holder.itemView.getContext().startActivity(intent);
//         }
//     });
    }

    @Override
    public int getItemCount() {
        return categoriesDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryName =itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout =itemView.findViewById(R.id.mainLayout);
        }
        private void setCategoryName(String name) {
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(), Category.class);
                    intent.putExtra("object",  name);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
