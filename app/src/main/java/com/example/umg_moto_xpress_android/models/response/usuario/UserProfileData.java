package com.example.umg_moto_xpress_android.models.response.usuario;

import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.data.RolData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileData {
    @SerializedName("idUsuario")
    @Expose
    private String idUser;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("correo")
    @Expose
    private String email;

    @SerializedName("rol")
    @Expose
    private RolData role;

    @SerializedName("persona")
    @Expose
    private PersonData person;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolData getRole() {
        return role;
    }

    public void setRole(RolData role) {
        this.role = role;
    }

    public PersonData getPerson() {
        return person;
    }

    public void setPerson(PersonData person) {
        this.person = person;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
