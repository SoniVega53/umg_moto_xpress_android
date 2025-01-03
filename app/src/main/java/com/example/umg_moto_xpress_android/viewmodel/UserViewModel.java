package com.example.umg_moto_xpress_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserDetailResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserPersonaResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserProfileData;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;
import com.example.umg_moto_xpress_android.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<StatusResponse<UserListResponse>> userListResponse;
    private MutableLiveData<StatusResponse<UserDetailResponse>> userDetailResponse;
    private MutableLiveData<StatusResponse<BaseResponse>> changePassword;
    private MutableLiveData<StatusResponse<UserPersonaResponse>> putUserDetail;
    private MutableLiveData<StatusResponse<BaseResponse>> updateUserRol;
    private MutableLiveData<StatusResponse<BaseResponse>> deleteUser;

    public MutableLiveData<StatusResponse<UserListResponse>> getUserListResponse() {
        return userListResponse;
    }

    public void setUserListResponse(Context context) {
        UserRepository repository = new UserRepository(context);
        userListResponse = repository.getListUsers();
    }

    public MutableLiveData<StatusResponse<UserDetailResponse>> getUserDetailResponse(Context context) {
        UserRepository repository = new UserRepository(context);
        userDetailResponse = repository.getUserProfile();
        return userDetailResponse;
    }

    public MutableLiveData<StatusResponse<UserDetailResponse>> getUserDetailResponse() {
        return userDetailResponse;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> getChangePassword(Context context,String password, String passwordChange) {
        UserRepository repository = new UserRepository(context);
        changePassword = repository.postChangePassword(password,passwordChange);
        return changePassword;
    }

    public MutableLiveData<StatusResponse<UserPersonaResponse>> getUpdateUser(Context context, PersonData request) {
        UserRepository repository = new UserRepository(context);
        putUserDetail = repository.postUpdateUserData(request);
        return putUserDetail;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> getUpdateUserRole(Context context, String rol) {
        UserRepository repository = new UserRepository(context);
        updateUserRol = repository.postUpdateUserRol(rol);
        return updateUserRol;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> getDeleteUser(Context context, String idUser) {
        UserRepository repository = new UserRepository(context);
        deleteUser = repository.deleteUser(idUser);
        return deleteUser;
    }

    public List<UserDataModel> getListUsers(List<UserProfileData> userProfileDataList, String userLogin){
        List<UserDataModel> list = new ArrayList<>();
        for (UserProfileData item:userProfileDataList) {
            if (!userLogin.trim().toLowerCase().equals(item.getUsername()))
                list.add(new UserDataModel(item.getUsername(),item.getEmail(),item.getPerson().getName(),item.getPerson().getLastName(),item.getIdUser()));
        }
        return list;
    }

    public List<UserDataModel> getListUsersFilter(List<UserDataModel> userProfileDataList, String search){
        if (search != null && !search.isEmpty()){
            List<UserDataModel> list = new ArrayList<>();
            for (UserDataModel item:userProfileDataList) {
                if (item.getUserName().toLowerCase().contains(search.toLowerCase().trim()))
                    list.add(item);
            }
            return list;
        }else{
            return userProfileDataList;
        }
    }

}
