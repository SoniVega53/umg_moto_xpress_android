package com.example.umg_moto_xpress_android.ui.biker.process;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentDetailsBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;


public class DetailsBikerFragment extends BaseFragment {

   private FragmentDetailsBikerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBikerBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}