package com.sunbeaminfo.mobilestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.activity.MobileDeatilsActivity;
import com.sunbeaminfo.mobilestore.entity.Mobile;
import com.sunbeaminfo.mobilestore.utils.Api;

import java.util.List;

public class MobileListAdapter extends RecyclerView.Adapter<MobileListAdapter.MobileViewHolder> {

    Context context;
    List<Mobile> mobileList;

    public MobileListAdapter(Context context, List<Mobile> mobileList) {
        this.context = context;
        this.mobileList = mobileList;
    }

    @NonNull
    @Override
    public MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mobiles_list,null);
        return new MobileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileViewHolder holder, int position) {
        Mobile mobile = mobileList.get(position);
        holder.textName.setText(mobile.getName());
        holder.textPrice.setText("Rs : "+mobile.getPrice());
        Glide.with(context).load(Api.BASEURL+"/images/"+mobile.getImage()).into(holder.imageMobile);
    }

    @Override
    public int getItemCount() {
        return mobileList.size();
    }

    class MobileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName,textPrice;
        ImageView imageMobile;
        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            imageMobile = itemView.findViewById(R.id.imageMobile);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MobileDeatilsActivity.class);
            intent.putExtra("mobile",mobileList.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
