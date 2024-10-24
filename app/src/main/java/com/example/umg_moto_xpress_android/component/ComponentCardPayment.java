package com.example.umg_moto_xpress_android.component;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.umg_moto_xpress_android.databinding.ItemMetodoPagoBinding;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;

public class ComponentCardPayment extends ConstraintLayout {
    private ItemMetodoPagoBinding binding;

    public ComponentCardPayment(@NonNull Context context) {
        super(context);
        initialize(context,null);
    }

    public ComponentCardPayment(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

    public ComponentCardPayment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs);
    }

    public ComponentCardPayment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context,attrs);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initialize(Context context, AttributeSet attrs){
        binding = ItemMetodoPagoBinding.inflate(LayoutInflater.from(context), this, true);


        binding.edDate.getEditText().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                super.onTextChanged(s, i, i1, i2);

                if(s.length() == 1) {
                    validIsDate(s);
                }else if (s.length() == 2 && !s.toString().endsWith("/")) {
                    binding.edDate.getEditText().setText(s + "/");
                    binding.edDate.getEditText().setSelection(s.length() + 1);
                }
            }
        });

        binding.edDate.getEditText().setSingleLine(true);
        binding.edDate.getEditText().setImeOptions(EditorInfo.IME_ACTION_NEXT);

        binding.edNumber.getEditText().setOnEditorActionListener((textView, actionId, event) -> {
            if (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                binding.edDate.getEditText().requestFocus();
                return true; // Indicar que el evento fue manejado
            }
            return false; // No manejar el evento
        });
    }

    private void validIsDate(CharSequence s){
        try {
            int date = Integer.parseInt(s.toString());
            if (date != 1) {
                binding.edDate.getEditText().setText("0" + s + "/");
                binding.edDate.getEditText().setSelection(s.length() + 2);
            }
        }catch (Exception e){
            // por si no hay numero
        }
    }

    public EditText getEditTextTitular(){
        return binding.edTitle.getEditText();
    }

    public EditText getEditTextNumber(){
        return binding.edNumber.getEditText();
    }

    public EditText getEditTextDate(){
        return binding.edDate.getEditText();
    }

    public EditText getEditTextCode(){
        return binding.edCode.getEditText();
    }

    public void addTextChange(SimpleTextWatcher simpleTextWatcher){
        binding.edTitle.getEditText().addTextChangedListener(simpleTextWatcher);
        binding.edNumber.getEditText().addTextChangedListener(simpleTextWatcher);
        binding.edDate.getEditText().addTextChangedListener(simpleTextWatcher);
        binding.edCode.getEditText().addTextChangedListener(simpleTextWatcher);
    }

    public boolean isValidEmptyEditText(){
        return binding.edTitle.getEditText().getText().toString().trim().isEmpty() ||
                binding.edNumber.getEditText().getText().toString().trim().isEmpty() ||
                binding.edNumber.getEditText().getText().length() < 16 ||
                binding.edCode.getEditText().getText().length() < 3 ||
                binding.edDate.getEditText().getText().length() < 5 ||
                binding.edDate.getEditText().getText().toString().trim().isEmpty() ||
                binding.edCode.getEditText().getText().toString().trim().isEmpty();
    }
}
