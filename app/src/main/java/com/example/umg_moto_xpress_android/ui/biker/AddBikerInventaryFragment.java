package com.example.umg_moto_xpress_android.ui.biker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentAddBikerInventaryBinding;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;


public class AddBikerInventaryFragment extends BaseFragment {

    private FragmentAddBikerInventaryBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBikerInventaryBinding.inflate(inflater,container,false);

        binding.btnContinue.setOnClickListener(view -> {
            getServiceBikerDis();
        });


        binding.componentZona.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentPost.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentregio.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentCiudad.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentAddress.getEditText().addTextChangedListener(simpleTextWatcher());
        return binding.getRoot();
    }


    private void getServiceBikerDis(){
        loadingShow(true);
        sleepService(() -> {
            bikerListViewModel.getBikerListDisponibles(true).observe(requireActivity(), response -> {
                try {
                    navPopBackStack(binding.getRoot(),R.id.homeFragment);
                    loadingShow(false);
                }catch (Exception e){
                    dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                    loadingShow(false);
                }
            });
        },500);
    }

    private SimpleTextWatcher simpleTextWatcher(){
        return new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                enableButton();
            }
        };
    }

    private void enableButton(){
        binding.btnContinue.setEnabled(
                binding.componentZona.isEmptyText() && binding.componentPost.isEmptyText() && binding.componentregio.isEmptyText()
                        && binding.componentCiudad.isEmptyText() && binding.componentAddress.isEmptyText()
        );
    }
}