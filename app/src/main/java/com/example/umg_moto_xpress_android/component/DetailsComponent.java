package com.example.umg_moto_xpress_android.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.InputItemBinding;
import com.example.umg_moto_xpress_android.databinding.ItemDetailsProfileBinding;

public class DetailsComponent extends ConstraintLayout {

    private ItemDetailsProfileBinding binding;

    public DetailsComponent(@NonNull Context context) {
        super(context);
        initialize(context,null);

    }

    public DetailsComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);

    }

    public DetailsComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs);

    }

    public DetailsComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context,attrs);

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void initialize(Context context, AttributeSet attrs){
        binding = ItemDetailsProfileBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomAttributesDetails);

        try {
            binding.txtTitle.setText(a.getString(R.styleable.CustomAttributesDetails_titleProfile));
            binding.txtDes.setText(a.getString(R.styleable.CustomAttributesDetails_textDesProfile));

            boolean isImage = a.getBoolean(R.styleable.CustomAttributesDetails_isImageViewProfile,true);

            binding.imgIcon.setVisibility(isImage ? VISIBLE:GONE);
            binding.imgIcon.setImageDrawable(getResources().getDrawable(a.getResourceId(R.styleable.CustomAttributesDetails_drawImageProfile, R.drawable.file_regular)));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            a.recycle();
        }

    }

    public void printDataDetails(String title,String desc){
        binding.txtTitle.setText(title);
        binding.txtDes.setText(desc);
    }

    public void printDataDetails(String desc){
        binding.txtDes.setText(desc);
    }
}
