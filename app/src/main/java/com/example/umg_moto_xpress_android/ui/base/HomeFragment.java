package com.example.umg_moto_xpress_android.ui.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        if (savedInstanceState == null) {
            Fragment newFragment = new ListBikerFragment();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.fragmentList, newFragment) // Usa el ID de tu FrameLayout
                    .commit();
        }

        return binding.getRoot();
    }
}