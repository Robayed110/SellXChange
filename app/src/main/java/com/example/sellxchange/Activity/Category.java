package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sellxchange.Adapter.ProductAdapter;
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ProductAdapter productAdapter;
    ArrayList<ProductDomain> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        String title = getIntent().getStringExtra("object");
        TextView titleName = findViewById(R.id.titleName);
        titleName.setText(title);
        recyclerView=findViewById(R.id.recyclerView5);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(linearLayoutManager);


        list=new ArrayList<>();
        //productAdapter =new ProductAdapter(Category.this, list);
        //recyclerView.setAdapter(productAdapter);

        databaseReference.child("collection").child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ProductDomain productDomain=dataSnapshot.getValue(ProductDomain.class);
                    if(productDomain == null) continue;
                    /*String img = dataSnapshot.child("pic").getValue(String.class);
                    productDomain.setImage(img);*/
                    list.add(productDomain);
                }

                productAdapter =new ProductAdapter(list,Category.this);
                recyclerView.setAdapter(productAdapter);
                //productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*databaseReference.child("collection").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                 for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                     ProductDomain productDomain=dataSnapshot.getValue(ProductDomain.class);
                     list.add(productDomain);
                 }
                 productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}