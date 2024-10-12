package com.example.umg_moto_xpress_android.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.models.data.CarouselHomeData;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;
import com.example.umg_moto_xpress_android.ui.carousel.CarouselFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;

import java.util.List;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private BikerListViewModel bikerListViewModel;
    private ListBikerFragment  listBikerFragment;
    private List<CarouselHomeData> carouselHomeDataList;

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
        carouselHomeDataList = bikerListViewModel.createListCarouselHome(requireActivity());

        if (savedInstanceState == null) {
            listBikerFragment = new ListBikerFragment(true);
            addChildFragmentManager(listBikerFragment,binding.fragmentList.getId());

            Fragment carousel = new CarouselFragment(true,false,false,carouselHomeDataList,null);
            addChildFragmentManager(carousel,binding.carousel.getId());
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