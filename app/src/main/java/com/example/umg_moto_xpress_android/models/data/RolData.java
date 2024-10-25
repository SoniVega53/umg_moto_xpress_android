package com.example.umg_moto_xpress_android.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RolData {
    @SerializedName("nombre")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
