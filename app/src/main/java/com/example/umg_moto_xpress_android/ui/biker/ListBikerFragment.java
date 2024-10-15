package com.example.umg_moto_xpress_android.ui.biker;

import android.annotation.SuppressLint;
import android.net.wifi.p2p.WifiP2pGroup;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.biker.ListBikerAdapterRecyclerView;
import com.example.umg_moto_xpress_android.databinding.FragmentListBikerBinding;
import com.example.umg_moto_xpress_android.databinding.ItemListBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class ListBikerFragment extends BaseFragment {

    private FragmentListBikerBinding binding;
    private ListBikerAdapterRecyclerView adapterRecyclerView;
    private ListBikerAdapterRecyclerView adapterRecyclerView1;
    private ListBikerAdapterRecyclerView adapterRecyclerView2;
    private List<BikerItemModel> bikerList;
    private boolean isFilter ;
    private boolean isVisible1 = true ;
    private boolean isVisible2 = true;
    private boolean isVisible3 = true;
    private BikerListViewModel bikerListViewModel;


    public ListBikerFragment(boolean isFilter) {
        this.isFilter = isFilter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bikerListViewModel = new ViewModelProvider(requireActivity()).get(BikerListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBikerBinding.inflate(inflater,container,false);

        bikerList = bikerListViewModel.listBikerAll();
        binding.horizontalScrollView.setVisibility(isFilter ? View.VISIBLE : View.GONE);
        addDataListRecyclerView();

        filterRecycler(true,false,false);
        binding.rdGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if (binding.rd4.getId() == i){
                filterRecycler(true,true,true);
            }else {
                filterRecycler(binding.rd1.getId() == i,binding.rd2.getId() == i,binding.rd3.getId() == i);
            }
            binding.contentRecycler1.resetVisibilityRecycler();
            binding.contentRecycler2.resetVisibilityRecycler();
            binding.contentRecycler3.resetVisibilityRecycler();
        });

        return binding.getRoot();
    }

    private void addDataListRecyclerView(){
        List<BikerItemModel> bikerList1 = bikerListViewModel.getListBikerByType(bikerList,0);
        List<BikerItemModel> bikerList2 = bikerListViewModel.getListBikerByType(bikerList,1);
        List<BikerItemModel> bikerList3 = bikerListViewModel.getListBikerByType(bikerList,2);

        adapterRecyclerView = new ListBikerAdapterRecyclerView(bikerList1,requireActivity());
        adapterRecyclerView1 = new ListBikerAdapterRecyclerView(bikerList2,requireActivity());
        adapterRecyclerView2 = new ListBikerAdapterRecyclerView(bikerList3,requireActivity());

        binding.contentRecycler1.setBikerListRecyclerView(adapterRecyclerView,bikerList1,requireActivity());
        binding.contentRecycler2.setBikerListRecyclerView(adapterRecyclerView1,bikerList2,requireActivity());
        binding.contentRecycler3.setBikerListRecyclerView(adapterRecyclerView2,bikerList3,requireActivity());

    }

    private void filterRecycler(boolean isVisible1,boolean isVisible2,boolean isVisible3){
        binding.contentRecycler1.setVisibility(isVisible1 ? View.VISIBLE : View.GONE);
        binding.contentRecycler2.setVisibility(isVisible2 ? View.VISIBLE : View.GONE);
        binding.contentRecycler3.setVisibility(isVisible3 ? View.VISIBLE : View.GONE);
    }


}