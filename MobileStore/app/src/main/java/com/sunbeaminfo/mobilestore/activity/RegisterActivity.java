package com.sunbeaminfo.mobilestore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.entity.User;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText editName, editEmail,editMobile, editPassword, editConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
    }

    public void register(View view){
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        if(password.equals(confirmPassword)){
            User user = new User();
            user.setName(editName.getText().toString());
            user.setEmail(editEmail.getText().toString());
            user.setMobile(editMobile.getText().toString());
            user.setPassword(password);
            RetrofitClient.getInstance().getApi().registerUser(user).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                    Toast.makeText(RegisterActivity.this, "Something went wrong while registration", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show();


    }

    public void cancel(View view){
        finish();
    }

}