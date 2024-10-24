package com.example.umg_moto_xpress_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserProfileData;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;
import com.example.umg_moto_xpress_android.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<StatusResponse<UserListResponse>> userListResponse;

    public MutableLiveData<StatusResponse<UserListResponse>> getUserListResponse() {
        return userListResponse;
    }

    public void setUserListResponse(Context context) {
        UserRepository repository = new UserRepository(context);
        userListResponse = repository.getListUsers();
    }

    public List<UserDataModel> getListUsers(List<UserProfileData> userProfileDataList){
        List<UserDataModel> list = new ArrayList<>();
        for (UserProfileData item:userProfileDataList) {
            list.add(new UserDataModel(item.getUsername(),item.getEmail(),item.getPerson().getName(),item.getPerson().getLastName()));
        }
        return list;
    }

}
