package com.example.umg_moto_xpress_android.component.inputs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.InputItemBinding;
import com.example.umg_moto_xpress_android.databinding.ItemListBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;

import java.util.List;

public class ItemInputText extends ConstraintLayout {

    private InputItemBinding binding;

    public ItemInputText(@NonNull Context context) {
        super(context);
        initialize(context,null);
    }

    public ItemInputText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

    public ItemInputText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs);
    }

    public ItemInputText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context,attrs);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initialize(Context context, AttributeSet attrs){
        binding = InputItemBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomAttributesInputs);

        try {
            boolean isImage = a.getBoolean(R.styleable.CustomAttributesInputs_isImageView,false);

            binding.imageView.setVisibility(isImage ? VISIBLE:GONE);

            binding.editTextText.setHint(a.getString(R.styleable.CustomAttributesInputs_textHidTitleInput));
            int maxLen = a.getInteger(R.styleable.CustomAttributesInputs_maxLengthInput,0);

            if (maxLen > 0){
                InputFilter maxLengthFilter = new InputFilter.LengthFilter(maxLen);
                binding.editTextText.setFilters(new InputFilter[]{maxLengthFilter});
            }


            binding.editTextText.setText(a.getString(R.styleable.CustomAttributesInputs_textTitleInput));
            binding.imageView.setImageDrawable(getResources().getDrawable(a.getResourceId(R.styleable.CustomAttributesInputs_drawImage,R.drawable.magnifying_glass_solid)));
            int inputType = a.getInt(R.styleable.CustomAttributesInputs_inputType, InputType.TYPE_CLASS_TEXT);

            binding.editTextText.setInputType(inputType);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            a.recycle();
        }

        binding.card.setOnClickListener(view -> {
            binding.editTextText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.editTextText, InputMethodManager.SHOW_IMPLICIT);
        });
    }

    public EditText getEditText(){
        return binding.editTextText;
    }

}
