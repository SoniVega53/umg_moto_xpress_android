package com.example.umg_moto_xpress_android.ui.biker;


import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.biker.ListBikerAdapterRecyclerView;
import com.example.umg_moto_xpress_android.databinding.FragmentListBikerBinding;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListBikerFragment extends BaseFragment {

    private FragmentListBikerBinding binding;
    private ListBikerAdapterRecyclerView adapterRecyclerView;
    private ListBikerAdapterRecyclerView adapterSearch;
    private ListBikerAdapterRecyclerView adapterRecyclerView1;
    private ListBikerAdapterRecyclerView adapterRecyclerView2;
    private List<BikerItemModel> bikerList;
    private List<BikerItemModel> bikerListFilter;
    private boolean isVisible1 ;
    private boolean isVisible2;
    private boolean isVisible3;
    private int type;
    private BikerListViewModel bikerListViewModel;
    private ListBikerAdapterRecyclerView.OnclickAction onclickAction;

    public ListBikerFragment(List<BikerItemModel> bikerList,boolean isVisible1, boolean isVisible2, boolean isVisible3,int type,ListBikerAdapterRecyclerView.OnclickAction onclickAction) {
        this.isVisible1 = isVisible1;
        this.isVisible2 = isVisible2;
        this.isVisible3 = isVisible3;
        this.type = type;
        this.onclickAction = onclickAction;
        this.bikerList = bikerList;
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

        bikerListFilter = new ArrayList<>();

        addDataListRecyclerView();
        filterRecycler(isVisible1,isVisible2,isVisible3);

        if (type == 0 || type == 2)
            binding.rdGroup.setVisibility(View.GONE);

        binding.comSearch.getEditText().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                if ( bikerList != null && bikerList.size() > 0 && charSequence.length() > 0){
                    filterRecycler(false,false,false);
                    binding.contentSearch.setVisibility(View.VISIBLE);
                    binding.rdGroup.setVisibility(View.GONE);
                    adapterSearch.updateDataList(bikerListViewModel.getFilterListBikerByType(bikerListFilter,charSequence.toString()));
                }else {
                    binding.rd4.setChecked(true);
                    filterRecycler(isVisible1,isVisible2,isVisible3);
                    binding.contentSearch.setVisibility(View.GONE);
                    if (type == 1)
                        binding.rdGroup.setVisibility(View.VISIBLE);
                }

            }
        });

        if (type == 2){
            binding.contentRecycler1.setTextTitle("En Proceso");
            binding.contentRecycler2.setTextTitle("Cancelado");
            binding.contentRecycler3.setTextTitle("Finalizado");
        }


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

        if (isVisible1)
            bikerListFilter.addAll(bikerList1);
        if (isVisible2)
            bikerListFilter.addAll(bikerList2);
        if (isVisible3)
            bikerListFilter.addAll(bikerList3);

        adapterRecyclerView = new ListBikerAdapterRecyclerView(bikerList1,requireActivity(),onclickAction);
        adapterRecyclerView1 = new ListBikerAdapterRecyclerView(bikerList2,requireActivity(),onclickAction);
        adapterRecyclerView2 = new ListBikerAdapterRecyclerView(bikerList3,requireActivity(),onclickAction);
        adapterSearch = new ListBikerAdapterRecyclerView(bikerListFilter,requireActivity(),onclickAction);

        binding.contentRecycler1.setBikerListRecyclerView(adapterRecyclerView,bikerList1,requireActivity());
        binding.contentRecycler2.setBikerListRecyclerView(adapterRecyclerView1,bikerList2,requireActivity());
        binding.contentRecycler3.setBikerListRecyclerView(adapterRecyclerView2,bikerList3,requireActivity());
        binding.contentSearch.setBikerListRecyclerView(adapterSearch,bikerListFilter,requireActivity());


    }

    private void filterRecycler(boolean isVisible1,boolean isVisible2,boolean isVisible3){
        binding.contentRecycler1.setVisibility(isVisible1 ? View.VISIBLE : View.GONE);
        binding.contentRecycler2.setVisibility(isVisible2 ? View.VISIBLE : View.GONE);
        binding.contentRecycler3.setVisibility(isVisible3 ? View.VISIBLE : View.GONE);
    }

}