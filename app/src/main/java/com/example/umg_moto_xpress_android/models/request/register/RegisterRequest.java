package com.example.umg_moto_xpress_android.models.request.register;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("rolId")
    @Expose
    private String rolId;

    @SerializedName("persona")
    @Expose
    private PersonData personData;

    public RegisterRequest(String username, String password, String email, String rolId, PersonData personData) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rolId = rolId;
        this.personData = personData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }
}
