package com.example.umg_moto_xpress_android.ui.bitacora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentInformeBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

public class InformeBikerFragment extends BaseFragment {

    private FragmentInformeBikerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInformeBikerBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        return binding.getRoot();
    }
}