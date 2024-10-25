package com.example.umg_moto_xpress_android.dialog.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.BottomSheetPaymentCardBinding;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomDialogPayment extends BottomSheetDialogFragment {

    private BottomSheetPaymentCardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NORMAL, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetPaymentCardBinding.inflate(inflater,container,false);

        binding.imgClose.setOnClickListener(view -> {
            dismiss();
        });

        binding.cardPayment.addTextChange(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                boolean isValidText = binding.cardPayment.isValidEmptyEditText();
                isActiveButton(!isValidText);
            }
        });

        return binding.getRoot();
    }

    private void isActiveButton(boolean isActive){
        binding.btnContinue.setEnabled(isActive);
    }
}
