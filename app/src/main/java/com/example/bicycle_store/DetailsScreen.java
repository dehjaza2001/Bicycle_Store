package com.example.bicycle_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsScreen extends AppCompatActivity {
    TextView productName, productPrice , productDes , productCate;
    ImageView productPic;
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
        // productCate = findViewById(R.id.detail_cate);
        productDes.setMovementMethod(new ScrollingMovementMethod());

        productName.setText(intent.getStringExtra("name"));
        productPrice.setText(intent.getStringExtra("price") + " VNĐ");
        productDes.setText(intent.getStringExtra("description"));
        productCate.setText(intent.getStringExtra("category"));
        Glide.with(this).load(intent.getStringExtra("image_url")).into(productPic);

    }
}