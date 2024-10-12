package com.example.umg_moto_xpress_android.ui.biker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.biker.ListBikerAdapterRecyclerView;
import com.example.umg_moto_xpress_android.databinding.FragmentListBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class ListBikerFragment extends BaseFragment {

   private FragmentListBikerBinding binding;
   private ListBikerAdapterRecyclerView adapterRecyclerView;
   private List<BikerItemModel> bikerList;
   private boolean isFilter ;

    public ListBikerFragment(boolean isFilter) {
        this.isFilter = isFilter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBikerBinding.inflate(inflater,container,false);

        bikerList = new ArrayList<>();
        bikerList.add(new BikerItemModel("Biker 1","","200"));
        bikerList.add(new BikerItemModel("Biker 2","","200"));
        bikerList.add(new BikerItemModel("Biker 3","","200"));
        bikerList.add(new BikerItemModel("Biker 3","","200"));
        bikerList.add(new BikerItemModel("Biker 3","","200"));
        bikerList.add(new BikerItemModel("Biker 3","","200"));
        bikerList.add(new BikerItemModel("Biker 3","","200"));

        adapterRecyclerView = new ListBikerAdapterRecyclerView(bikerList);

        binding.horizontalScrollView.setVisibility(isFilter ? View.VISIBLE : View.GONE);

        binding.recyclerview.setLayoutManager(new GridLayoutManager(requireActivity(),1));
        binding.recyclerview.setAdapter(adapterRecyclerView);
        binding.recyclerview.setHasFixedSize(true);

        return binding.getRoot();
    }
}