package com.example.umg_moto_xpress_android.models.response.usuario;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPersonaResponse extends BaseResponse {

    @SerializedName("entity")
    @Expose
    private PersonData personData;

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }
}
