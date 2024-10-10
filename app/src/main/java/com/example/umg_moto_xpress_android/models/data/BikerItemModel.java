package com.example.umg_moto_xpress_android.models.data;

public class BikerItemModel {

    private String title;
    private String description;
    private String value;

    public BikerItemModel(String title, String description, String value) {
        this.title = title;
        this.description = description;
        this.value = value;
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
}
