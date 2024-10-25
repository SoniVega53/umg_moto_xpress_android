package com.example.umg_moto_xpress_android.ui.biker;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentInventaryBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;


public class InventarioBikerFragment extends BaseFragment {

    private FragmentInventaryBikerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInventaryBikerBinding.inflate(inflater,container,false);

        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());


        if (savedInstanceState == null) {
            if (!getUserDecodeData().getRole().toLowerCase().equals("cliente")){
                Fragment newFragment = new ListBikerFragment(bikerListViewModel.listBikerAll(),true,true,true,1,item -> {
                    navigation(binding.getRoot(), R.id.action_inventarioBikerFragment_to_detailsBikerFragment);
                });
                addChildFragmentManager(newFragment,binding.fragmentList.getId());
            }else {
                Fragment newFragment = new ListBikerFragment(bikerListViewModel.listBikerAll(),true,false,false,1,item -> {
                    navigation(binding.getRoot(), R.id.action_inventarioBikerFragment_to_detailsBikerFragment);
                });
                addChildFragmentManager(newFragment,binding.fragmentList.getId());
            }


//            Fragment carousel = new CarouselFragment(false,false,listCarouselItem,null);
//            addChildFragmentManager(carousel,binding.carousel.getId());
        }

        return binding.getRoot();
    }
}