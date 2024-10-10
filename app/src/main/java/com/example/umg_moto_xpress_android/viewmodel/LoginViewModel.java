package com.example.umg_moto_xpress_android.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;
import com.example.umg_moto_xpress_android.repositories.LoginRepository;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<StatusResponse<LoginResponse>> loginResponseMutableLiveData;

    public MutableLiveData<StatusResponse<LoginResponse>> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }

    public void setLoginResponseMutableLiveData(Context context, LoginSingRequest request) {
        LoginRepository repository = new LoginRepository(context);
        loginResponseMutableLiveData = repository.getLoginCall(request);
    }

}
