package com.example.bicycle_store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicycle_store.adapter.CartProductRecyclerAdapter;
import com.example.bicycle_store.model.Cart;
import com.example.bicycle_store.model.MainProduct;

import java.util.ArrayList;
import java.util.List;

public class CartScreen extends AppCompatActivity {

    private RecyclerView cartProductRecyclerView;
    CartProductRecyclerAdapter cartProductRecyclerAdapter;
    List<MainProduct> cartProductList;

    ImageView backButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tinguyen", "DID start1");

        setContentView(R.layout.cart_screen);

        Intent intent = getIntent();

        cartProductRecyclerView = findViewById(R.id.mainProductRecyclerView);
        cartProductList = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        Cart obj = (Cart) b. getParcelable("key");
//
        Log.d("tinguyen", obj.getListProduct().toString());
        cartProductList = obj.getListProduct();
        Log.d("tinguyen", cartProductList.toString());


//        try {
//            JSONObject jsonObject = new JSONObject(JsonDataFromAsset(getApplicationContext(),"data.json"));
//            JSONArray jsonArray = jsonObject.getJSONArray("xeDap");
            for(int i = 0 ; i < cartProductList.size() ; i++){
                MainProduct xedapData = cartProductList.get(i);
                Log.d("TAG",
                        xedapData.getName());
//                mainProductList.add(product);
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        cartProductRecyclerAdapter = new CartProductRecyclerAdapter(this,cartProductList);
        cartProductRecyclerView.setHasFixedSize(true);
        cartProductRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        cartProductRecyclerView.setAdapter(cartProductRecyclerAdapter);
        cartProductRecyclerAdapter.notifyDataSetChanged();

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        // get location for the chain store
//        ImageView locationView = findViewById(R.id.location);
//        locationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CartScreen.this, MapsActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
