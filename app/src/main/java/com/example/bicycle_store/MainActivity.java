package com.example.bicycle_store;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicycle_store.adapter.MainProductRecyclerAdapter;
import com.example.bicycle_store.adapter.NewProductRecyclerAdapter;
import com.example.bicycle_store.model.MainProduct;
import com.example.bicycle_store.model.NewProduct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView , mainProductRecyclerView;
    NewProductRecyclerAdapter newProductRecyclerAdapter;
    MainProductRecyclerAdapter mainProductRecyclerAdapter;
    List<NewProduct> newProductList;
    List<MainProduct> mainProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // recycler view new product
        recyclerView = findViewById(R.id.recyclerView1);
        mainProductRecyclerView = findViewById(R.id.mainProductRecyclerView);
        newProductList = new ArrayList<>();

        NewProduct product1 = new NewProduct("XE ĐẠP FORNIX FN26","4.490.000","xeDiaHinh","https://salt.tikicdn.com/cache/400x400/ts/product/ce/4f/09/3e031d17b80d8779ace992a9d57363dd.jpg.webp","");
        NewProduct product2 = new NewProduct("XE ĐẠP FORNIX FN26","4.490.000","xeDiaHinh","https://salt.tikicdn.com/cache/400x400/ts/product/ce/4f/09/3e031d17b80d8779ace992a9d57363dd.jpg.webp","");
        NewProduct product3 = new NewProduct("XE ĐẠP FORNIX FN26","4.490.000","xeDiaHinh","https://salt.tikicdn.com/cache/400x400/ts/product/ce/4f/09/3e031d17b80d8779ace992a9d57363dd.jpg.webp","");
        NewProduct product4 = new NewProduct("XE ĐẠP FORNIX FN26","4.490.000","xeDiaHinh","https://salt.tikicdn.com/cache/400x400/ts/product/ce/4f/09/3e031d17b80d8779ace992a9d57363dd.jpg.webp","");
        newProductList.add(product1);
        newProductList.add(product2);
        newProductList.add(product3);
        newProductList.add(product4);

        newProductRecyclerAdapter = new NewProductRecyclerAdapter(this,newProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(newProductRecyclerAdapter);
        newProductRecyclerAdapter.notifyDataSetChanged();

        mainProductList = new ArrayList<>();
        MainProduct mainProduct1 = new MainProduct("Xe điện cân bằng Mini Robot","3.450.000","xe điện cân bằng","https://salt.tikicdn.com/cache/400x400/ts/product/25/c1/5d/fd5b7f240a037b74426aa188873cdb37.jpg.webp","");
        MainProduct mainProduct2 = new MainProduct("Xe điện cân bằng Mini Robot","3.450.000","xe điện cân bằng","https://salt.tikicdn.com/cache/400x400/ts/product/25/c1/5d/fd5b7f240a037b74426aa188873cdb37.jpg.webp","");
        MainProduct mainProduct3 = new MainProduct("Xe điện cân bằng Mini Robot","3.450.000","xe điện cân bằng","https://salt.tikicdn.com/cache/400x400/ts/product/25/c1/5d/fd5b7f240a037b74426aa188873cdb37.jpg.webp","");
        MainProduct mainProduct4 = new MainProduct("Xe điện cân bằng Mini Robot","3.450.000","xe điện cân bằng","https://salt.tikicdn.com/cache/400x400/ts/product/25/c1/5d/fd5b7f240a037b74426aa188873cdb37.jpg.webp","");
        MainProduct mainProduct5 = new MainProduct("Xe điện cân bằng Mini Robot","3.450.000","xe điện cân bằng","https://salt.tikicdn.com/cache/400x400/ts/product/25/c1/5d/fd5b7f240a037b74426aa188873cdb37.jpg.webp","");

        mainProductList.add(mainProduct1);
        mainProductList.add(mainProduct2);
        mainProductList.add(mainProduct3);
        mainProductList.add(mainProduct4);
        mainProductList.add(mainProduct5);

        mainProductRecyclerAdapter = new MainProductRecyclerAdapter(this,mainProductList);
        mainProductRecyclerView.setHasFixedSize(true);
        mainProductRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mainProductRecyclerView.setAdapter(mainProductRecyclerAdapter);
        mainProductRecyclerAdapter.notifyDataSetChanged();


    }
}