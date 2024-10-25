package com.example.umg_moto_xpress_android.network.user;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserDetailResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserPersonaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("admin/usuarios/list")
    Call<UserListResponse> getListUsers();

    @GET("detalle/usuario/{username}")
    Call<UserDetailResponse> getDetailUser(@Path("username") String username);

    @POST("usuario/update/password/{username}/{password}/{passwordChange}")
    Call<BaseResponse> getChangePassword(@Path("username") String username, @Path("password") String password, @Path("passwordChange") String passwordChange);

    @POST("usuario/update/{username}")
    Call<UserPersonaResponse> getUpdateUser(@Path("username") String username, @Body PersonData personDataRequest);
}
