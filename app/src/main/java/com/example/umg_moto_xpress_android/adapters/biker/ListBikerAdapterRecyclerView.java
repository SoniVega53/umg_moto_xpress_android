package com.example.umg_moto_xpress_android.adapters.biker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;

import java.util.List;

public class ListBikerAdapterRecyclerView extends RecyclerView.Adapter<ListBikerAdapterRecyclerView.ViewHolder>{

    private List<BikerItemModel> bikerList;
    private Context context;

    public ListBikerAdapterRecyclerView(List<BikerItemModel> bikerList,Context context) {
        this.bikerList = bikerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListBikerAdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBikerBinding binding = ItemCardBikerBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBikerAdapterRecyclerView.ViewHolder holder, int position) {
        holder.bind(bikerList.get(position));
    }

    @Override
    public int getItemCount() {
        return bikerList != null ? bikerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardBikerBinding binding;
        int[] colors = new int[] {
                context.getResources().getColor(R.color.primaryBlue),
                context.getResources().getColor(R.color.primaryRed),
                context.getResources().getColor(R.color.secondGreen),
                context.getResources().getColor(R.color.primaryDarkGray)
        };

        public ViewHolder(@NonNull ItemCardBikerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(BikerItemModel item){
            int color = getColor(item);

            binding.txtTitle.setText(item.getTitle());
            binding.txtDes.setText(item.getDescription());

            binding.cardPrice.setCardBackgroundColor(color);
            binding.card.setStrokeColor(color);
        }

        private int getColor(BikerItemModel item){
            try {
                return colors[item.getType()];
            }catch (Exception e){
                return colors[0];
            }
        }
    }
}
