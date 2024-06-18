package com.sunbeaminfo.mobilestore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sunbeaminfo.mobilestore.fragments.MobileListFragment;
import com.sunbeaminfo.mobilestore.fragments.OrdersFragment;
import com.sunbeaminfo.mobilestore.fragments.ProfileFragment;

public class MainFragmentAdapter extends FragmentStateAdapter {
    public MainFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MobileListFragment();
            case 1:
                return new OrdersFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
