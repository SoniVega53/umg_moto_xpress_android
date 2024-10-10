package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.response.Login.EntityResponse;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;
import com.example.umg_moto_xpress_android.network.login.LoginApi;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.securepreferences.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {
    private static LoginRepository loginRepository;
    private LoginApi loginApi;

    public LoginRepository(Context context) {
        loginApi = MainRepositories.createService(LoginApi.class,context);
    }

    public static LoginRepository getInstance(Context context){
        if (loginRepository == null){
            loginRepository = new LoginRepository(context);
        }
        return loginRepository;
    }

    public MutableLiveData<StatusResponse<LoginResponse>> getLoginCall(LoginSingRequest request){
        MutableLiveData<StatusResponse<LoginResponse>> data = new MutableLiveData<>();
        StatusResponse<LoginResponse> statusResponse = new StatusResponse<>();

        statusResponse.setStatus(StringTool.LOADING);
        loginApi.getLoginCall(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }
}