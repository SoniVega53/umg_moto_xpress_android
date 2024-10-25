package com.example.umg_moto_xpress_android.models.response.register;

import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse extends BaseResponse {
    @SerializedName("entity")
    @Expose
    private EntityRegisterData username;

    public EntityRegisterData getUsername() {
        return username;
    }

    public void setUsername(EntityRegisterData username) {
        this.username = username;
    }
}
