package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        TextView name,email,mobile,address;
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.Proaddress);
        String mobile1=getIntent().getStringExtra("var");
        bottomNavigation();
        databaseReference.child("userinfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // check if mobile number is not registered

               String getMobile=snapshot.child(mobile1).child("mobile").getValue(String.class);
                String getUsername=snapshot.child(mobile1).child("username").getValue(String.class);
                String getEmail=snapshot.child(mobile1).child("email").getValue(String.class);
                String getAddress=snapshot.child(mobile1).child("address").getValue(String.class);
               name.setText(getUsername);
               email.setText(getEmail);
               mobile.setText(getMobile);
               address.setText(getAddress);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.fac);
        View homeBtn=findViewById(R.id.home);
        View setting=findViewById(R.id.setting);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(Profile.this, CartListActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(Profile.this, MainActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Setting.class));
            }
        });
    }
}