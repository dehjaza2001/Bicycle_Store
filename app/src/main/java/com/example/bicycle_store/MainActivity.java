package com.example.bicycle_store;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicycle_store.adapter.NewProductRecyclerAdapter;
import com.example.bicycle_store.model.NewProduct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    NewProductRecyclerAdapter newProductRecyclerAdapter;
    List<NewProduct> newProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_ac73dcdfe08c4c7f6639ed41d3f50bc8);

        recyclerView = findViewById(R.id.recyclerView1);
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

    }
}