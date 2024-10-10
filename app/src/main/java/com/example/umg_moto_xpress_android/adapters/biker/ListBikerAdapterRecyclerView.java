package com.example.umg_moto_xpress_android.adapters.biker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;

import java.util.List;

public class ListBikerAdapterRecyclerView extends RecyclerView.Adapter<ListBikerAdapterRecyclerView.ViewHolder>{

    private List<BikerItemModel> bikerList;

    public ListBikerAdapterRecyclerView(List<BikerItemModel> bikerList) {
        this.bikerList = bikerList;
    }

    @NonNull
    @Override
    public ListBikerAdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBikerBinding binding = ItemCardBikerBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBikerAdapterRecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bikerList != null ? bikerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardBikerBinding binding;

        public ViewHolder(@NonNull ItemCardBikerBinding binding) {
            super(binding.getRoot());
        }
    }
}
