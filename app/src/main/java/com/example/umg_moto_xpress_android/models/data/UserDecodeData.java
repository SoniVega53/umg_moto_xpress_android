package com.example.umg_moto_xpress_android.models.data;

public class UserDecodeData {
    private String name;
    private String role;

    public UserDecodeData(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
