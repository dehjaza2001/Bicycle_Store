package com.example.bicycle_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailsScreen extends AppCompatActivity {
    TextView productName, productPrice , productDes , productCate;
    ImageView productPic , backButton ;
    Button addToCart, buyNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        Intent intent = getIntent();

        productName = findViewById(R.id.detail_name);
        productPrice = findViewById(R.id.detail_price);
        productDes = findViewById(R.id.detail_des);
        productPic = findViewById(R.id.detail_image_view);
        productCate = findViewById(R.id.detail_cate);
        backButton = findViewById(R.id.back_button);
        productDes.setMovementMethod(new ScrollingMovementMethod());

        productName.setText(intent.getStringExtra("name"));
        productPrice.setText(intent.getStringExtra("price") + " VNĐ");
        productDes.setText(intent.getStringExtra("description"));
        productCate.setText(intent.getStringExtra("category"));
        Glide.with(this).load(intent.getStringExtra("image_url")).into(productPic);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent1 = new Intent(DetailsScreen.this,MainActivity.class);
//                finish();
//                startActivity(intent1);

            }
        });

        addToCart = findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsScreen.this, "ADDED TO CART", Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(DetailsScreen.this,MainActivity.class);
//                startActivity(intent1)
                ObservableImpl.INSTANCE.postMessage(1, getIntent().getIntExtra("index", -1));
                finish();
            }
        });

        buyNow = findViewById(R.id.buyNow);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsScreen.this, "THANKS FOR PURCHASED", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}