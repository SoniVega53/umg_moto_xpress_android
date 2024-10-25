package com.example.umg_moto_xpress_android.adapters.biker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;

import java.util.List;

public class ListBikerAdapterRecyclerView extends RecyclerView.Adapter<ListBikerAdapterRecyclerView.ViewHolder>{

    private List<BikerItemModel> bikerList;
    private Context context;
    private OnclickAction onclickAction;

    public ListBikerAdapterRecyclerView(List<BikerItemModel> bikerList,Context context,OnclickAction onclickAction) {
        this.bikerList = bikerList;
        this.context = context;
        this.onclickAction = onclickAction;
    }

    public void setOnclickAction(OnclickAction onclickAction) {
        this.onclickAction = onclickAction;
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
            binding.txtPrice.setText("Q.".concat(item.getValue()).concat(".00"));

            binding.cardPrice.setCardBackgroundColor(color);
            binding.card.setStrokeColor(color);

            binding.card.setOnClickListener(view -> {
                if (onclickAction != null){
                    onclickAction.actionClick(item);
                }
            });
        }

        private int getColor(BikerItemModel item){
            try {
                return colors[item.getType()];
            }catch (Exception e){
                return colors[0];
            }
        }
    }

    public interface OnclickAction{
        void actionClick(BikerItemModel item);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDataList(List<BikerItemModel> updateList){
        this.bikerList = updateList;
        notifyDataSetChanged();
    }
}
