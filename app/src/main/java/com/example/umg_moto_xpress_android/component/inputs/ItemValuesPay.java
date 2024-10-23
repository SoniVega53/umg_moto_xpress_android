package com.example.umg_moto_xpress_android.component.inputs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.umg_moto_xpress_android.databinding.ItemValuesPayBinding;

public class ItemValuesPay extends ConstraintLayout {
    private ItemValuesPayBinding binding;

    public ItemValuesPay(@NonNull Context context) {
        super(context);
        initialize(context);
    }

    public ItemValuesPay(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ItemValuesPay(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public ItemValuesPay(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        binding = ItemValuesPayBinding.inflate(LayoutInflater.from(context), this, true);

    }



    public ItemValuesPayBinding getBinding() {
        return binding;
    }
}
