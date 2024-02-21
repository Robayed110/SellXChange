package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,mobile,address;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        address=findViewById(R.id.address);
        ed1=findViewById(R.id.regusername);
        ed2=findViewById(R.id.regmail);
        ed3=findViewById(R.id.regPassword1);
        ed4=findViewById(R.id.regPassword2);
        mobile=findViewById(R.id.regMobile);
        TextView btn=findViewById(R.id.haveaccount);
        Button register=findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 =ed1.getText().toString().trim();
                String mail=ed2.getText().toString().trim();
                String password1=ed3.getText().toString().trim();
                String password2=ed4.getText().toString().trim();
                String mobile1=mobile.getText().toString().trim();
                String address1=address.getText().toString().trim();

                if(username1.isEmpty() || mail.isEmpty()) {
                    Toast.makeText(Register.this,"Please enter username,mail and password ",Toast.LENGTH_SHORT).show();
                    if(username1.isEmpty())ed1.requestFocus();
                    else ed2.requestFocus();
                }
                else if(mobile1.isEmpty()){
                    Toast.makeText(Register.this,"Please enter mobile number ",Toast.LENGTH_SHORT).show();
                    mobile.requestFocus();
                }
                else if(mobile1.length()<11){
                    mobile.setError("contact no should be 11 digit");
                    mobile.requestFocus();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    ed2.setError("Enter a valid email");
                    ed2.requestFocus();
                    return;
                }
                else if(password1.length()<6){
                    ed3.setError("password length should be 6 digit");
                    ed3.requestFocus();
                    return;
                }
                else if(password2.length()<6){
                    ed4.setError("password length should be 6 digit");
                    ed4.requestFocus();
                    return;
                }
                else if(!password1.equals(password2)){
                    Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    ed4.requestFocus();
                }


                else {
                    databaseReference.child("userinfo").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if mobile number is not registered
                            if(snapshot.hasChild(mobile1)){
                                Toast.makeText(Register.this, "This number is already registered!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("userinfo").child(mobile1).child("username").setValue(username1);
                                databaseReference.child("userinfo").child(mobile1).child("email").setValue(mail);
                                databaseReference.child("userinfo").child(mobile1).child("password").setValue(password1);
                                databaseReference.child("userinfo").child(mobile1).child("address").setValue(address1);
                                databaseReference.child("userinfo").child(mobile1).child("mobile").setValue(mobile1);

                                Toast.makeText(Register.this, "Register successful!", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Register.this, Login.class));
                                finish();
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