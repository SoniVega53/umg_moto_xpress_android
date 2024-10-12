package com.example.umg_moto_xpress_android.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private BikerListViewModel bikerListViewModel;
    private ListBikerFragment  listBikerFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hiddenShowNavBar(false);
        bikerListViewModel = new ViewModelProvider(requireActivity()).get(BikerListViewModel.class);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());
        onBackPressedCall(null);
        bikerListViewModel.createListCarousel();

        if (savedInstanceState == null) {
            listBikerFragment = new ListBikerFragment();
            addChildFragmentManager(listBikerFragment,binding.fragmentList.getId());
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.reservation){
                clearOnBackPressedCall();
                navigation(binding.getRoot(),R.id.action_homeFragment_to_reservationBikerFragment);
            }else if (item.getItemId() == R.id.process){
                clearOnBackPressedCall();
                navigation(binding.getRoot(),R.id.action_homeFragment_to_createBikerFragment);
            }
            return false;
        });

        return binding.getRoot();
    }

}