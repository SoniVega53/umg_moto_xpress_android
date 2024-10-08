package com.example.umg_moto_xpress_android.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.umg_moto_xpress_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements InterfaceMainAction{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.constraLoading.setOnClickListener(view -> {});


    }

    @Override
    public void hiddenShowNavbar(boolean isHidden) {
        try {
            binding.consNavBar.setVisibility(isHidden ? View.GONE:View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadingShow(boolean isShow) {
        try {
            binding.constraLoading.setVisibility(isShow ? View.VISIBLE:View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void logout(View.OnClickListener listener) {
        try {
            binding.logout.setOnClickListener(listener);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}