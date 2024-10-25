package com.example.umg_moto_xpress_android.ui.login;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentRegisterBinding;
import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.request.register.RegisterRequest;
import com.example.umg_moto_xpress_android.tools.JwtDecoder;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.viewmodel.LoginViewModel;
import com.example.umg_moto_xpress_android.viewmodel.UserViewModel;


public class RegisterFragment extends BaseFragment {

    private FragmentRegisterBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);

        binding.txtLogin.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnAccept.setOnClickListener(view -> {
            getServiceRegister();
        });

        binding.txtEditUser.addTextChangedListener(simpleTextWatcher());
        binding.txtEditName.addTextChangedListener(simpleTextWatcher());
        binding.txtEditLastName.addTextChangedListener(simpleTextWatcher());
        binding.txtEditEmail.addTextChangedListener(simpleTextWatcher());
        binding.txtEditPass.addTextChangedListener(simpleTextWatcher());

        return binding.getRoot();
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


    private void getServiceLogin(){
        loadingShow(true);
        loginViewModel.setLoginResponseMutableLiveData(requireActivity(),new LoginSingRequest(
                binding.txtEditUser.getText().toString().trim(),
                binding.txtEditPass.getText().toString().trim()
        ));

        loginViewModel.getLoginResponseMutableLiveData().observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        SharedPreferencesTool.writeSecureString(requireActivity(),StringTool.LOGIN_SESSION,loginResponse.getResponse().getEntityResponse().getToken());
                        SharedPreferencesTool.writeSecureUser(requireActivity(),StringTool.LOGIN_USER,
                                JwtDecoder.getNameDecode(loginResponse.getResponse().getEntityResponse().getToken()));
                        getServiceDetailUser();
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        loadingShow(false);
                        break;
                }

            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }

    private void getServiceDetailUser(){
        userViewModel.getUserDetailResponse(requireActivity()).observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        navigation(binding.getRoot(),R.id.action_loginFragment_to_homeFragment);
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        break;
                }
                loadingShow(false);
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }

    private void getServiceRegister(){
        loadingShow(true);

        RegisterRequest request = new RegisterRequest(
                binding.txtEditUser.getText().toString().trim(),binding.txtEditPass.getText().toString().trim(),
                binding.txtEditEmail.getText().toString().trim(), "2", new PersonData(
                binding.txtEditName.getText().toString().trim(),binding.txtEditLastName.getText().toString().trim(),
                binding.txtEditPhone.getText().toString().trim(),binding.txtEditAddress.getText().toString().trim()
        ));

        loginViewModel.setRegisterResponse(requireActivity(),request);

        loginViewModel.getRegisterResponse().observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        getServiceLogin();
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        loadingShow(false);
                        break;
                }
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }

    private void enableButton(){
        String input =  binding.txtEditUser.getText().toString().trim();
        String input2 = binding.txtEditName.getText().toString().trim();
        String input3 = binding.txtEditLastName.getText().toString().trim();
        String input4 = binding.txtEditEmail.getText().toString().trim();
        String input6 = binding.txtEditPass.getText().toString().trim();

        binding.btnAccept.setEnabled(!input.isEmpty() && !input2.isEmpty() && !input3.isEmpty()&& !input4.isEmpty()&& !input6.isEmpty() );
    }
}