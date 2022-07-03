package com.example.bicycle_store;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicycle_store.adapter.MainProductRecyclerAdapter;
import com.example.bicycle_store.adapter.NewProductRecyclerAdapter;
import com.example.bicycle_store.model.Cart;
import com.example.bicycle_store.model.MainProduct;
import com.example.bicycle_store.model.NewProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnEventController {

    private RecyclerView recyclerView , mainProductRecyclerView;
    NewProductRecyclerAdapter newProductRecyclerAdapter;
    MainProductRecyclerAdapter mainProductRecyclerAdapter;
    List<NewProduct> newProductList;
    ArrayList<MainProduct> mainProductList;
    ArrayList<MainProduct> cartProduct;
    EditText searchBar;
    TextView lableNewProducts, labelOurProducts;
    ImageView cancelButton;

    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObservableImpl.INSTANCE.register(this);

        // recycler view new product
        lableNewProducts = findViewById(R.id.textView3);
        labelOurProducts = findViewById(R.id.textView2);
        recyclerView = findViewById(R.id.recyclerView1);
        cancelButton = findViewById(R.id.cancel_button);
        mainProductRecyclerView = findViewById(R.id.mainProductRecyclerView);
        searchBar = findViewById(R.id.searchBar);

        newProductList = new ArrayList<>();
        cartProduct = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset(getApplicationContext(),"data.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("xeDap");
            for(int i = 0 ; i <= 15 ; i += 3){
                JSONObject xedapData = jsonArray.getJSONObject(i);
                NewProduct product = new NewProduct(
                        xedapData.getString("name"),
                        xedapData.getString("price"),
                        xedapData.getString("category"),
                        xedapData.getString("image_url"),
                        xedapData.getString("description"),
                        xedapData.getString("id")
                );
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
                MainProduct product = new MainProduct(
                        xedapData.getString("name"),
                        xedapData.getString("price"),
                        xedapData.getString("category"),
                        xedapData.getString("image_url"),
                        xedapData.getString("description"),
                        xedapData.getString("id")
                        );
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
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        ImageView carView = findViewById(R.id.cart);
        carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                cart.setListProduct(cartProduct);
                Bundle b = new Bundle();
                b.putParcelable("key", cart);
                Intent intent = new Intent(MainActivity.this, CartScreen.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lableNewProducts.setVisibility(View.GONE);
                } else {
                    lableNewProducts.setVisibility(View.VISIBLE);
                }
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                lableNewProducts.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                labelOurProducts.setVisibility(View.GONE);
            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                if(timer != null)
                    timer.cancel();
            }
            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short

                if (s.length() >= 3) {
                    Log.d("HIGH PRIORITY", "PRINT submit");

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ObservableImpl.INSTANCE.postMessage(2, s.toString());
                        }
                    }, DELAY);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setText("");
                lableNewProducts.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                filter("");
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onEvent(int eventType, View view, Object data) {
        switch (eventType) {
            case 1:
                int index = (int) data;
                if (index == -1) return;
                MainProduct xedapData = this.mainProductList.get(index);
                MainProduct product = new MainProduct(
                        xedapData.getName(),
                        xedapData.getPrice(),
                        xedapData.getCategory(),
                        xedapData.getImage_url(),
                        xedapData.getDescription(),
                        xedapData.getId()
                );
                this.cartProduct.add(product);
                break;
            case 2:
                filter(data.toString());
                break;
            default:
                Log.d("HIGH PRIORITY", "NOT IMPLEMENT THIS EVENT");
        }
    }

    @Override
    public void onEvent(int eventType, View view, Object data, OnEventController eventController) {
        Log.d("tinguyen", "onEvent: RECEIVE EVENT 02");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObservableImpl.INSTANCE.unregister(this);
    }

    private void filter(String text) {
        if (text == "") {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    mainProductRecyclerAdapter.filterList(mainProductList);
                }
            });
        } else {
            // creating a new array list to filter our data.
            ArrayList<MainProduct> filteredlist = new ArrayList<>();

            // running a for loop to compare elements.
            for (MainProduct item : mainProductList) {
                // checking if the entered string matched with any item of our recycler view.
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item);
                }
            }
            if (filteredlist.isEmpty()) {
                // if no item is added in filtered list we are
                // displaying a toast message as no data found.
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            } else {
                // at last we are passing that filtered
                // list to our adapter class.
                this.runOnUiThread(new Runnable() {
                    public void run() {
                        mainProductRecyclerAdapter.filterList(filteredlist);
                    }
                });
            }
        }
    }
}

