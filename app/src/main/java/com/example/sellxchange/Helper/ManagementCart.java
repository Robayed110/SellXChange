package com.example.sellxchange.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.sellxchange.Domain.PopularDomain;
import com.example.sellxchange.Domain.ProductDomain;
import com.example.sellxchange.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }
    public void insertCar (PopularDomain item){
        ArrayList<PopularDomain>listCar=getListCart();
         boolean existAlready =false;
         int n=0;
         for(int i=0;i<listCar.size();i++){
             if(listCar.get(i) == null) continue;
             if(listCar.get(i).getTitle().equals(item.getTitle())){
                 existAlready=true;
                 n=i;
                 break;
             }
         }
         
         if(existAlready){
             listCar.get(n).setNumberInCart(item.getNumberInCart());

         }
         else {
             listCar.add(item);
         }
         tinyDB.putListObject("CartList",listCar);
        Toast.makeText(context,"Added to your Cart",Toast.LENGTH_SHORT).show();
    }
    public void insertCar2 (ProductDomain item){
        ArrayList<ProductDomain>listCar=getListCart2();
        boolean existAlready =false;
        int n=0;
        for(int i=0;i<listCar.size();i++){
            if(listCar.get(i) == null) continue;
            if(listCar.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;

                break;
            }
        }

        if(existAlready){
            listCar.get(n).setNumberInCart(item.getNumberInCart());

        }
        else {
            listCar.add(item);
        }
        tinyDB.putListObject2("CartList",listCar);
        Toast.makeText(context,"Added to your Cart",Toast.LENGTH_SHORT).show();
    }
    
    public ArrayList<PopularDomain>getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public ArrayList<ProductDomain>getListCart2(){
        return tinyDB.getListObject2("CartList");
    }
    public void plusNumberCar(ArrayList<PopularDomain>listCar, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listCar.get(position).setNumberInCart(listCar.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listCar);
        changeNumberItemsListener.changed();
    }

    public void minusNumberCar(ArrayList<PopularDomain>listcar,int position,ChangeNumberItemsListener changeNumberItemsListener){
        if(listcar.get(position).getNumberInCart()==1){
            listcar.remove(position);
        }
        else {
            listcar.get(position).setNumberInCart(listcar.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listcar);
        changeNumberItemsListener.changed();
    }
    public  Double getTotalFee(){
        ArrayList<PopularDomain> listcar=getListCart();
        double fee=0;
        for(int i=0;i<listcar.size();i++){
            fee=fee+(listcar.get(i).getPrice()*listcar.get(i).getNumberInCart());
        }
        return fee;
    }
}
