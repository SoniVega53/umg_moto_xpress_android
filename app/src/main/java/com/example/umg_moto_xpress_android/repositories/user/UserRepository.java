package com.example.umg_moto_xpress_android.repositories.user;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;
import com.example.umg_moto_xpress_android.network.user.UserApi;
import com.example.umg_moto_xpress_android.repositories.MainRepositories;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;
import com.example.umg_moto_xpress_android.tools.StringTool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository loginRepository;
    private UserApi userApi;

    public UserRepository(Context context) {
        userApi = MainRepositories.createServiceToken(UserApi.class,context);
    }

    public static UserRepository getInstance(Context context){
        if (loginRepository == null){
            loginRepository = new UserRepository(context);
        }
        return loginRepository;
    }

    public MutableLiveData<StatusResponse<UserListResponse>> getListUsers(){
        MutableLiveData<StatusResponse<UserListResponse>> data = new MutableLiveData<>();
        StatusResponse<UserListResponse> statusResponse = new StatusResponse<>();

        statusResponse.setStatus(StringTool.LOADING);
        userApi.getListUsers().enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                try {
                    statusResponse.setStatus(!response.body().getCode().equals("400") ? StringTool.SUCCESS:StringTool.ERROR);
                    statusResponse.setResponse(response.body());
                    data.setValue(statusResponse);

                }catch (Exception e){
                    statusResponse.setStatus(StringTool.ERROR);
                    data.setValue(new StatusResponse<>());
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

}
