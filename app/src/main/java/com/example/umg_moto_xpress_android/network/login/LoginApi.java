package com.example.umg_moto_xpress_android.network.login;

import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.response.Login.EntityResponse;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("login")
    Call<LoginResponse> getLoginCall(@Body LoginSingRequest loginSingRequest);
}
