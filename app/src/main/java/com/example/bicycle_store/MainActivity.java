package com.example.bicycle_store;

//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicycle_store.adapter.MainProductRecyclerAdapter;
import com.example.bicycle_store.adapter.NewProductRecyclerAdapter;
import com.example.bicycle_store.model.MainProduct;
import com.example.bicycle_store.model.NewProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();



        // recycler view new product
        recyclerView = findViewById(R.id.recyclerView1);
        mainProductRecyclerView = findViewById(R.id.mainProductRecyclerView);
        newProductList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset(getApplicationContext(),"data.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("xeDap");
            for(int i = 0 ; i <= 15 ; i += 3){
                JSONObject xedapData = jsonArray.getJSONObject(i);
                NewProduct product = new NewProduct(xedapData.getString("name"),xedapData.getString("price"),xedapData.getString("category"),xedapData.getString("image_url"),xedapData.getString("description"));
                newProductList.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        newProductRecyclerAdapter = new NewProductRecyclerAdapter(this,newProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(newProductRecyclerAdapter);
        newProductRecyclerAdapter.notifyDataSetChanged();

        mainProductList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset(getApplicationContext(),"data.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("xeDap");
            for(int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject xedapData = jsonArray.getJSONObject(i);
                MainProduct product = new MainProduct(xedapData.getString("name"),xedapData.getString("price"),xedapData.getString("category"),xedapData.getString("image_url"),xedapData.getString("description"));
                mainProductList.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainProductRecyclerAdapter = new MainProductRecyclerAdapter(this,mainProductList);
        mainProductRecyclerView.setHasFixedSize(true);
        mainProductRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mainProductRecyclerView.setAdapter(mainProductRecyclerAdapter);
        mainProductRecyclerAdapter.notifyDataSetChanged();

        // get location for the chain store

        ImageView locationView = findViewById(R.id.location);
        locationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private String JsonDataFromAsset(Context context, String fileName) {
        String  json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int sizeOfFile = inputStream.available();
            byte[]  bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;


    }
}