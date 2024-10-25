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
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentReservationBikerBinding;
import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.dialog.bottomSheet.BottomDialogReservation;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReservationBikerBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        getServiceBikerRes(savedInstanceState);


        return binding.getRoot();
    }

    private void getServiceBikerRes(Bundle savedInstanceState){
        if (bikerListViewModel.getBikerListReservadas() == null){
            bikerListViewModel.getBikerListReservadas(false);
        }
        loadingShow(true);
        sleepService(() -> {
            bikerListViewModel.getBikerListReservadas().observe(requireActivity(), response -> {
                try {
                    if (savedInstanceState == null) {
                        Fragment listBikerFragment = new ListBikerFragment(response,true,true,false,2,item -> {
                            clearOnBackPressedCall();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("reservation",true);
                            bundle.putBoolean("isActiveButton",item.getType() == 0);
                            navigation(binding.getRoot(), R.id.action_reservationBikerFragment_to_detailsBikerFragment,bundle);
                        });

                        addChildFragmentManagerDelete(listBikerFragment,binding.fragmentList.getId());
                    }
                    loadingShow(false);
                }catch (Exception e){
                    dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                    loadingShow(false);
                }
            });
        },500);


    }

}