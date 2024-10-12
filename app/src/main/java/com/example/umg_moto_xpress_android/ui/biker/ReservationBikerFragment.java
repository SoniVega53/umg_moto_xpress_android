package com.example.umg_moto_xpress_android.ui.biker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentReservationBikerBinding;
import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.ui.carousel.CarouselFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;
import com.example.umg_moto_xpress_android.viewmodel.LoginViewModel;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.model.CarouselType;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationBikerFragment extends BaseFragment {

    private FragmentReservationBikerBinding binding;
    private BikerListViewModel bikerListViewModel;
    private List<CarouselItem> listCarouselItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bikerListViewModel = new ViewModelProvider(requireActivity()).get(BikerListViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReservationBikerBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());
        listCarouselItem = bikerListViewModel.createListCarousel();

        if (savedInstanceState == null) {
            Fragment newFragment = new ListBikerFragment();
            addChildFragmentManager(newFragment,binding.fragmentList.getId());

            Fragment carousel = new CarouselFragment(false,false,listCarouselItem,null);
            addChildFragmentManager(carousel,binding.header.getId());
        }




        return binding.getRoot();
    }
}