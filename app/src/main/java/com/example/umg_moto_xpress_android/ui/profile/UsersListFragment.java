package com.example.umg_moto_xpress_android.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.users.UserListAdapter;
import com.example.umg_moto_xpress_android.databinding.FragmentUsersListBinding;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class UsersListFragment extends BaseFragment {

    private FragmentUsersListBinding binding;
    private UserListAdapter adapterList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersListBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        List<UserDataModel> list = new ArrayList<>();
        list.add(new UserDataModel("Svega","svega@gmail.com","Soni","Vega"));
        list.add(new UserDataModel("Smunoz","Smunoz@gmail.com","Javier","Munoz"));
        list.add(new UserDataModel("Sperez","Sperez@gmail.com","Juan","Perez"));



        adapterList = new UserListAdapter(list);

        binding.recyclerUsers.setLayoutManager(new GridLayoutManager(requireActivity(),1));
        binding.recyclerUsers.setHasFixedSize(true);
        binding.recyclerUsers.setAdapter(adapterList);

        return binding.getRoot();
    }
}