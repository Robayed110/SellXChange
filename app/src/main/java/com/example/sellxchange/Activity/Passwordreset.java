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

public class Passwordreset extends AppCompatActivity {
    private EditText ed1,ed2,ed3,ed4;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordreset);
        ed1=findViewById(R.id.username);
        ed2=findViewById(R.id.newpass1);
        ed3=findViewById(R.id.newpass2);
        ed4=findViewById(R.id.contact);
        Button btn1=findViewById(R.id.resetpassword);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =ed1.getText().toString().trim();
                String newpass1=ed2.getText().toString();
                String newpass2=ed3.getText().toString();
                String mobile=ed4.getText().toString();
                if(mobile.isEmpty()){
                    Toast.makeText(Passwordreset.this,"Please enter mobile number ",Toast.LENGTH_SHORT).show();
                }
                else if(mobile.length()<11){
                    Toast.makeText(Passwordreset.this,"Contact no should be 11 digit",Toast.LENGTH_SHORT).show();
                }
                else if(username.isEmpty() || newpass1.isEmpty() || newpass2.isEmpty()) {
                    Toast.makeText(Passwordreset.this,"Please enter username and password",Toast.LENGTH_SHORT).show();
                }
                else if (!newpass1.equals(newpass2)) {
                        Toast.makeText(Passwordreset.this, "Please confirmed your password ", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("userinfo").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if mobile number is not registered
                            if(snapshot.hasChild(mobile)){
                                String getMobile=snapshot.child(mobile).child("mobile").getValue(String.class);
                                String getUsername=snapshot.child(mobile).child("username").getValue(String.class);

                                if(getMobile.equals(mobile) && getUsername.equals(username)){
                                    String num=String.valueOf(mobile);
                                    databaseReference.child("userinfo").child(mobile).child("password").setValue(newpass1);
                                    Toast.makeText(Passwordreset.this, "Password reset successful!!", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(Passwordreset.this,Login.class);
                                    finish();

                                }
                                else{
                                    Toast.makeText(Passwordreset.this, "Invalid username", Toast.LENGTH_SHORT).show();
                                    ed1.requestFocus();
                                }

                            }

                            else{
                                Toast.makeText(Passwordreset.this, "Please SignUp", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                        //startActivity(new Intent(Passwordreset.this, Login.class));
                }
            }


        });


    }
}