package com.sunbeaminfo.mobilestore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.entity.Mobile;
import com.sunbeaminfo.mobilestore.entity.Order;
import com.sunbeaminfo.mobilestore.utils.Api;
import com.sunbeaminfo.mobilestore.utils.Constants;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileDeatilsActivity extends AppCompatActivity {
        TextView textName,textCompany,textRam,textStorage,textPrice;
        ImageView imageMobile;
        Mobile mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_deatils);
        textName = findViewById(R.id.textName);
        textCompany = findViewById(R.id.textCompany);
        textRam = findViewById(R.id.textRam);
        textStorage = findViewById(R.id.textStorage);
        textPrice = findViewById(R.id.textPrice);
        imageMobile = findViewById(R.id.imageMobile);

        Intent intent = getIntent();
        mobile = (Mobile) intent.getSerializableExtra("mobile");
        textName.setText(mobile.getName());
        textCompany.setText(mobile.getCompany());
        textRam.setText("Ram : "+mobile.getRam());
        textStorage.setText("Storage :  "+mobile.getStorage());
        textPrice.setText("Price : "+mobile.getPrice());
        Glide.with(this).load(Api.BASEURL+"/images/"+mobile.getImage()).into(imageMobile);
    }

    public void buy(View view){
        int userId = getSharedPreferences(Constants.PREFERENCE_FILE_NAME,MODE_PRIVATE)
                .getInt(Constants.ID,0);
        Order order = new Order(mobile.getId(),userId);
        RetrofitClient.getInstance().getApi().addOrder(order).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().get("status").getAsString().equals("success")){
                    Toast.makeText(MobileDeatilsActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(MobileDeatilsActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                Toast.makeText(MobileDeatilsActivity.this, "Something went wrong while adding the order", Toast.LENGTH_SHORT).show();
            }
        });

    }
}