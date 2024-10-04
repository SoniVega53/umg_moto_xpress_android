package com.example.umg_moto_xpress_android.ui.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.umg_moto_xpress_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}