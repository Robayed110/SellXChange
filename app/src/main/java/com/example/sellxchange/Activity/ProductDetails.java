package com.example.sellxchange.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sellxchange.Domain.PopularDomain;
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.Helper.ManagementCart;
import com.example.sellxchange.R;

public class ProductDetails extends AppCompatActivity {
    private TextView title,price,addToCart,description,orderNumberTxt;
    private ImageView pic,plus,minus;
    private PopularDomain object;
    private ProductDomain object2;
    int orderNumber=1;
    int f=0;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        managementCart=new ManagementCart(this);
        String n1=getIntent().getStringExtra("var1");
        int N1=Integer.parseInt(n1);
        //Toast.makeText(this, N1, Toast.LENGTH_SHORT).show();
        if(N1==1) {

            initView();

            getBundle();
            // getBun();
        }
        else {
            initView();
            getBun();
        }

    }

   private void getBundle()
   {
       object = (PopularDomain) getIntent().getSerializableExtra("object");

       //int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
       //Glide.with(this).load(drawableResourceId).into(pic);
       Glide.with(this).load(object.getPic()).into(pic);
       title.setText(object.getTitle());

       price.setText("BDT " + object.getPrice());
       //Toast.makeText(this, String.valueOf(object.getTitle()), Toast.LENGTH_SHORT).show();
       description.setText(object.getDescription());
       orderNumberTxt.setText(String.valueOf(orderNumber));
       plus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               orderNumber = orderNumber + 1;
               orderNumberTxt.setText(String.valueOf(orderNumber));
           }
       });
       minus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (orderNumber > 1) {
                   orderNumber = orderNumber - 1;
               }
               orderNumberTxt.setText(String.valueOf(orderNumber));
           }

       });

       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               object.setNumberInCart(orderNumber);
               managementCart.insertCar(object);
           }
       });
   }
    private void getBun()
    {
        object2 = (ProductDomain) getIntent().getSerializableExtra("object2");
        Glide.with(this).load(object2.getPic()).into(pic);
       // Glide.with(holder.itemView.getContext()).load(productDomain.getPic()).into(holder.pic);
        /*int drawableResourceId = this.getResources().getIdentifier(object2.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(pic);*/
        title.setText(object2.getTitle());

        price.setText("BDT " + object2.getPrice());
        //Toast.makeText(this, String.valueOf(object2.getTitle()), Toast.LENGTH_SHORT).show();
        description.setText(object2.getDescription());
        orderNumberTxt.setText(String.valueOf(orderNumber));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderNumber = orderNumber + 1;
                orderNumberTxt.setText(String.valueOf(orderNumber));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderNumber > 1) {
                    orderNumber = orderNumber - 1;
                }
                orderNumberTxt.setText(String.valueOf(orderNumber));
            }

        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object2.setNumberInCart(orderNumber);
                managementCart.insertCar2(object2);
            }
        });
    }

    private void initView() {
        addToCart=findViewById(R.id.addToCart);
        title=findViewById(R.id.titleText);
        price=findViewById(R.id.price2);
        description=findViewById(R.id.descriptiontxt);
        orderNumberTxt=findViewById(R.id.ordernumber);
        pic=findViewById(R.id.carpic);
        plus=findViewById(R.id.plusBtn);
        minus=findViewById(R.id.minusBtn);
    }
}