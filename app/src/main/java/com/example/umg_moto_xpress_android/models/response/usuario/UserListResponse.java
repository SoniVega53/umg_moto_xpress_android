package com.example.umg_moto_xpress_android.models.response.usuario;

import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListResponse extends BaseResponse {
    @SerializedName("entity")
    @Expose
    private List<UserProfileData> userProfileDataList;

    public List<UserProfileData> getUserProfileDataList() {
        return userProfileDataList;
    }

    public void setUserProfileDataList(List<UserProfileData> userProfileDataList) {
        this.userProfileDataList = userProfileDataList;
    }
}
