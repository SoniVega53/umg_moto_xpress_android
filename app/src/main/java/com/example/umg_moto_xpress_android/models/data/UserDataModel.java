package com.example.umg_moto_xpress_android.models.data;

public class UserDataModel {
    private String userName;
    private String idUser;
    private String email;
    private String name;
    private String lastname;

    public UserDataModel(String userName, String email, String name, String lastname,String idUser) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser != null ? idUser :"";
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
