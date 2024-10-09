package com.example.umg_moto_xpress_android.dialog.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umg_moto_xpress_android.databinding.DialogMessageBinding;
import com.example.umg_moto_xpress_android.dialog.BaseDialog;
import com.example.umg_moto_xpress_android.R;

public class DetailsMessageInf extends BaseDialog {
    public static String TAG = "DetailsMessage";
    private DialogMessageBinding binding;
    private String title;
    private String message;
    private String txtButton;
    private OnClick lis;
    private int type = 0;

    public DetailsMessageInf(String title, String message,int type) {
        this.title = title != null ? title : "";
        this.message = message != null ? message : "";
        this.type = type;
    }
    public DetailsMessageInf(String title, String message,String txtButton) {
        this.title = title;
        this.message = message;
        this.txtButton = txtButton;
    }


    public void setLis(OnClick lis) {
        this.lis = lis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setStyle(STYLE_NORMAL, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogMessageBinding.inflate(inflater,container,false);

        binding.txtTitleHeader.setText(title);
        binding.txtDesc.setText(message);
        binding.btnContinue.setText(txtButton != null ? txtButton : getText(R.string.accept));

        binding.getRoot().setOnClickListener(view -> {
            dismiss();
        });

        binding.btnContinue.setOnClickListener(view -> {
            if (lis != null)
                lis.onClick(view);
            dismiss();
        });

        if (type > -1)
            binding.imgAlert.setImageResource(type == 0 ? R.drawable.exclamation_triangle_solid :
                type == 1 ? R.drawable.exclamation_circle_solid : R.drawable.error_solid);
        else
            binding.imgAlert.setVisibility(View.GONE);

        return binding.getRoot();
    }
}
