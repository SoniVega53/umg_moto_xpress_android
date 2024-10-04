package com.example.umg_moto_xpress_android.ui.biker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentListBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;


public class ListBikerFragment extends BaseFragment {

   private FragmentListBikerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBikerBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}