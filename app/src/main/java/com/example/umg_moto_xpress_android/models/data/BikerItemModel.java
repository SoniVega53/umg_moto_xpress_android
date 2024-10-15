package com.example.umg_moto_xpress_android.models.data;

public class BikerItemModel {

    private String title;
    private String description;
    private String value;
    private int type;


    public BikerItemModel(String title, String description, String value,int type) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
