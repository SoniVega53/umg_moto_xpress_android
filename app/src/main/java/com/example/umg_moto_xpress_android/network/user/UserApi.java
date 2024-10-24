package com.example.umg_moto_xpress_android.network.user;

import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {

    @GET("admin/see")
    Call<UserListResponse> getListUsers();

}
