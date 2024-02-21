package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.Adapter.CategoryAdapter;
import com.example.sellxchange.Adapter.PopularAdapter;
import com.example.sellxchange.Domain.PopularDomain;
import com.example.sellxchange.Domain.CategoriesDomain;
import com.example.sellxchange.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");

    private RecyclerView.Adapter adapter1,adapter2;
    private RecyclerView recyclerViewcategorieslist,recyclerViewpopularlist;
    TextView textView,name;
    Button btncart;
    View prs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewcatagories();
        recyclerViewpopularlist();
        bottomNavigation();
        name=findViewById(R.id.textView);
        prs=findViewById(R.id.person);
        String n1=getIntent().getStringExtra("var");
        databaseReference.child("userinfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String getUsername=snapshot.child(n1).child("username").getValue(String.class);

                name.setText("Hi,"+getUsername);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, Profile.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });





    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.fac);
        View homeBtn=findViewById(R.id.home);
        View setting =findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Setting.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(MainActivity.this, CartListActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });

    }

    private void recyclerViewpopularlist() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false);
        recyclerViewpopularlist =findViewById(R.id.recyclerView2);
        recyclerViewpopularlist.setLayoutManager(linearLayoutManager);
        String getPic_1,getPic_2,getPic_3,getPic_4,getPic_5,getPic_6;
        ArrayList<PopularDomain> popularDomains =new ArrayList<>();
        databaseReference.child("popular").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // check if mobile number is not registered

                String getPic1=snapshot.child("pic1").getValue(String.class);
                String getPic2=snapshot.child("pic2").getValue(String.class);
                String getPic3=snapshot.child("pic3").getValue(String.class);
                String getPic4=snapshot.child("pic4").getValue(String.class);
                String getPic5=snapshot.child("pic5").getValue(String.class);
                String getPic6=snapshot.child("pic6").getValue(String.class);
                popularDomains.add(new PopularDomain("Mercedes-56",getPic1,"ranked #1 in Luxury Midsize SUVs ",56150.00));
                popularDomains.add(new PopularDomain("Mercedes-09",getPic2,"Mercedes-Benz S–Class ",6150.00));
                popularDomains.add(new PopularDomain("Ferrari_classic",getPic3,"ranked #1 in Luxury Midsize SUVs ",56150.00));
                popularDomains.add(new PopularDomain("Mercedes_type-c",getPic4,"MercedesBenz-GLE-Coupe-Right-Front-Three-Quarter ",91150.00));

                popularDomains.add(new PopularDomain("BMW",getPic5,"ranked #1 in Luxury Midsize SUVs ",56150.00));
                popularDomains.add(new PopularDomain("Lamborghini",getPic6,"ranked #1 in Luxury Midsize SUVs ",56150.00));

                adapter2 = new PopularAdapter(popularDomains);
                recyclerViewpopularlist.setAdapter(adapter2);

            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    private void recyclerViewcatagories() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false);
        recyclerViewcategorieslist =findViewById(R.id.recyclerView);
        recyclerViewcategorieslist.setLayoutManager(linearLayoutManager);

        ArrayList<CategoriesDomain> categotieslist =new ArrayList<>();
        categotieslist.add(new CategoriesDomain("BMW","cat_1"));
        categotieslist.add(new CategoriesDomain("Lamborghini","cat_2"));
        categotieslist.add(new CategoriesDomain("Ford","cat_3"));
        categotieslist.add(new CategoriesDomain("Ferrari","cat_4"));
        categotieslist.add(new CategoriesDomain("MercedesBenz","cat_5"));

        adapter1 =new CategoryAdapter(categotieslist);
        recyclerViewcategorieslist.setAdapter(adapter1);


    }
}