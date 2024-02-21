package com.example.sellxchange.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class PaymentGateway extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-c3b1d-default-rtdb.firebaseio.com/");

    TextView bkash,nagad,mastercard;
    EditText account,transaction;
    String getAddress=null;
    String getName=null;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        account=findViewById(R.id.account);
        transaction=findViewById(R.id.transaction);
        mastercard=findViewById(R.id.textView15);

        String n=getIntent().getStringExtra("N");
        int N=(int)Integer.parseInt(n);
        if(N==1){
            TextView bkash = findViewById(R.id.textView15);
            bkash.setText("Your bKash Account Number");
        }
        else if(N==2){
            TextView bkash = findViewById(R.id.textView15);
            bkash.setText("Your Nagad Account Number");
        }
        else{
            TextView bkash = findViewById(R.id.textView15);
            bkash.setText("Enter Your Debit/Credit Card No");
        }
        Button confirm =findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account1 =account.getText().toString();
                String transaction1=transaction.getText().toString();

                String n1 = getIntent().getStringExtra("var");
                String payment = getIntent().getStringExtra("payment");
                databaseReference.child("userinfo").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        getAddress = snapshot.child(n1).child("address").getValue(String.class);
                        getName = snapshot.child(n1).child("username").getValue(String.class);
                        //Toast.makeText(PaymentGateway.this, String.valueOf(getName), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (account1.isEmpty() || transaction1.isEmpty()) {
                    Toast.makeText(PaymentGateway.this, "Enter account no and Trans ID", Toast.LENGTH_SHORT).show();
                    account.requestFocus();
                }
                else {


                databaseReference.child("order").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        {
                            Toast.makeText(PaymentGateway.this, "Thank You!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(PaymentGateway.this, "Your Payment is Successful!!", Toast.LENGTH_SHORT).show();

                            databaseReference.child("order").child(n1).child("mobile no").setValue(n1);
                            databaseReference.child("order").child(n1).child("amount").setValue(payment);
                            databaseReference.child("order").child(n1).child("address").setValue(getAddress);
                            databaseReference.child("order").child(n1).child("name").setValue(getName);
                            databaseReference.child("order").child(n1).child("account no").setValue(account1);
                            databaseReference.child("order").child(n1).child("transaction id").setValue(transaction1);



                            Intent intent = new Intent(PaymentGateway.this, MainActivity.class);
                            intent.putExtra("var", n1);
                            startActivity(intent);
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