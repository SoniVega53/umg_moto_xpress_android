package com.example.umg_moto_xpress_android.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.users.UserListAdapter;
import com.example.umg_moto_xpress_android.databinding.FragmentUsersListBinding;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.repositories.user.UserRepository;
import com.example.umg_moto_xpress_android.tools.JwtDecoder;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class UsersListFragment extends BaseFragment {

    private FragmentUsersListBinding binding;
    private UserViewModel userViewModel;
    private UserListAdapter adapterList;
    private List<UserDataModel> listUserResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersListBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        listUserResponse = new ArrayList<>();
        getServiceLogin();

        return binding.getRoot();
    }


    private void getServiceLogin(){
        loadingShow(true);
        userViewModel.setUserListResponse(requireActivity());

        userViewModel.getUserListResponse().observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        listUserResponse = userViewModel.getListUsers(loginResponse.getResponse().getUserProfileDataList());

                        adapterList = new UserListAdapter(listUserResponse);

                        binding.recyclerUsers.setLayoutManager(new GridLayoutManager(requireActivity(),1));
                        binding.recyclerUsers.setHasFixedSize(true);
                        binding.recyclerUsers.setAdapter(adapterList);
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        break;
                }
                loadingShow(false);
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }
}