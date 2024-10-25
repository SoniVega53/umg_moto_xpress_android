package com.example.umg_moto_xpress_android.dialog.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.BottomSheetPaymentCardBinding;
import com.example.umg_moto_xpress_android.databinding.BottomSheetUpdateUserBinding;
import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomDialogUpdateUser extends BottomSheetDialogFragment {

    private BottomSheetUpdateUserBinding binding;
    private PersonData personData;
    private ActionClick actionClick;

    public BottomDialogUpdateUser(PersonData personData) {
        this.personData = personData;
    }

    public void setActionClick(ActionClick actionClick) {
        this.actionClick = actionClick;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NORMAL, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetUpdateUserBinding.inflate(inflater,container,false);

        binding.imgClose.setOnClickListener(view -> {
            dismiss();
        });

        printDataEditText();

        binding.txtEditName.addTextChangedListener(simpleTextWatcher());
        binding.txtEditLastName.addTextChangedListener(simpleTextWatcher());

        binding.btnAccept.setOnClickListener(view -> {
            PersonData request = new PersonData();
            request.setName(binding.txtEditName.getText().toString().trim());
            request.setLastName(binding.txtEditLastName.getText().toString().trim());
            request.setPhone(binding.txtEditPhone.getText().toString().trim());
            request.setAddress(binding.txtEditAddress.getText().toString().trim());

            if (actionClick != null)
                actionClick.onClick(request);
        });

        return binding.getRoot();
    }

    private void printDataEditText(){
        binding.txtEditName.setText(personData.getName());
        binding.txtEditLastName.setText(personData.getLastName());
        binding.txtEditPhone.setText(personData.getPhone());
        binding.txtEditAddress.setText(personData.getAddress());
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
        String input2 = binding.txtEditName.getText().toString().trim();
        String input3 = binding.txtEditLastName.getText().toString().trim();

        binding.btnAccept.setEnabled(!input2.isEmpty() && !input3.isEmpty());
    }

    public interface ActionClick{
        void onClick(PersonData data);
    }
}
