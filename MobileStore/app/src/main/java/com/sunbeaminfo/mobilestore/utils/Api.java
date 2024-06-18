package com.sunbeaminfo.mobilestore.utils;

import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.entity.Order;
import com.sunbeaminfo.mobilestore.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    public static final String BASEURL="http://172.18.4.167:8080";

    @POST("/users/login")
    public Call<JsonObject> loginUser(@Body User user);

    @GET("/users/{id}")
    public Call<JsonObject> getUserById(@Path("id") int id);

    @POST("/users")
    public Call<JsonObject> registerUser(@Body User user);

    @GET("/mobiles")
    public Call<JsonObject> getAllMobiles();

    @POST("/orders")
    public Call<JsonObject> addOrder(@Body Order order);

    @GET("/orders/user/{id}")
    public Call<JsonObject> getOrdersById(@Path("id") int id);
}
