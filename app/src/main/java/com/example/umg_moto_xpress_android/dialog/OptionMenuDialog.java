package com.example.umg_moto_xpress_android.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.component.inputs.ItemValuesPay;
import com.example.umg_moto_xpress_android.databinding.DialogItemMenuBinding;

public class OptionMenuDialog extends BaseDialog {
    public static String TAG = "CreateDetailsAmountDialog";
    private DialogItemMenuBinding binding;
    private OnClickType onClick;
    private int type = 0;

    public OptionMenuDialog(OnClickType onClick) {
        this.onClick = onClick;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NORMAL, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogItemMenuBinding.inflate(inflater,container,false);

        binding.imgClose.setOnClickListener(view -> dismiss());
        binding.frameOp.setOnClickListener(view -> dismiss());

        getDataStart();

        return binding.getRoot();
    }

    private void getDataStart(){
        viewDataItem(binding.itemMenu1,getString(R.string.menu_profile),1, R.drawable.user_solid);
        viewDataItem(binding.itemMenu2,getString(R.string.menu_reservation),2,R.drawable.motorcycle_solid);
        viewDataItem(binding.itemMenu3,getString(R.string.menu_biker),3, R.drawable.motorcycle_solid);
        viewDataItem(binding.itemMenu4,getString(R.string.menu_add_biker),4, R.drawable.plus_solid);
        viewDataItem(binding.itemMenu5,getString(R.string.menu_users),5, R.drawable.user_solid);
        viewDataItem(binding.itemMenu6,getString(R.string.menu_bita),6, R.drawable.file_regular);

        binding.containerData.setVisibility(type == 1 ? View.GONE : View.VISIBLE);
        binding.txtTitle.setText(getText(R.string.title_home));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void viewDataItem(ItemValuesPay biPa, String title, int num, int icon){
        biPa.getBinding().txtAmount.setVisibility(View.GONE);
        biPa.getBinding().txtTitle.setText(title);
        biPa.getBinding().icon.setImageResource(icon);
        biPa.getBinding().getRoot().setOnClickListener(view -> {
            dismiss();
            if (onClick != null)
                onClick.onClick(num);
        });
    }


}
