package com.example.umg_moto_xpress_android.dialog.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.component.spinner_design.AttributesSpinner;
import com.example.umg_moto_xpress_android.databinding.BottomSheetReservationBinding;
import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.tools.DatePicker;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomDialogReservation extends BottomSheetDialogFragment {

    private BottomSheetReservationBinding binding;
    private PersonData personData;
    private ActionClick actionClick;

    public BottomDialogReservation() {
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
        binding = BottomSheetReservationBinding.inflate(inflater,container,false);

        List<String> options = new ArrayList<>();
        options.add("Kilometraje Libre");
        options.add("Kilometraje");

        isActiveButton();
        binding.imgClose.setOnClickListener(view -> {
            dismiss();
        });

        binding.cardPayment.addTextChange(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                isActiveButton();
            }
        });

        binding.btnContinue.setOnClickListener(view -> {
            if (actionClick != null)
                actionClick.onClick();
        });

        binding.spinnerPopupWindow.setImplementDataSpinner(requireActivity(),new AttributesSpinner(0,false,options),(select, position) -> {
            isActiveButton();
        });

        binding.spinnerPopupWindow.setSelectPosition(0);

        DatePicker date = new DatePicker();
        binding.etReturn.setOnClickListener(v -> {
            isActiveButton();
            date.getDate(requireActivity(), binding.etReturn, "1", false,true);
        });
        binding.etOutput.setOnClickListener(v -> {
            isActiveButton();
            date.getDate(requireActivity(), binding.etOutput, "1", false,true);
        });

        binding.etOutput.setFocusable(false);
        binding.etOutput.setFocusableInTouchMode(false);
        binding.etOutput.setClickable(true);

        binding.etReturn.setFocusable(false);
        binding.etReturn.setFocusableInTouchMode(false);
        binding.etReturn.setClickable(true);

        return binding.getRoot();
    }

    private void isActiveButton(){
        boolean isValidText = binding.cardPayment.isValidEmptyEditText();
        boolean isSelectDate = isEmptyText(binding.etReturn) || isEmptyText(binding.etOutput);
        binding.btnContinue.setEnabled(!isValidText && !isSelectDate);
    }

    public interface ActionClick{
        void onClick();
    }

    private boolean isEmptyText(TextView tx){
        return String.valueOf(tx.getText()).isEmpty();
    }
}
