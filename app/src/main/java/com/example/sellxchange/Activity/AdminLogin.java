package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sellxchange.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    private EditText ed1,ed2;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        ed1=findViewById(R.id.usernameAd);
        ed2=findViewById(R.id.passwordAd);
        Button login=findViewById(R.id.adminLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ed1.getText().toString().trim();
                String password=ed2.getText().toString();
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AdminLogin.this,"Please enter username and password ",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if mobile number is not registered

                                String getPassword=snapshot.child("password").getValue(String.class);
                                String getUsername=snapshot.child("username").getValue(String.class);

                                if(getPassword.equals(password) && getUsername.equals(username)){

                                    Toast.makeText(AdminLogin.this, "Log in successful!!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AdminLogin.this,Admin.class));
                                    finish();

                                }
                                else{
                                    Toast.makeText(AdminLogin.this, "Wrong password or username", Toast.LENGTH_SHORT).show();
                                }

                            }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}