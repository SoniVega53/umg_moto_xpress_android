package com.example.umg_moto_xpress_android.ui.login;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentLoginBinding;
import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.repositories.MainRepositories;
import com.example.umg_moto_xpress_android.tools.JwtDecoder;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.ui.base.HomeFragment;
import com.example.umg_moto_xpress_android.viewmodel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;


public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        hiddenShowNavBar(true);
        functionFocusFragment(binding.getRoot());

        binding.btnAccept.setOnClickListener(view -> {
            getServiceLogin();
           // navigation(binding.getRoot(),R.id.action_loginFragment_to_homeFragment);
            clearInputs();
        });

        setListenerTextWatcher(binding.txtEditUser);
        setListenerTextWatcher(binding.txtEditPass);

        binding.txtRegister.setOnClickListener(view -> {
            navigation(binding.getRoot(),R.id.action_loginFragment_to_registerFragment);
        });

        binding.ipConfig.setOnClickListener(view -> {
            showInputDialog();
        });

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
                        navigation(binding.getRoot(),R.id.action_loginFragment_to_homeFragment);
                        clearInputs();
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

    private void clearInputs(){
        binding.txtEditUser.getText().clear();
        binding.txtEditPass.getText().clear();
    }


    private void showInputDialog() {
        EditText input = new EditText(requireActivity());
        input.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        LinearLayout layout = new LinearLayout(requireActivity());
        layout.setPadding(50, 50, 50, 50);
        layout.addView(input);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Ingresa un valor");

        builder.setView(layout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputValue = input.getText().toString();
            MainRepositories.URL_API_IP = "http://".concat(inputValue).concat(":9090/api/proyecto/");
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

}