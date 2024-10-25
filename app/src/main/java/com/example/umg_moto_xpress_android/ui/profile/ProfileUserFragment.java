package com.example.umg_moto_xpress_android.ui.profile;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentProfileUserBinding;
import com.example.umg_moto_xpress_android.dialog.bottomSheet.BottomDialogUpdateUser;
import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.response.usuario.UserDetailResponse;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

public class ProfileUserFragment extends BaseFragment {

    private FragmentProfileUserBinding binding;
    private UserDetailResponse userDetailResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileUserBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        binding.btnEditPassword.setOnClickListener(view -> {
            showChangePasswordDialog();
        });

        binding.btnEdit.setOnClickListener(view -> {
            BottomDialogUpdateUser bottomSheetFragment = new BottomDialogUpdateUser(userDetailResponse.getUserProfileData().getPerson());
            bottomSheetFragment.setActionClick(data -> {
                getServiceUpdateUser(data,bottomSheetFragment);
            });

            bottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "card_bottom");
        });

        getServiceDetailUser();
        return binding.getRoot();
    }

    private void getServiceDetailUser(){
        userViewModel.getUserDetailResponse().observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        userDetailResponse = loginResponse.getResponse();
                        printDataDetailsUser();
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1, this::navPopBackStack);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2,this::navPopBackStack);
                        }
                        break;
                }
                loadingShow(false);
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2,this::navPopBackStack);
                loadingShow(false);
            }
        });
    }

    private void printDataDetailsUser(){
        if (userDetailResponse != null){
            binding.txtUserName.setText(userDetailResponse.getUserProfileData().getUsername());
            binding.txtEmail.setText(userDetailResponse.getUserProfileData().getEmail());
            binding.compName.printDataDetails(userDetailResponse.getUserProfileData().getPerson().getName());
            binding.compLastName.printDataDetails(userDetailResponse.getUserProfileData().getPerson().getLastName());
            binding.compPhone.printDataDetails(userDetailResponse.getUserProfileData().getPerson().getPhone());
            binding.compAddress.printDataDetails(userDetailResponse.getUserProfileData().getPerson().getAddress());
            binding.compUserName.printDataDetails(userDetailResponse.getUserProfileData().getUsername());
            binding.compEmail.printDataDetails(userDetailResponse.getUserProfileData().getEmail());
        }
    }

    private void printDataDetailsUserUpdate(PersonData request){
        if (request != null){
            binding.compName.printDataDetails(request.getName());
            binding.compLastName.printDataDetails(request.getLastName());
            binding.compPhone.printDataDetails(request.getPhone());
            binding.compAddress.printDataDetails(request.getAddress());
        }
    }

    private void getServiceChangePassword(String password, String passwordChange,AlertDialog alertDialog){
        loadingShow(true);
        userViewModel.getChangePassword(requireActivity(),password,passwordChange).observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        alertDialog.dismiss();
                        Toast.makeText(requireActivity(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
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

    private void getServiceUpdateUser(PersonData request,BottomDialogUpdateUser bottomSheetFragment){
        loadingShow(true);
        userViewModel.getUpdateUser(requireActivity(),request).observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        bottomSheetFragment.dismiss();
                        printDataDetailsUserUpdate(loginResponse.getResponse().getPersonData());
                        Toast.makeText(requireActivity(), loginResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void showChangePasswordDialog() {
        LinearLayout layout = new LinearLayout(requireActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        EditText etOldPassword = new EditText(requireActivity());
        etOldPassword.setHint("Contraseña actual");
        etOldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etOldPassword);

        EditText etNewPassword = new EditText(requireActivity());
        etNewPassword.setHint("Nueva contraseña");
        etNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etNewPassword);

        EditText etConfirmPassword = new EditText(requireActivity());
        etConfirmPassword.setHint("Confirmar nueva contraseña");
        etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etConfirmPassword);

        Button btnChangePassword = new Button(requireActivity());
        btnChangePassword.setText("Cambiar contraseña");
        layout.addView(btnChangePassword);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireActivity());
        dialogBuilder.setTitle("Cambiar Contraseña");
        dialogBuilder.setView(layout);

        AlertDialog alertDialog = dialogBuilder.create();

        btnChangePassword.setOnClickListener(v -> {
            String oldPassword = etOldPassword.getText().toString().trim();
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(requireActivity(), "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            getServiceChangePassword(oldPassword,newPassword,alertDialog);
        });

        alertDialog.show();
    }

}