package com.sunbeaminfo.mobilestore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.adapter.MobileListAdapter;
import com.sunbeaminfo.mobilestore.entity.Mobile;
import com.sunbeaminfo.mobilestore.utils.Constants;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrdersFragment extends Fragment {
    RecyclerView recyclerView;
    List<Mobile> mobileList;

    MobileListAdapter mobileListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_orders, container, false);
        return inflater.inflate(R.layout.fragment_mobile_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        mobileList = new ArrayList<>();
        mobileListAdapter = new MobileListAdapter(getContext(),mobileList);
        recyclerView.setAdapter(mobileListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllMobiles();
    }

    public void getAllMobiles(){
        int userId = getContext().getSharedPreferences(Constants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                        .getInt(Constants.ID,0);
        RetrofitClient.getInstance().getApi().getOrdersById(userId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray jsonArray = response.body().getAsJsonArray("data");
                    mobileList.clear();
                    for (JsonElement element: jsonArray) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        Mobile mobile = new Mobile();
                        mobile.setId(jsonObject.get("id").getAsInt());
                        mobile.setName(jsonObject.get("name").getAsString());
                        mobile.setRam(jsonObject.get("ram").getAsInt());
                        mobile.setStorage(jsonObject.get("storage").getAsInt());
                        mobile.setPrice(jsonObject.get("price").getAsDouble());
                        mobile.setCompany(jsonObject.get("company").getAsString());
                        mobile.setImage(jsonObject.get("image").getAsString());
                        mobileList.add(mobile);
                    }
                    mobileListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                Toast.makeText(getContext(), "Something went wrong while fetching mobiles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}