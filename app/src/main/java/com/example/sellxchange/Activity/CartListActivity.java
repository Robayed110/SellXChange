package com.example.sellxchange.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellxchange.Adapter.CartListAdapter;
import com.example.sellxchange.Helper.ManagementCart;
import com.example.sellxchange.Interface.ChangeNumberItemsListener;
import com.example.sellxchange.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;
TextView TotalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt,checkOut;
private double tax;
private ScrollView scrollView;
    View prs,setting;
    double total;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart =new ManagementCart(this);
        prs=findViewById(R.id.person);
        setting=findViewById(R.id.setting);
        checkOut=findViewById(R.id.CheckOut);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(CartListActivity.this, Payment.class);
                intent.putExtra("var",n1);
                intent.putExtra("payment",String.valueOf(total));
                startActivity(intent);
            }
        });
        prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(CartListActivity.this, Profile.class);
                intent.putExtra("var",n1);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,Setting.class));
                finish();
            }
        });
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.fac);
        View homeBtn=findViewById(R.id.home);

        /*floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });*/
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1=getIntent().getStringExtra("var");
                Intent intent=new Intent(CartListActivity.this, MainActivity.class);
                intent.putExtra("var",n1);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        //recyclerViewList=findViewById(R.id.recyclerView);
        TotalFeeTxt=findViewById(R.id.itemFeeTxt);
        taxTxt=findViewById(R.id.totalTaxTxt);
        deliveryTxt=findViewById(R.id.deliveryFeeTxt);
        totalTxt=findViewById(R.id.totalFeeTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView4);
        recyclerViewList=findViewById(R.id.cartView);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax=0.10;
        double delivery=1000;
        tax=Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
         total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100)/100;
        TotalFeeTxt.setText("Tk "+itemTotal);
        taxTxt.setText("Tk "+tax);
        deliveryTxt.setText("Tk "+delivery);
        totalTxt.setText("Tk "+total);

    }
}