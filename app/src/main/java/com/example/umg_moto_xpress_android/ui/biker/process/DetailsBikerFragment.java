package com.example.umg_moto_xpress_android.ui.biker.process;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentDetailsBikerBinding;
import com.example.umg_moto_xpress_android.dialog.bottomSheet.BottomDialogReservation;
import com.example.umg_moto_xpress_android.dialog.bottomSheet.BottomDialogUpdateUser;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;
import com.example.umg_moto_xpress_android.ui.carousel.CarouselFragment;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.List;


public class DetailsBikerFragment extends BaseFragment {

    private FragmentDetailsBikerBinding binding;
    private List<CarouselItem> listCarouselItem;
    private boolean reservation = false;
    private boolean isActiveButton = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBikerBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());
        listCarouselItem = bikerListViewModel.createListCarousel();

        if (getArguments() != null){
            reservation = getArguments().getBoolean("reservation");
            isActiveButton = getArguments().getBoolean("isActiveButton");
        }
        binding.btnContinue.setText(reservation ? "Cancelar": "Reservar");
        binding.btnContinue.setVisibility(isActiveButton ? View.VISIBLE : View.GONE);

        if (savedInstanceState == null) {
            Fragment carousel = new CarouselFragment(false,false,listCarouselItem,null);
            addChildFragmentManager(carousel,binding.carousel.getId());
        }

        binding.btnContinue.setOnClickListener(view -> {
            if (reservation){
                focusFragment = true;
                dialogMessage("Cancelar","Estas Seguro de Cancelar",0,view1 -> {
                    getServiceBikerDisCancel();
                });

            }else {
                BottomDialogReservation bottomSheetFragment = new BottomDialogReservation();
                bottomSheetFragment.setActionClick(() -> {
                    getServiceBikerDis(bottomSheetFragment);
                });
                bottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "card_bottom");
            }


        });



        return binding.getRoot();
    }

    private void getServiceBikerDis(BottomDialogReservation bottomSheetFragment){
        loadingShow(true);
        bottomSheetFragment.dismiss();
        sleepService(() -> {
            bikerListViewModel.getBikerListReservadas(true).observe(requireActivity(), response -> {
                try {
                    bikerListViewModel.getBikerListDisponibles(false).observe(requireActivity(), res -> {
                        try {
                            Toast.makeText(requireActivity(), "Se reservo con exito", Toast.LENGTH_SHORT).show();
                            navPopBackStack(binding.getRoot(),R.id.homeFragment);
                            loadingShow(false);
                        }catch (Exception e){
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                            loadingShow(false);
                        }
                    });

                }catch (Exception e){
                    dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                    loadingShow(false);
                }
            });
        },500);
    }

    private void getServiceBikerDisCancel(){
        loadingShow(true);
        sleepService(() -> {
            bikerListViewModel.getBikerListReservadasCancel();
            navPopBackStack(binding.getRoot());
        },500);
    }
}