package com.example.umg_moto_xpress_android.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentLoginBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.ui.base.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        hiddenShowNavBar(true);


        binding.btnAccept.setOnClickListener(view -> {
            Bundle bundle = new Bundle();

            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
        });

        setListenerTextWatcher(binding.txtEditUser);
        setListenerTextWatcher(binding.txtEditPass);

        return binding.getRoot();
    }

    private void setListenerTextWatcher (TextInputEditText input){
        setListenerTextWatcher(input,(charSequence, i, i1, i2) -> {
            enableButton();
        });
    }

    private void enableButton(){
        String input = binding.txtEditUser.getText().toString().trim();
        String input2 = binding.txtEditPass.getText().toString().trim();

        binding.btnAccept.setEnabled(!input.isEmpty() && !input2.isEmpty());
    }
}