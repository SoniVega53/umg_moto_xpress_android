package com.example.umg_moto_xpress_android.repositories.user;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.data.UserDecodeData;
import com.example.umg_moto_xpress_android.models.request.LoginSingRequest;
import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserDetailResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserListResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserPersonaResponse;
import com.example.umg_moto_xpress_android.network.user.UserApi;
import com.example.umg_moto_xpress_android.repositories.BaseRepository;
import com.example.umg_moto_xpress_android.repositories.MainRepositories;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;
import com.example.umg_moto_xpress_android.tools.StringTool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class UserRepository extends BaseRepository {
    private static UserRepository loginRepository;
    private UserApi userApi;
    private Context context;

    public UserRepository(Context context) {
        this.context = context;
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

    public MutableLiveData<StatusResponse<UserDetailResponse>> getUserProfile(){
        MutableLiveData<StatusResponse<UserDetailResponse>> data = new MutableLiveData<>();
        StatusResponse<UserDetailResponse> statusResponse = new StatusResponse<>();

        UserDecodeData user = getUserDecodeData(context);

        statusResponse.setStatus(StringTool.LOADING);
        userApi.getDetailUser(user.getName()).enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(Call<UserDetailResponse> call, Response<UserDetailResponse> response) {
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
            public void onFailure(Call<UserDetailResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> postChangePassword(String password, String passwordChange){
        MutableLiveData<StatusResponse<BaseResponse>> data = new MutableLiveData<>();
        StatusResponse<BaseResponse> statusResponse = new StatusResponse<>();

        UserDecodeData user = getUserDecodeData(context);

        statusResponse.setStatus(StringTool.LOADING);
        userApi.getChangePassword(user.getName(),password,passwordChange).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
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
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

    public MutableLiveData<StatusResponse<UserPersonaResponse>> postUpdateUserData(PersonData request){
        MutableLiveData<StatusResponse<UserPersonaResponse>> data = new MutableLiveData<>();
        StatusResponse<UserPersonaResponse> statusResponse = new StatusResponse<>();

        UserDecodeData user = getUserDecodeData(context);

        statusResponse.setStatus(StringTool.LOADING);
        userApi.getUpdateUser(user.getName(),request).enqueue(new Callback<UserPersonaResponse>() {
            @Override
            public void onResponse(Call<UserPersonaResponse> call, Response<UserPersonaResponse> response) {
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
            public void onFailure(Call<UserPersonaResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> postUpdateUserRol(String idRole){
        MutableLiveData<StatusResponse<BaseResponse>> data = new MutableLiveData<>();
        StatusResponse<BaseResponse> statusResponse = new StatusResponse<>();

        UserDecodeData user = getUserDecodeData(context);

        statusResponse.setStatus(StringTool.LOADING);
        userApi.getUpdateUserRol(user.getName(),idRole).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
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
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

    public MutableLiveData<StatusResponse<BaseResponse>> deleteUser(String idUser){
        MutableLiveData<StatusResponse<BaseResponse>> data = new MutableLiveData<>();
        StatusResponse<BaseResponse> statusResponse = new StatusResponse<>();

        statusResponse.setStatus(StringTool.LOADING);
        userApi.deleteUser(idUser).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
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
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                statusResponse.setStatus(StringTool.ERROR);
                data.setValue(new StatusResponse<>());
            }
        });

        return data;
    }

}
