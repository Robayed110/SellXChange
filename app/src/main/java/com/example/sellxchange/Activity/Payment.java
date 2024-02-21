package com.example.sellxchange.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Payment extends AppCompatActivity {
    TextView TotalCost;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        View prs,setting;
        ImageView bKash,nagad,mastercard;
        bKash=findViewById(R.id.bkash);
        nagad= findViewById(R.id.nagad);
        mastercard= findViewById(R.id.mastercard);
        //setContentView(R.layout.activity_payment);
        prs=findViewById(R.id.person);
        setting=findViewById(R.id.setting);
        String n1=getIntent().getStringExtra("var");
        String payment=getIntent().getStringExtra("payment");
        TextView totalCost = findViewById(R.id.total_cost);
        totalCost.setText("Total Cost BDT: "+payment);
       mastercard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int  n=3;
               Intent intent=new Intent(Payment.this, PaymentGateway.class);
               intent.putExtra("N",String.valueOf(n));
               intent.putExtra("var",n1);
               intent.putExtra("payment",String.valueOf(payment));
               startActivity(intent);
           }
       });
       bKash.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int  n=1;

              // Intent intn =new Intent(Payment.this,PaymentGateway.class);



               Intent intent=new Intent(Payment.this, PaymentGateway.class);
               intent.putExtra("N",String.valueOf(n));
               intent.putExtra("var",n1);
               intent.putExtra("payment",String.valueOf(payment));
               startActivity(intent);
           }
       });
       nagad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int  n=2;
               Intent intent=new Intent(Payment.this, PaymentGateway.class);
               intent.putExtra("N",String.valueOf(n));
               intent.putExtra("var",n1);
               intent.putExtra("payment",String.valueOf(payment));
               startActivity(intent);
           }
       });

        prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(Payment.this, Profile.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payment.this,Setting.class));
                finish();
            }
        });
        //nt intent=new Intent(Payment.this, Profile.class);
        //intent.putExtra("var",n1);
        //intent.putExtra("payment",String.valueOf(totalTxt));
        //startActivity(intent);

        bottomNavigation();

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.fac);
        View homeBtn=findViewById(R.id.home);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(Payment.this, CartListActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(Payment.this, MainActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
                finish();
            }
        });
    }
}