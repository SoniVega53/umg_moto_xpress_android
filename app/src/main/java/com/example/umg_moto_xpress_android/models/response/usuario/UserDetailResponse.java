package com.example.umg_moto_xpress_android.models.response.usuario;

import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailResponse extends BaseResponse {
    @SerializedName("entity")
    @Expose
    private UserProfileData userProfileData;

    public UserProfileData getUserProfileData() {
        return userProfileData;
    }

    public void setUserProfileData(UserProfileData userProfileData) {
        this.userProfileData = userProfileData;
    }
}
