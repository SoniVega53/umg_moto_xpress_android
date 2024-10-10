package com.example.umg_moto_xpress_android.models.response.Login;

import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponse {

    @SerializedName("entity")
    @Expose
    private EntityResponse entityResponse;

    public EntityResponse getEntityResponse() {
        return entityResponse;
    }

    public void setEntityResponse(EntityResponse entityResponse) {
        this.entityResponse = entityResponse;
    }
}
