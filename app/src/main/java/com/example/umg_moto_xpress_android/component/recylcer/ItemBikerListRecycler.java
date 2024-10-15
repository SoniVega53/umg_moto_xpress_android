package com.example.umg_moto_xpress_android.component.recylcer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.R;

import com.example.umg_moto_xpress_android.databinding.ItemListBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;

import java.util.List;

public class ItemBikerListRecycler extends ConstraintLayout {

    private ItemListBikerBinding binding;
    private boolean isVisible = true;

    public ItemBikerListRecycler(@NonNull Context context) {
        super(context);
        initialize(context,null);
    }

    public ItemBikerListRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

    public ItemBikerListRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs);
    }

    public ItemBikerListRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context,attrs);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initialize(Context context, AttributeSet attrs){
        binding = ItemListBikerBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomAttributesItemBiker);
        try {
            binding.txtTitle.setText(a.getString(R.styleable.CustomAttributesItemBiker_textTitle));
            binding.txtTitleEmpty.setText(a.getString(R.styleable.CustomAttributesItemBiker_titleMessageEmpty));
            binding.txtDesEmpty.setText(a.getString(R.styleable.CustomAttributesItemBiker_descriptionMessageEmpty));

            int image = a.getInt(R.styleable.CustomAttributesItemBiker_imageMessageEmpty,R.drawable.file_regular);
            binding.imgEmpty.setImageDrawable(getResources().getDrawable(image));

            onClickActionRecycler();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            a.recycle();
        }
    }

    public void setBikerListRecyclerView(RecyclerView.Adapter adapter, List<BikerItemModel> bikerList,Context context){
       try {
           if (bikerList != null){
               binding.recyclerview.setLayoutManager(new GridLayoutManager(context,1));
               binding.recyclerview.setAdapter(adapter);
               binding.recyclerview.setHasFixedSize(true);
               binding.card.setVisibility(bikerList.size() > 0 ? View.VISIBLE : View.GONE);
               binding.isEmpty.setVisibility(bikerList.size() > 0 ? View.GONE : View.VISIBLE);
           }else
               binding.isEmpty.setVisibility(View.GONE);
       }catch (Exception e){e.printStackTrace();}
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void onClickActionRecycler(){
        binding.linerOcult.setOnClickListener(view -> {
            showRecycler();
        });
    }

    public void showRecycler(){
        isVisible = !isVisible;
        binding.recyclerview.setVisibility(isVisible ? View.VISIBLE:View.GONE);
        binding.imgMore.setImageDrawable(isVisible ? getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down_24) :
                getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up_24));
    }

    public void resetVisibilityRecycler(){
        isVisible = false;
        showRecycler();
    }
}
