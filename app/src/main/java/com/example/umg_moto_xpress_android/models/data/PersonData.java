package com.example.umg_moto_xpress_android.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonData {

    @SerializedName("nombre")
    @Expose
    private String name;

    @SerializedName("apellido")
    @Expose
    private String lastName;

    @SerializedName("telefono")
    @Expose
    private String phone;

    @SerializedName("direccion")
    @Expose
    private String address;

    public PersonData() {
    }

    public PersonData(String name, String lastName, String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone != null ? phone : "";
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address != null ? address : "";
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
