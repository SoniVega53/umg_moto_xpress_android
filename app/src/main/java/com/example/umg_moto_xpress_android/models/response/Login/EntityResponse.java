package com.example.umg_moto_xpress_android.models.response.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityResponse {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
