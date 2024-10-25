package com.example.umg_moto_xpress_android.models.response.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityRegisterData {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("correo")
    @Expose
    private String email;
}
