package com.example.umg_moto_xpress_android.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.adapters.users.UserListAdapter;
import com.example.umg_moto_xpress_android.databinding.FragmentUsersListBinding;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.repositories.user.UserRepository;
import com.example.umg_moto_xpress_android.tools.JwtDecoder;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class UsersListFragment extends BaseFragment {

    private FragmentUsersListBinding binding;
    private UserListAdapter adapterList;
    private List<UserDataModel> listUserResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersListBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());
        listUserResponse = new ArrayList<>();

        binding.comSearch.getEditText().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                if ( listUserResponse != null && listUserResponse.size() > 0){
                    adapterList.updateDataList(userViewModel.getListUsersFilter(listUserResponse,charSequence.toString()));
                }
            }
        });

        getServiceListUsers();

        return binding.getRoot();
    }


    private void getServiceListUsers(){
        loadingShow(true);
        userViewModel.setUserListResponse(requireActivity());

        userViewModel.getUserListResponse().observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        listUserResponse = userViewModel.getListUsers(loginResponse.getResponse().getUserProfileDataList(),getUserDecodeData().getName());
                        getRecyclerView();
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

    private void getRecyclerView(){
        adapterList = new UserListAdapter(listUserResponse);
        adapterList.setActionClickUser(id -> {
            dialogMessage(getString(R.string.delete),getString(R.string.delete_message),0,view1 -> {
                getServiceDeleteUser(id);
            });
        });

        binding.recyclerUsers.setLayoutManager(new GridLayoutManager(requireActivity(),1));
        binding.recyclerUsers.setHasFixedSize(true);
        binding.recyclerUsers.setAdapter(adapterList);
    }

    private void getServiceChangeRole(String rol){
        loadingShow(true);
        userViewModel.getUpdateUserRole(requireActivity(),rol).observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:

                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        loadingShow(false);
                        break;
                }
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }

    private void getServiceDeleteUser(String idUser){
        loadingShow(true);
        userViewModel.getDeleteUser(requireActivity(),idUser).observe(requireActivity(), loginResponse -> {
            try {
                switch (loginResponse.getStatus()){
                    case StringTool.SUCCESS:
                        getServiceListUsers();
                        Toast.makeText(requireActivity(), loginResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                    case StringTool.ERROR:
                        if (loginResponse.getResponse() != null){
                            dialogMessage(getString(R.string.title_error),loginResponse.getResponse().getMessage(),1);
                        }else {
                            dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                        }
                        loadingShow(false);
                        break;
                }
            }catch (Exception e){
                dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                loadingShow(false);
            }
        });
    }
}